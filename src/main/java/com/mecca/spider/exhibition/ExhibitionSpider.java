package com.mecca.spider.exhibition;

import com.mecca.spider.exhibition.mapper.ExhibitionMapper;
import com.mecca.spider.exhibition.parser.ExhibitionParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangaijun on 2018/4/12.
 */
public class ExhibitionSpider {

    public static void main(String[] args) {
        System.out.println("hello world");
        String url = "http://www.cnena.com/showroom/";
//        http://www.cnena.com/showroom/listall-htm-ordertype-.html 【最新发布的展会信息大全】
//        http://www.cnena.com/showroom/list_time.php?daytime=4&page=1  四月最新 分页
        url = "http://www.cnena.com/showroom/list_time.php?daytime=2";
//        url = "http://www.haozhanhui.com/zhanlanjihua/";
        String docStr = "";
        Document doc = getDocument(url);//Jsoup.parse(docStr);//

        Elements s = doc.getElementsByClass("gridtable");
        s.stream().forEach(a->{
            System.out.println(a.children());
        });

        Element oseexh1 = doc.getElementById("oseexh1");
        Elements ul = oseexh1.children();
        Element e = ul.get(0);
        ExhibitionParser parser = new ExhibitionParser();

        List ret = parser.parseElements(e,new ExhibitionMapper());
        ret.stream().forEach(System.out::println);


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
