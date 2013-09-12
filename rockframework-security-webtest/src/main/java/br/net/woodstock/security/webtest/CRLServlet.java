package br.net.woodstock.security.webtest;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.X509CRL;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.net.woodstock.rockframework.core.utils.Resources;
import br.net.woodstock.rockframework.security.Identity;
import br.net.woodstock.rockframework.security.cert.CRLEntry;
import br.net.woodstock.rockframework.security.cert.CRLRequest;
import br.net.woodstock.rockframework.security.cert.CRLResponse;
import br.net.woodstock.rockframework.security.cert.RevokeReason;
import br.net.woodstock.rockframework.security.cert.impl.BouncyCastleCRLGenerator;
import br.net.woodstock.rockframework.security.sign.SignatureType;
import br.net.woodstock.rockframework.security.store.KeyStoreType;
import br.net.woodstock.rockframework.security.store.PrivateKeyEntry;
import br.net.woodstock.rockframework.security.store.Store;
import br.net.woodstock.rockframework.security.store.StoreAlias;
import br.net.woodstock.rockframework.security.store.impl.JCAStore;

public class CRLServlet extends HttpServlet {

	private static final long	serialVersionUID	= 1L;

	private Identity			identity;

	public CRLServlet() {
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
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doAll(resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doAll(resp);
	}

	protected void doAll(HttpServletResponse resp) throws ServletException {
		CRLRequest request = new CRLRequest(this.identity, SignatureType.SHA1_RSA);
		request.getEntries().add(new CRLEntry(BigInteger.valueOf(123456789), RevokeReason.KEY_COMPROMISE));

		CRLResponse response = BouncyCastleCRLGenerator.getInstance().generate(request);

		try {
			resp.getOutputStream().write(((X509CRL) response.getCrl()).getEncoded());
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
