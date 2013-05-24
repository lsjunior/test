package br.net.woodstock.test.certtest.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

import org.junit.Test;

import br.net.woodstock.test.certtest.KeyStoreUtils;

public class CertTest {

	public CertTest() {
		super();
	}

	@Test
	public void testCopy() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("lourival.pfx");
		KeyStore keyStore = KeyStoreUtils.load(inputStream, "lourival", KeyStoreUtils.KEYSTORE_PKCS12_TYPE);

		// Tanto oa alias como a senha sao lourival
		KeyStore newKeystore = KeyStoreUtils.copy(keyStore, KeyStoreUtils.KEYSTORE_JKS_TYPE, new String[] { "lourival" }, new String[] { "lourival" });

		// Salva o keystore
		File file = File.createTempFile("lourival", ".jks"); // Cria um arquivo para o keystore JKS
		FileOutputStream outputStream = new FileOutputStream(file);
		newKeystore.store(outputStream, "lourival".toCharArray()); // Armazena com a senha lourival
		outputStream.close();
		System.out.println("JKS: " + file.getAbsolutePath());

		// Para verificar o keystore
		System.out.println("keytool -list -alias lourival -keystore " + file.getAbsolutePath() + " -storepass lourival -storetype JKS");

		// Salva o certificado
		Certificate[] chain = keyStore.getCertificateChain("lourival"); // Pega o certificado da chave

		file = File.createTempFile("lourival", ".cer"); // Cria um arquivo para o certificado
		outputStream = new FileOutputStream(file);
		outputStream.write(KeyStoreUtils.toBase64(chain[0])); // Grava o certificado em base64
		outputStream.close();
		System.out.println("CER: " + file.getAbsolutePath());

		file = File.createTempFile("lourival", ".pem"); // Cria um arquivo para o certificado
		outputStream = new FileOutputStream(file);
		outputStream.write(chain[0].getEncoded()); // Grava o certificado em base64
		outputStream.close();
		System.out.println("CER(bin): " + file.getAbsolutePath());

		// Para verificar o certificado
		System.out.println("keytool -printcert -file " + file.getAbsolutePath());
	}

}
