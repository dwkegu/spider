package com.dwkegu.spiderlib;



import com.dwkegu.spiderlib.BaseFrame.CrawlerBase;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;


public class NYTimes {
    private File myFile;
    public static final String fileDir = "f:/tmp/nytimes/";
    private List<File> allFile;
    JsonParser parser;
    OkHttpClient client;
    FileSaver mSaver;
    int counter;
    NYTimes(){
        counter = 0;
        myFile = new File(fileDir);
        allFile = new ArrayList<>();
        Collections.addAll(allFile, Objects.requireNonNull(myFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".json");
            }
        })));
        parser = new JsonParser();
        client = new OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",1080)))
                .build();
        mSaver = new FileSaver();
    }

    public void start(){
        parseFiles();
    }

    public void parseFiles(){
        for(File item:allFile){
            parseFile(item);
        }
    }

    public void parseFile(File item){
        try {
            FileInputStream fin = new FileInputStream(item);
            byte[] buffer = new byte[fin.available()];
            int count = fin.read(buffer,0,fin.available());
            if(count!=item.length()){
                FileLogger.d("NYTimes","read error in file"+item.getName());
                System.out.println("count:"+String.valueOf(count)+"file length:"+String.valueOf(item.length()));
            }
            String s = new String(buffer, Charset.forName("UTF-8"));
            JsonObject body = parser.parse(s).getAsJsonObject();
            JsonObject response = body.get("response").getAsJsonObject();
            JsonArray docs = response.get("docs").getAsJsonArray();
            for(JsonElement doc:docs){
                JsonObject _doc = doc.getAsJsonObject();
                String documentType = _doc.get("document_type").getAsString();
                if(!documentType.equals("article"))
                    continue;
                String url = _doc.get("web_url").getAsString();
                String[] spiltS = url.split("/");
                String category = null;
                String fileName = null;
                if(spiltS.length>7){
                    StringBuilder sb = new StringBuilder();
                    for(int i = 6;i < spiltS.length-1;i++){
                        sb.append(spiltS[i]).append("/");
                    }
                    category = sb.toString();
                    fileName = spiltS[spiltS.length-1].replace(".html",".txt");
                }
                String content = parseArticle(url);
                if(content==null||category==null){
                    continue;
                }
                counter++;
                System.out.println(counter);
                mSaver.save(category,fileName,content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseArticle(String url){
        try {
            String webPage=null;
            for(int i = 0; i < 5; i++){
                webPage = get(url);
                if(webPage!=null){
                    break;
                }
            }
            if(webPage==null){
                return null;
            }
            Document document = Jsoup.parse(webPage);
            Element body = document.body();
            Elements paras = body.getElementsByClass("story-body-text story-content");
            StringBuilder stringBuilder = new StringBuilder();
            for(Element para:paras){
                stringBuilder.append(para.text());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                .build();
        System.out.println(url);
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return null;
        }
    }
}
