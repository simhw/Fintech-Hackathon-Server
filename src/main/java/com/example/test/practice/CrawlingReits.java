package com.example.test.practice;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.test.model.Reits;

public class CrawlingReits {

	
	public static void main(String[] args) {
		String url = "https://www.paxnet.co.kr/stock/infoStock/themaView?schThemaCode=0310";
		
		try {
		
			JSONArray reitsList = new JSONArray();
			Document doc = Jsoup.connect(url).get();
			
			Element table = doc.select(".table-data > tbody").get(1);
			Elements trs = table.select("tr");
			
			
			Reits reits;
			for(int i = 0; i < trs.size(); i++) {
				reits = new Reits();
				// 종목명  
				Element tr = trs.get(i);
				Elements a = tr.select("td > strong > a");
				reits.setType(a.text());
				
				Elements tds = tr.select(".a-right");
				String price = tds.get(0).text();
				String pcr = tds.get(1).text();
				String pcp = tds.get(2).text();
				String volume = tds.get(3).text();
				
				reits.setPrice(price);
				reits.setPcr(pcr);
				reits.setPcp(pcp);
				reits.setVolume(volume);
				
				reitsList.put(reits);
			}
			
			for(int i = 0; i < reitsList.length(); i++) {
				System.out.println(reitsList.get(i).toString());
			
			}	
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}

}
