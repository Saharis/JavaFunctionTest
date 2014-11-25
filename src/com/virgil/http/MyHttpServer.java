package com.virgil.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import com.virgil.util.StringUtil;

public class MyHttpServer {
	public static void httpserverService() throws IOException {
		HttpServerProvider provider = HttpServerProvider.provider();
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(8002), 100);// ����˿�,��ͬʱ��

		httpserver.createContext("/", new MyHttpHandler());
		httpserver.setExecutor(null);
		
		httpserver.start();
		System.out.println("server started");
	}

	// Http��������
	static class MyHttpHandler implements HttpHandler {
		public void handle(HttpExchange httpExchange) throws IOException {
			String responseMsg = processHttprseponse(httpExchange.getRequestURI()); // ��Ӧ��Ϣ
			InputStream in = httpExchange.getRequestBody(); // ���������
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String temp = null;
			if ((temp = reader.readLine()) != null) {
				System.out.println("client request:" + temp);
			}
			byte[] msgBytes=responseMsg.getBytes();
			httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
			httpExchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
			httpExchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type, Accept");
			httpExchange.getResponseHeaders().set("Access-Control-Max-Age", "1728000");
			httpExchange.sendResponseHeaders(200, msgBytes.length); // ������Ӧͷ���Լ���Ӧ��Ϣ�ĳ���

			OutputStream out = httpExchange.getResponseBody(); // ��������
			out.write(msgBytes,0,msgBytes.length);
			out.flush();
			httpExchange.close();

		}
	}

	public static void main(String[] args) throws IOException {
		httpserverService();
	}

	public static String getQueryParamByattr(String query, String attr) {
		String spiltSym = "=";
		String param = null;
		String[] result = query.split(spiltSym);
		if (result != null && result.length >= 2) {
			if (attr.equalsIgnoreCase(result[0])) {
				param = result[1];
			}
		}
		return param;
	}

	public static String processHttprseponse(URI uri) {

		String path = uri.getPath();
		String uid = getQueryParamByattr(uri.getQuery(), "groupid");
		StringBuilder content = new StringBuilder();
		if (!StringUtil.emptyOrNull(path)) {
			path = path.replace("/", ".");
			if (path.startsWith(".")) {
				path = path.substring(1);
			}

			try {
				Connection conn = DBUtilsHelper.getConnection();
				PreparedStatement ps;
				String sql = "select user_name from user_tb where group_id=\'"+uid+"\'";
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String value=rs.getString("user_name");
					if(!StringUtil.emptyOrNull(value)){
						content.append(value + "\n");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return content.toString();
	}

	public static void querydataFromDB() {

	}
}
