package br.net.woodstock.test.certtest;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

import org.apache.commons.codec.binary.Base64;

public abstract class KeyStoreUtils {

	public static final String	KEYSTORE_PKCS12_TYPE	= "PKCS12";

	public static final String	KEYSTORE_JKS_TYPE		= "JKS";

	private KeyStoreUtils() {
		//
	}

	public static KeyStore load(final InputStream inputStream, final String password, final String type) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		char[] passwordChars = password == null ? null : password.toCharArray();
		KeyStore keyStore = KeyStore.getInstance(type);
		keyStore.load(inputStream, passwordChars);

		return keyStore;
	}

	public static KeyStore copy(final KeyStore keyStore, final String type, final String[] aliases, final String[] passwords) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, IOException {
		KeyStore newKeyStore = KeyStore.getInstance(type);
		newKeyStore.load(null, null);

		for (int i = 0; i < aliases.length; i++) {
			String alias = aliases[i];
			if (keyStore.isCertificateEntry(alias)) {
				Certificate certificate = keyStore.getCertificate(alias);
				newKeyStore.setCertificateEntry(alias, certificate);
			} else if (keyStore.isKeyEntry(alias)) {
				String password = passwords[i];
				char[] passwordChars = password == null ? null : password.toCharArray();
				Key key = keyStore.getKey(alias, passwordChars);
				if (key instanceof PrivateKey) {
					PrivateKey privateKey = (PrivateKey) key;
					Certificate[] chain = keyStore.getCertificateChain(alias);
					newKeyStore.setKeyEntry(alias, privateKey, passwordChars, chain);
				}
			}
		}
		return newKeyStore;
	}

	public static byte[] toBase64(final Certificate certificate) throws CertificateEncodingException {
		return Base64.encodeBase64(certificate.getEncoded(), true);
	}

}
