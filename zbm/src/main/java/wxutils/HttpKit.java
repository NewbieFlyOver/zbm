package wxutils;

import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class HttpKit {
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private static final int CONNECT_TIME_OUT = 5000; //链接超时时间3秒

	private static SSLContext wx_ssl_context = null; //微信支付ssl证书
	
	static{
		
		Resource resource = new ClassPathResource("apiclient_cert.p12"); //获取微信证书 或者直接从文件流读取
		char[] keyStorePassword = ConfigUtil.getProperty("wx.mchid").toCharArray(); //证书密码
		try {
			KeyStore keystore = KeyStore.getInstance("PKCS12");
			keystore.load(resource.getInputStream(), keyStorePassword);
			KeyManagerFactory keyManagerFactory = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(keystore, keyStorePassword);
			SSLContext wx_ssl_context = SSLContext.getInstance("TLS");
			wx_ssl_context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}