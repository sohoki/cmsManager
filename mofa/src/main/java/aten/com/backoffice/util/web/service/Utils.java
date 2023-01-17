package aten.com.backoffice.util.web.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {
	
	/**
	 * NVL 함수
	 * 
	 * @param o
	 * @return
	 */
	public static String nvl(Object o) {
		return o == null ? "" : o.toString();
	}
	
	/**
	 * NVL 함수
	 * 
	 * @param o
	 * @param def
	 * @return
	 */
	public static String nvl(Object o, String def) {
		return o == null ? def : o.toString();
	}
	
	/**
	 * NVL 함수
	 * 
	 * @param s
	 * @return
	 */
	public static String nvl(String s) {
		return nvl(s, "");
	}
	
	/**
	 * NVL 함수
	 * 
	 * @param s
	 * @param def
	 * @return
	 */
	public static String nvl(String s, String def) {
		return s == null ? def : s;
	}
	
	public static String timeFormat(String s) {
		return (s.length() == 4) ? s.substring(0, 2) + ":" + s.substring(2) : s;
	}
	
	public static String timeFormat(Object o) {
		String s = Utils.nvl(o);
		if (s.length() == 4) {
			return s.substring(0, 2) + ":" + s.substring(2);
		} else {
			return s;
		}
	}
	
	public static String dateFormat(String s) {
		return (s.length() == 8) ? s.substring(0, 4) +"."+ s.substring(4, 6) +"."+ s.substring(6, 8) : s;
	}
	
	public static String substring(String s, int n) {
		if (s.length() < n) {
			return s;
		} else {
			return s.substring(0, n);
		}
	}
	
	public static JSONObject jsonObject(String jstr) throws ParseException {
		return (JSONObject)new JSONParser().parse(jstr);
	}
	
	/**
	 * POST 전송
	 * 
	 * @param connUrl
	 * @param headers
	 * @param sendData
	 * @return
	 */
	public static String postSend(String connUrl, Map<String, String> headers, String sendData) throws Exception {
		StringBuffer response = new StringBuffer();
		
		OutputStreamWriter wr = null;
		BufferedReader in = null;
		try {
			URL url = new URL(connUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			for (Entry<String, String> elem : headers.entrySet()) {
				conn.setRequestProperty(elem.getKey(), elem.getValue());
			}
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream() );
			wr.write(sendData);
			wr.flush();
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()  ));
			String 	inputLine = "";	
	        while ((inputLine = in.readLine()) != null) {
	        	response.append(inputLine);
	        }
		} catch(Exception e) {
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ie) {}
			try {
				if (wr != null) {
					wr.close();
				}
			} catch (IOException ie) {}
		}
		
		return response.toString();
	}

	public static String getPrintStackTrace(Throwable e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(out));
		return out.toString();
	}
}
