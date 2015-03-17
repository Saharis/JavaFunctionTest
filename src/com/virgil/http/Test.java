package com.virgil.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static final int SEND_TIMES = 1;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < SEND_TIMES; i++) {
			Runnable run = new Runnable() {
				public void run() {
					try {
						startWork();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		exec.shutdown();
	}

	public static void startWork() throws IOException {
		URL url = new URL("http://localhost:5389/testlink-1.9.3/lib/results/resultsGeneral.php?format=0&tplan_id=26621");
		
		InputStream input=new BufferedInputStream(new FileInputStream("com/virgil/http/cookie.properties"));
		Properties properties=new Properties();
		properties.load(input);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setDoOutput(true);
		urlConn.setDoInput(true);
		urlConn.setRequestMethod("GET");
		urlConn.setRequestProperty("Cookie", "");
		String teststr = "test message from virgil";
		OutputStream out = urlConn.getOutputStream();
		out.write(teststr.getBytes());
		out.flush();
		if (urlConn.getContentLength() != -1) {
			if (urlConn.getResponseCode() == 200) {
				InputStream in = urlConn.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String temp = "";
				StringBuilder sb = new StringBuilder();
				while ((temp = reader.readLine()) != null) {
					sb.append(temp);
				}
				System.out.println(sb.toString());
				reader.close();
				in.close();
				urlConn.disconnect();
			}
		}
	}

	public static void loginBaidu() {
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		try {
			url = new URL("http://localhost:5389/testlink-1.9.3/login.php");
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setRequestProperty("User-Agent", "Internet Explorer");
			httpurlconnection.setRequestProperty("Host", "localhost:5389/testlink-1.9.3/login.php");
			httpurlconnection.connect();

			String cookie0 = httpurlconnection.getHeaderField("Set-Cookie");

			System.out.println(cookie0);// 打印出cookie
			httpurlconnection.disconnect();
			// String cookie0 =
			// "BAIDUID=8AF5EA24DBF1275CE15C02B5FF65A265:FG=1;BDSTAT=61a1d3a7118ce8a7ce1b9d16fdfaaf51f3deb48f8e5494eef01f3a292cf5b899;
			// BDUSE=deleted";
			url = new URL("http://localhost:5389/testlink-1.9.3/login.php");
			String strPost = "username=liuwj&password=123456&mem_pass=on";
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setFollowRedirects(true);
			httpurlconnection.setInstanceFollowRedirects(true);
			httpurlconnection.setDoOutput(true); // 需要向服务器写数据
			httpurlconnection.setDoInput(true); //
			httpurlconnection.setUseCaches(false); // 获得服务器新信息
			httpurlconnection.setAllowUserInteraction(false);
			httpurlconnection.setRequestMethod("POST");
			httpurlconnection.addRequestProperty("Accept", "image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/x-silverlight, */*");
			httpurlconnection.setRequestProperty("Referer", "http://passport.baidu.com/?login&tpl=mn&u=http%3A//www.baidu.com/");
			httpurlconnection.setRequestProperty("Accept-Language", "zh-cn");
			httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpurlconnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpurlconnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Foxy/1; .NET CLR 2.0.50727;MEGAUPLOAD 1.0)");
			httpurlconnection.setRequestProperty("Host", "passport.baidu.com");
			httpurlconnection.setRequestProperty("Content-Length", strPost.length() + "");
			httpurlconnection.setRequestProperty("Connection", "Keep-Alive");
			httpurlconnection.setRequestProperty("Cache-Control", "no-cache");
			httpurlconnection.setRequestProperty("Cookie", cookie0);
			httpurlconnection.getOutputStream().write(strPost.getBytes());
			httpurlconnection.getOutputStream().flush();
			httpurlconnection.getOutputStream().close();
			httpurlconnection.connect();
			int code = httpurlconnection.getResponseCode();
			System.out.println("code   " + code);
			String cookie1 = httpurlconnection.getHeaderField("Set-Cookie");
			System.out.print(cookie0 + "; " + cookie1);
			httpurlconnection.disconnect();
			url = new URL("http://www.baidu.com/");
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setRequestProperty("User-Agent", "Internet Explorer");
			httpurlconnection.setRequestProperty("Host", "www.baidu.com");
			httpurlconnection.setRequestProperty("Cookie", cookie0 + "; " + cookie1);
			httpurlconnection.connect();
			InputStream urlStream = httpurlconnection.getInputStream();
			BufferedInputStream buff = new BufferedInputStream(urlStream);
			Reader r = new InputStreamReader(buff, "gbk");
			BufferedReader br = new BufferedReader(r);
			StringBuffer strHtml = new StringBuffer("");
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				strHtml.append(strLine + "\r\n");
			}
			System.out.print(strHtml.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
	}
}
