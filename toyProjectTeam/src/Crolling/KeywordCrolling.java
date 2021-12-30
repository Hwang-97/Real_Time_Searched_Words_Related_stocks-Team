package Crolling;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class KeywordCrolling {

	private static String issunowUrl = "http://rank.ezme.net/";
	private static Elements body;
	public static List<String> keyword = new ArrayList<String>();

	public static void crolling() {
		try {
			body = Jsoup.connect(issunowUrl).get().body().select("div.mdl-card__actions b");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] keywordArr = body.toString().replaceAll("[< > b /]","").split("\n");
		for(int i=0 ; i<keywordArr.length;i++ ) {
			keyword.add(keywordArr[i]);
		}  
	}      
}          
           
           
           