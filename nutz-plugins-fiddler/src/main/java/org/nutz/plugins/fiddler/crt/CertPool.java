package org.nutz.plugins.fiddler.crt;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import org.nutz.plugins.fiddler.server.HttpProxyServerConfig;

public class CertPool {

	private static Map<String, X509Certificate> certCache = new HashMap<>();

	public static X509Certificate getCert(String host, HttpProxyServerConfig serverConfig) throws Exception {
		X509Certificate cert = null;
		if (host != null) {
			String key = host.trim().toLowerCase();
			if (certCache.containsKey(key)) {
				return certCache.get(key);
			} else {
				cert = CertUtil.genCert(serverConfig.getIssuer(), serverConfig.getCaPriKey(), serverConfig.getCaNotBefore(), serverConfig.getCaNotAfter(), serverConfig.getServerPubKey(), key);
				certCache.put(key, cert);
			}
		}
		return cert;
	}
}
