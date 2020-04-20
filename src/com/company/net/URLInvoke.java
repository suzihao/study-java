package com.company.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLInvoke {

    public String JsonPostInvoke(String url) {
        String returnLine = "";
        try {
            URL my_url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) my_url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                returnLine += line;
            }
            reader.close();
            connection.disconnect();
            System.out.println("results:"+returnLine);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnLine;
    }

        public static void main(String[] args) {
        try {
            URLInvoke uRLInvoke = new URLInvoke();

            String result = uRLInvoke.JsonPostInvoke("http://www.nmggyy.com/api/headerV3.jspx");
//            JSONObject jsonObject = JSON.parseObject(aaa);
            System.out.print(result);

        }catch (Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());

        }
    }
}
