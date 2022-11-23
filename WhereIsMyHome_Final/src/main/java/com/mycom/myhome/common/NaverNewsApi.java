package com.mycom.myhome.common;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
/**
 * Created by rucrazia on 2017-08-01.
 */
public class NaverNewsApi {
// https://developers.naver.com/docs/search/news/


    public static StringBuffer getNews() {
        String clientId = "KKpRZ5ljCrkz0BQ6aSeR";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "o7PxjvjCVs";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode("부동산", "UTF-8"); //검색어";
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+ text + "&display=2&start=1&sort=date"; // 뉴스의 json 결과
       //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // 블로그의 xml 결과 
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            return response;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}