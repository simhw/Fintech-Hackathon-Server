package com.example.test.practice;
/* Java 샘플 코드 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class ApiExplorerController {

	public String api() throws IOException, JSONException {
		
		
		int i = 1;
		int j = 100;
		
		//http://openapi.seoul.go.kr:8088/sample/json/landActualPriceInfo/1/5///2021
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");	
		urlBuilder.append("/5676526b6573696d38336e427a7748");	//KEY
        urlBuilder.append("/json");	//TYPE
        urlBuilder.append("/houseRentPriceInfo");//SERVICE
        urlBuilder.append("/"+i);	//START_INDEX
        urlBuilder.append("/"+j);	//END_INDEX
        urlBuilder.append("///2021");	//ACC_YEAR

        URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader bufferReader;
		
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			bufferReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} 
		else {
			bufferReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		String line;
		while ((line = bufferReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		
		fromJSONItems(stringBuilder.toString());
		
		bufferReader.close();
		conn.disconnect();
		
		return "/index";
        
	}
	
	public void fromJSONItems(String result) throws JSONException{
		
		JSONObject listJson  = new JSONObject(result);
		JSONArray row = listJson.getJSONArray("row");
		
		for(int i = 0; i < row.length(); i++) {
			JSONObject itemJson = (JSONObject)row.get(i);
			System.out.println(itemJson);
		}

	}
	     
}