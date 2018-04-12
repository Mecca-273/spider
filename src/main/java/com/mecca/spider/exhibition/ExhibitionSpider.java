package com.mecca.spider.exhibition;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by zhangaijun on 2018/4/12.
 */
public class ExhibitionSpider {
    public static void main(String[] args) {
        System.out.println("hello world");
        String url = "http://www.cnena.com/showroom/";
//        url = "http://www.haozhanhui.com/zhanlanjihua/";
        Document doc = getDocument(url);
        System.out.println(doc.body());

    }

    private static Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(5000).userAgent("Mozilla").get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }
}
