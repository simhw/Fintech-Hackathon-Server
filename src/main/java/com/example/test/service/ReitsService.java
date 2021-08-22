package com.example.test.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.test.model.Reits;


@Service
public class ReitsService {
	public List<Reits> getReits() {

		String url = "https://www.paxnet.co.kr/stock/infoStock/themaView?schThemaCode=0310";
		List<Reits> reitsList = new ArrayList<Reits>();
		
		try {

			Document doc = Jsoup.connect(url).get();

			Element table = doc.select(".table-data > tbody").get(1);
			Elements trs = table.select("tr");

			Reits reits;
			for (int i = 0; i < trs.size(); i++) {
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

				reitsList.add(reits);
			}

			for (int i = 0; i < reitsList.size(); i++) {
				System.out.println(reitsList.get(i).toString());

			}

		}

		catch (Exception ex) {
			ex.getMessage();
		}
		return reitsList;

	}

}
