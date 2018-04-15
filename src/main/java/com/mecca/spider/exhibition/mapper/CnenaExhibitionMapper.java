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
public class CnenaExhibitionMapper implements Mapper {

    private final static String URL_PRE = "http://www.cnena.com/showroom/";
    private final static Pattern page = Pattern.compile("red\">(\\d+)</font>/(\\d+)/(\\d+)");


    @Override
    public UserDTO mapper(Element element) {
        Elements tds = element.children().select("tbody>tr>td");
        UserDTO dto = null;
        String nextUrl;
        String lastUrl;
        if (tds.size()>2){
            dto = new UserDTO();
            dto.setLogo(tds.get(0).select("a>img").attr("src"));
            dto.setName(tds.get(1).select("a").attr("title"));
            dto.setTime(tds.get(2).select("center").html().replaceAll("&nbsp;",""));
//        dto.setType();
            dto.setUrl(URL_PRE+tds.get(1).select("a").attr("href"));
//        dto.setCity(null);
            parseDoc(dto);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else{
//            若当前页面等于尾页页码则停止
            Elements pages = element.select(".page>[title=下一页]");
            if (pages.size()==1){
                nextUrl = pages.get(0).attr("href");
                String pageStr = element.select("a[href=#]").html();
                Matcher m = page.matcher(pageStr);
                if (m.find()){
                    String cur = m.group(1);
                    String last = m.group(2);
                    if (!cur.equals(last)){
                        dto = new UserDTO(URL_PRE+nextUrl);
                    }
                }
            }
        }
        return dto;
    }

    static Pattern orgReg = Pattern.compile("(.*?)<a");
    public static UserDTO parseDoc(UserDTO dto){
        Document doc = ExhibitionSpider.getDocument(dto.getUrl());
        if (doc==null){
            doc = ExhibitionSpider.getDocument(dto.getUrl());
        }
        if (doc==null){
            System.out.println("error init :"+dto.getUrl());
            return dto;
        }
        Elements sub = doc.select(".area>.area-sub>.sub-info-2");
        Element subInfoArea = sub.get(0);
        Element subInfo = subInfoArea.child(1);
        String venueUrl = subInfo.select("a").get(0).attr("href");
        String venue = subInfo.select("a").get(0).html();
        String organization = sub.get(1).select("p").html();
        Matcher m = orgReg.matcher(organization);
        if (m.find()){
            organization =m.group(1);
        }
        String contact = sub.get(2).html().replaceAll(htmlTag,"");
        String content = doc.select(".area-main").html().replace(htmlTag,"");
        dto.initDetail(venue,venueUrl,null,organization,null,content,contact);
        return dto;
    }

}
