package com.virgil.http;

import javax.naming.Context;

import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * Created by liuwujing on 15/3/16.
 */
public class Test2 {
    public static void main(String[] args){

    }
    public static void a(String paramString, Context paramContext)
    {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
        HttpClientParams.setRedirecting(localBasicHttpParams, true);
//        SSLSessionCache localSSLSessionCache = paramContext == null ? null : new SSLSessionCache(paramContext);
        HttpProtocolParams.setUserAgent(localBasicHttpParams, paramString);
        SchemeRegistry localSchemeRegistry = new SchemeRegistry();
        localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
//        localSchemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(30000, localSSLSessionCache), 443));
        ThreadSafeClientConnManager localThreadSafeClientConnManager = new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry);
        ConnManagerParams.setTimeout(localBasicHttpParams, 60000L);
        ConnPerRouteBean localConnPerRouteBean = new ConnPerRouteBean(10);
        ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, localConnPerRouteBean);
        ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 50);

    }
}
