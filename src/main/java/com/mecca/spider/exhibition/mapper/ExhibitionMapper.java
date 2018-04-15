package com.mecca.spider.exhibition.mapper;

import com.mecca.spider.exhibition.ExhibitionSpider;
import com.mecca.spider.exhibition.Mapper;
import com.mecca.spider.exhibition.domain.UserDTO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public class ExhibitionMapper implements Mapper {
    private static Pattern lipath = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})&nbsp;(.*?)&nbsp;&nbsp;(.*?)&nbsp;");

    @Override
    public UserDTO mapper(Element element) {
        String html = element.html();
        Matcher m = lipath.matcher(html);
        UserDTO dto = null;
        if (m.find()) {
            dto = new UserDTO();
            dto.setName(element.children().attr("title"));
            dto.setTime(m.group(1));
            dto.setType(m.group(2));
            dto.setCity(m.group(3));
            dto.setUrl(element.children().attr("href"));
            System.out.println("init bean:"+dto.getUrl());
            parseDoc(dto);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return dto;
    }

    static Pattern organize = Pattern.compile("<strong>组织单位：</strong><a href=\"(.*?)\".*?>(.*?)</a>");

    public static UserDTO parseDoc(UserDTO dto){
        Document doc;
            doc = ExhibitionSpider.getDocument(dto.getUrl());
        if (doc==null){
            doc = ExhibitionSpider.getDocument(dto.getUrl());
        }
        if (doc==null){
            System.out.println("error init :"+dto.getUrl());
            return dto;
        }

        Elements s = doc.select(".exhinfo_center>ul>li");

        String venue = s.get(0).select("a").attr("title");
        String venueUrl = s.get(0).select("a").attr("href");
        String venueArea = s.get(1).select("strong").html().replaceAll("查看","");

        StringBuilder organization = new StringBuilder();
        StringBuilder organizationUrl = new StringBuilder();
        s.stream().forEach(a->{
            Matcher m = organize.matcher(a.html());
            if (m.find()){
                organization.append(m.group(2)).append("<br>");
                organizationUrl.append(m.group(1)).append("<br>");
            }
        });
        String content = doc.getElementById("exhdetail").html().replaceAll(htmlTag,"");;
        String contact = doc.getElementById("exhlinkinfo").parent().parent().child(1).html().replaceAll(htmlTag,"");;
        dto.initDetail(venue,venueUrl,venueArea,organization.toString(),organizationUrl.toString(),content,contact);
        return dto;
    }
}
