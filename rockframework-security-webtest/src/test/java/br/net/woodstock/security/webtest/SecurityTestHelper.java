package br.net.woodstock.security.webtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import br.net.woodstock.rockframework.core.utils.SystemProperties;
import br.net.woodstock.rockframework.security.Identity;
import br.net.woodstock.rockframework.security.cert.CertificateRequest;
import br.net.woodstock.rockframework.security.cert.CertificateResponse;
import br.net.woodstock.rockframework.security.cert.ExtendedKeyUsageType;
import br.net.woodstock.rockframework.security.cert.KeySizeType;
import br.net.woodstock.rockframework.security.cert.KeyUsageType;
import br.net.woodstock.rockframework.security.cert.ext.icpbrasil.DadoPessoa;
import br.net.woodstock.rockframework.security.cert.ext.icpbrasil.PessoaFisicaCertificateExtensionHandler;
import br.net.woodstock.rockframework.security.cert.ext.icpbrasil.TipoFormato;
import br.net.woodstock.rockframework.security.cert.impl.BouncyCastleCertificateGenerator;
import br.net.woodstock.rockframework.security.store.KeyStoreType;
import br.net.woodstock.rockframework.security.store.PrivateKeyEntry;
import br.net.woodstock.rockframework.security.store.Store;
import br.net.woodstock.rockframework.security.store.StoreAlias;
import br.net.woodstock.rockframework.security.store.impl.JCAStore;

public abstract class SecurityTestHelper {

	public static final String	CA_STORE_KEY_ALIAS		= "woodstock";

	public static final String	CA_STORE_KEY_PASSWORD	= "woodstock";

	public static final String	CA_STORE_PASSWORD		= "woodstock";

	public static final String	USER_STORE_KEY_ALIAS	= "lourival";

	public static final String	USER_STORE_KEY_PASSWORD	= "lourival";

	public static final String	USER_STORE_PASSWORD		= "lourival";

	public static final String	TSA_STORE_KEY_ALIAS		= "woodstocktsa";

	public static final String	TSA_STORE_KEY_PASSWORD	= "woodstocktsa";

	public static final String	TSA_STORE_PASSWORD		= "woodstocktsa";

	private static final String	CA_STORE_DIR			= "tmp";

	private static final String	CA_STORE_FILE			= "woodstock.pfx";

	private static final String	USER_STORE_DIR			= "tmp";

	private static final String	USER_STORE_FILE			= "lourival.pfx";

	private static final String	TSA_STORE_DIR			= "tmp";

	private static final String	TSA_STORE_FILE			= "woodstock-tsa.pfx";

	private SecurityTestHelper() {
		//
	}

	public static Store getCAStore() throws Exception {
		File dir = new File(SystemProperties.getProperty(SystemProperties.USER_HOME_PROPERTY), SecurityTestHelper.CA_STORE_DIR);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(dir, SecurityTestHelper.CA_STORE_FILE);

		Store store = null;

		if (!file.exists()) {
			CertificateRequest request = new CertificateRequest("Woodstock Tecnologia CA");
			request.setCa(true);
			request.setComment("Woodstock Tecnologia CA");
			request.setEmail("ca@woodstock.net.br");
			request.setKeySize(KeySizeType.KEYSIZE_4K);

			CertificateResponse response = BouncyCastleCertificateGenerator.getInstance().generate(request);
			Identity identity = response.getIdentity();

			store = new JCAStore(KeyStoreType.PKCS12);
			store.add(new PrivateKeyEntry(new StoreAlias(SecurityTestHelper.CA_STORE_KEY_ALIAS, SecurityTestHelper.CA_STORE_KEY_PASSWORD), identity));

			FileOutputStream outputStream = new FileOutputStream(file);
			store.write(outputStream, SecurityTestHelper.CA_STORE_PASSWORD);
			outputStream.close();
		} else {
			FileInputStream inputStream = new FileInputStream(file);
			store = new JCAStore(KeyStoreType.PKCS12);
			store.read(inputStream, SecurityTestHelper.CA_STORE_PASSWORD);
			inputStream.close();
		}

		return store;

	}

	public static Store getUserStore() throws Exception {
		File dir = new File(SystemProperties.getProperty(SystemProperties.USER_HOME_PROPERTY), SecurityTestHelper.USER_STORE_DIR);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(dir, SecurityTestHelper.USER_STORE_FILE);

		Store store = null;

		if (!file.exists()) {
			CertificateRequest request = new CertificateRequest("Lourival Sabino");
			request.setEmail("lourival.sabino.junior@gmail.com");
			// request.withIssuer("Woodstock Tecnologia");
			request.setKeySize(KeySizeType.KEYSIZE_2K);
			request.getKeyUsage().add(KeyUsageType.DIGITAL_SIGNATURE);
			request.getKeyUsage().add(KeyUsageType.NON_REPUDIATION);
			request.getKeyUsage().add(KeyUsageType.KEY_ENCIPHERMENT);
			request.getExtendedKeyUsage().add(ExtendedKeyUsageType.CLIENT_AUTH);
			request.getExtendedKeyUsage().add(ExtendedKeyUsageType.EMAIL_PROTECTION);

			// ICP Brasil
			DadoPessoa dadoPessoa = new DadoPessoa();
			dadoPessoa.setCpf("11111111111");
			dadoPessoa.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("24/05/1979"));
			dadoPessoa.setEmissorRG("SSP/DF");
			dadoPessoa.setPis("33333333333");
			dadoPessoa.setRg("2222222");

			PessoaFisicaCertificateExtensionHandler extension = new PessoaFisicaCertificateExtensionHandler();

			extension.setTipoFormato(TipoFormato.A3);
			extension.setCei("111111111111");
			extension.setDadoTitular(dadoPessoa);
			extension.setRegistroOAB("DF123456-A");
			extension.setRegistroSINCOR("123456DF");
			extension.setRic("66666666666");
			extension.setTituloEleitor("7777777777777");

			// extensions.process(request);
			request.getExtensionHandlers().add(extension);

			// CA
			request.setIssuer(SecurityTestHelper.getCAIdentity());

			Identity identity = BouncyCastleCertificateGenerator.getInstance().generate(request).getIdentity();
			System.out.println(identity);

			store = new JCAStore(KeyStoreType.PKCS12);
			store.add(new PrivateKeyEntry(new StoreAlias(SecurityTestHelper.USER_STORE_KEY_ALIAS, SecurityTestHelper.USER_STORE_KEY_PASSWORD), identity));

			FileOutputStream outputStream = new FileOutputStream(file);
			store.write(outputStream, SecurityTestHelper.USER_STORE_PASSWORD);
			outputStream.close();
		} else {
			FileInputStream inputStream = new FileInputStream(file);
			store = new JCAStore(KeyStoreType.PKCS12);
			store.read(inputStream, SecurityTestHelper.USER_STORE_PASSWORD);
			inputStream.close();
		}

		return store;
	}

	public static Store getTsaStore() throws Exception {
		File dir = new File(SystemProperties.getProperty(SystemProperties.USER_HOME_PROPERTY), SecurityTestHelper.TSA_STORE_DIR);

		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(dir, SecurityTestHelper.TSA_STORE_FILE);

		Store store = null;

		if (!file.exists()) {
			CertificateRequest request = new CertificateRequest("Woodstock TSA");
			request.setEmail("lourival.sabino.junior@gmail.com");
			// request.withIssuer("Woodstock Tecnologia");
			request.setKeySize(KeySizeType.KEYSIZE_2K);
			request.getExtendedKeyUsage().add(ExtendedKeyUsageType.TIMESTAMPING);

			// CA
			request.setIssuer(SecurityTestHelper.getCAIdentity());

			Identity identity = BouncyCastleCertificateGenerator.getInstance().generate(request).getIdentity();
			System.out.println(identity);

			store = new JCAStore(KeyStoreType.PKCS12);
			store.add(new PrivateKeyEntry(new StoreAlias(SecurityTestHelper.TSA_STORE_KEY_ALIAS, SecurityTestHelper.TSA_STORE_KEY_PASSWORD), identity));

			FileOutputStream outputStream = new FileOutputStream(file);
			store.write(outputStream, SecurityTestHelper.TSA_STORE_PASSWORD);
			outputStream.close();
		} else {
			FileInputStream inputStream = new FileInputStream(file);
			store = new JCAStore(KeyStoreType.PKCS12);
			store.read(inputStream, SecurityTestHelper.TSA_STORE_PASSWORD);
			inputStream.close();
		}

		return store;

	}

	public static Identity getCAIdentity() throws Exception {
		Store caStore = SecurityTestHelper.getCAStore();
		PrivateKeyEntry entry = (PrivateKeyEntry) caStore.get(new StoreAlias(SecurityTestHelper.CA_STORE_KEY_ALIAS, SecurityTestHelper.CA_STORE_KEY_PASSWORD));
		return entry.toIdentity();
	}

	public static Identity getUserIdentity() throws Exception {
		Store caStore = SecurityTestHelper.getUserStore();
		PrivateKeyEntry entry = (PrivateKeyEntry) caStore.get(new StoreAlias(SecurityTestHelper.USER_STORE_KEY_ALIAS, SecurityTestHelper.USER_STORE_KEY_PASSWORD));
		return entry.toIdentity();
	}

	public static Identity getTsaIdentity() throws Exception {
		Store caStore = SecurityTestHelper.getTsaStore();
		PrivateKeyEntry entry = (PrivateKeyEntry) caStore.get(new StoreAlias(SecurityTestHelper.TSA_STORE_KEY_ALIAS, SecurityTestHelper.TSA_STORE_KEY_PASSWORD));
		return entry.toIdentity();
	}

}
