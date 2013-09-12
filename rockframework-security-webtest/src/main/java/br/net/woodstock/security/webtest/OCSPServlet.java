package br.net.woodstock.security.webtest;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.net.woodstock.rockframework.core.utils.Codecs;
import br.net.woodstock.rockframework.core.utils.IO;
import br.net.woodstock.rockframework.core.utils.Resources;
import br.net.woodstock.rockframework.security.Identity;
import br.net.woodstock.rockframework.security.cert.OCSPRequest;
import br.net.woodstock.rockframework.security.cert.OCSPResponse;
import br.net.woodstock.rockframework.security.cert.OCSPValidator;
import br.net.woodstock.rockframework.security.cert.RevokeReason;
import br.net.woodstock.rockframework.security.cert.impl.BouncyCastleOCSPGenerator;
import br.net.woodstock.rockframework.security.digest.DigestType;
import br.net.woodstock.rockframework.security.digest.impl.BasicDigester;
import br.net.woodstock.rockframework.security.sign.SignatureType;
import br.net.woodstock.rockframework.security.store.KeyStoreType;
import br.net.woodstock.rockframework.security.store.PrivateKeyEntry;
import br.net.woodstock.rockframework.security.store.Store;
import br.net.woodstock.rockframework.security.store.StoreAlias;
import br.net.woodstock.rockframework.security.store.impl.JCAStore;

public class OCSPServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;

	private static final String	CONTENT_TYPE		= "application/ocsp-response";

	private Identity			identity;

	private OCSPValidator		validator;

	public OCSPServlet() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			InputStream propertiesInputStream = Resources.getResourceAsStream("webtest.properties");
			Properties properties = new Properties();
			properties.load(propertiesInputStream);

			String storeType = properties.getProperty("store.type");
			String storePassword = properties.getProperty("store.password");
			String storeResource = properties.getProperty("store.resource");
			String keyAlias = properties.getProperty("key.alias");
			String keyPassword = properties.getProperty("key.password");

			InputStream storeInputStream = Resources.getResourceAsStream(storeResource);

			Store store = new JCAStore(KeyStoreType.valueOf(storeType));
			store.read(storeInputStream, storePassword);
			PrivateKeyEntry entry = (PrivateKeyEntry) store.get(new StoreAlias(keyAlias, keyPassword));
			this.identity = entry.toIdentity();
			this.validator = new OCSPValidatorTest();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doAll(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doAll(req, resp);
	}

	protected void doAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		OCSPRequest request = new OCSPRequest(this.identity, SignatureType.SHA1_RSA, this.validator, IO.toByteArray(req.getInputStream()));
		OCSPResponse response = BouncyCastleOCSPGenerator.getInstance().generate(request);

		byte[] bytes = response.getEncoded();

		System.out.println(Codecs.toHex(new BasicDigester(DigestType.SHA1).digest(bytes)));

		resp.setContentType(OCSPServlet.CONTENT_TYPE);
		resp.getOutputStream().write(bytes);
	}

	static class OCSPValidatorTest implements OCSPValidator {

		public OCSPValidatorTest() {
			super();
		}

		@Override
		public RevokeReason validate(final BigInteger serial) {
			System.out.println("Validating  " + serial);
			return null;
		}

	}

}
