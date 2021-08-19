package com.example.test.practice;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CrawlingTest {

	public static void main(String[] args) throws Exception {

		String city = "마포구 공덕동";

		// 네이버 부동산 지역 정보 URL
		String url = "https://m.land.naver.com/search/result/" + city; 

		try {
			Document doc = Jsoup.connect(url).get();
			Element script = doc.select("script").get(3);
			// filter 내 지역 정보 추출
			Pattern pattern = Pattern.compile("filter: \\{\\n\\s+(.*\\s+.*\\s+.*\\s+.*\\s+.*\\s+.*\\s+.*)");
			Matcher matcher = pattern.matcher(script.html());

			String str = "";
			while (matcher.find()) {
				str = matcher.group(1);
			}

			String lat = ""; // 위도
			String lon = ""; // 경도
			String z = ""; // 확대
			String cortarNo = ""; // 지역번호
//			String cortarNm = ""; // 지역이름
			String rletTpCds = "OPST"; // 아파트(APT), 빌라(BL), 오피스텔(OPST)
			String tradTpCds = "A1:B1:B2"; // 건물유형 매매, 전세, 월세 

			// lat
			pattern = Pattern.compile("lat: \\'(.*)\\'");
			matcher = pattern.matcher(str);
			while (matcher.find()) {
				lat = matcher.group(1);
			}

			// lon
			pattern = Pattern.compile("lon: \\'(.*)\\'");
			matcher = pattern.matcher(str);
			while (matcher.find()) {
				lon = matcher.group(1);
			}

			// z
			pattern = Pattern.compile("z: \\'(.*)\\'");
			matcher = pattern.matcher(str);
			while (matcher.find()) {
				z = matcher.group(1);
			}

			// cortarNo
			pattern = Pattern.compile("cortarNo: \\'(.*)\\'");
			matcher = pattern.matcher(str);
			while (matcher.find()) {
				cortarNo = matcher.group(1);
			}

			// 네이버 부동산 단지 정보 URL
			url = "https://m.land.naver.com/cluster/clusterList?";
			doc = Jsoup.connect(url).data("cortarNo", cortarNo).data("rletTpCds", rletTpCds)
					.data("tradTpCds", tradTpCds)
					.data("z", z).data("lat", lat)
					.data("lon", lon)
					.data("addon", "COMPLEX")
					.data("bAddon", "COMPLEX")
					.ignoreContentType(true) // JOSN 값으로 받아온다.
					.get();

			// 단지 정보 (count, lgeo, lat, lon)
			String body = doc.select("body").text();
			JSONObject area = new JSONObject(body);
			area = area.getJSONObject("data");
			JSONArray complex = area.getJSONArray("COMPLEX");

			for (int i = 0; i < complex.length(); i++) {

				// count/ lon/ lat/ lgeo
				JSONObject items = complex.getJSONObject(i);
				// 단지 내 매물 정보 URL
				url = null;
				url = "https://m.land.naver.com/cluster/ajax/complexList?";
				
				// 단지 내 매물 개수 
				int cnt = Integer.parseInt(items.getString("count"));
				// 단지 내 매물 정보가 20개가 넘을 시 다음 페이지에 출력해준다. 
				int iPage = (int) Math.ceil(Integer.parseInt(items.getString("count")) / 20.0);
				System.out.println("cnt : " + cnt + " iPage : " + iPage);
				
				for (int j = 0; j < iPage; j++) {

					doc = Jsoup.connect(url)
							.data("itemId", items.getString("lgeo"))
							.data("lgeo", items.getString("lgeo"))
							.data("rletTpCd", rletTpCds)
							.data("tradTpCd", tradTpCds)
							.data("z", z)
							.data("lat", items.getString("lat"))
							.data("lon", items.getString("lon")).data("cortarNo", cortarNo)
							.data("isOnlyIsale", "false")
							.data("sort", "readRank")
							.data("page", iPage+ "")
							.ignoreContentType(true) // JOSN 값으로 받아온다.
							.get();

					body = doc.select("body").text();
					System.out.println(body);
					
				}
			}

		} catch (IOException ex) {
			ex.getMessage();
		}
	}
}
