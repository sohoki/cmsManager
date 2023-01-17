package aten.com.backoffice.util.service.impl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import aten.com.backoffice.util.service.proxyInfo;

public class proxInfoImpl implements proxyInfo{

	@Override
	public String proxy(HttpServletRequest request, String url_address) throws Exception {
		// TODO Auto-generated method stub
		String result = null;
		
		try{
			//다운 받을 주소
			URL url = new URL(url_address);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while(true){
				String line = br.readLine();
				if (line == null){
					break;
				}
				sb.append(line);
			}
			result = sb.toString();
			br.close();
			con.disconnect();
		}catch(Exception e){
			System.out.println("" + e.getMessage());
		}
		
		return result;
	}
	
}
