package com.mecca.spider.exhibition;

import com.mecca.spider.exhibition.domain.UserDTO;
import com.mecca.spider.exhibition.mapper.CnenaExhibitionMapper;
import com.mecca.spider.exhibition.mapper.ExhibitionMapper;
import com.mecca.spider.exhibition.parser.CnenaExhibitionParser;
import com.mecca.spider.exhibition.parser.ExhibitionParser;
import com.mecca.spider.utils.ExportBeanExcel;
import com.mecca.spider.utils.FileWriteReadUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangaijun on 2018/4/12.
 */
public class ExhibitionSpider {
    private final static String FILE_PATH = "/Users/Mecca/Documents/work/mine/Git/spider/src/main/resources/";
    private final static String FILE_NAME = "haozhanhui.txt";
    private final static String FILE_NAME2 = "cnena.txt";
    private final static String FILE_NAME_XLS = "HZH.xls";
    private final static String FILE_NAME_XLS2 = "cnena";
    public static void spider() {
        String url = "http://www.haozhanhui.com/zhanlanjihua/";
        List ret = new ArrayList<>();
//        spiderHZH(url);
        int[] daytime = {4,5,6,7,8,9,10,11,12};
        final String cnenaurl= "http://www.cnena.com/showroom/list_time.php?daytime=";
//        http://www.cnena.com/showroom/listall-htm-ordertype-.html 【最新发布的展会信息大全】
//        http://www.cnena.com/showroom/list_time.php?daytime=4&page=1  四月最新 分页
        Arrays.stream(daytime).forEach(time->{
            spiderCnena(cnenaurl,time);
        });

    }

    private static void exportExcel(List<UserDTO> userDTOList,String fileName){
        List<String> listName = new ArrayList<>();
        listName.add("展会时间");
        listName.add("场馆");
        listName.add("城市");
        listName.add("类型");
        listName.add("展会名称");
        listName.add("展会链接");
        listName.add("展会面积");
        listName.add("展商数量");
        listName.add("观众数量");
        listName.add("详情");
        listName.add("组织单位");
        listName.add("组织单位url");
        listName.add("地址");
        listName.add("电话");
        listName.add("联系方式1");
        listName.add("logo");
        List<String> listId = new ArrayList<>();

        listId.add("time");
        listId.add("venue");
        listId.add("city");
        listId.add("type");
        listId.add("name");
        listId.add("url");
        listId.add("area");
        listId.add("num");
        listId.add("visitorNum");
        listId.add("content");
        listId.add("organization");
        listId.add("organizationUrl");
        listId.add("address");
        listId.add("telephone");
        listId.add("contact");
        listId.add("logo");
        ExportBeanExcel<UserDTO> exportBeanExcelUtil = new ExportBeanExcel();
        exportBeanExcelUtil.exportExcel(FILE_PATH+fileName,"展会信息",listName,listId,userDTOList);

    }

    private static List spiderCnena(String url,int time){
        CnenaExhibitionParser par = new CnenaExhibitionParser();
        List<UserDTO> ret = new ArrayList<>();
        List<UserDTO> retAll = new ArrayList<>();
        Document doc ;
        int iterator = 0;
        boolean first = true;
        while (first||par.queueLenth()>0) {
            url = first ? url+time : par.popUrl();
            doc = getDocument(url);
            System.out.println(time+"月,第"+(++iterator)+"页 url= "+url);
            if (doc!=null){
                Elements s = doc.select(".srpnel>.gridtable");
                ret = par.parseElements(s, new CnenaExhibitionMapper());
                retAll.addAll(ret);
                ret.stream().forEach(dto->{
                    FileWriteReadUtils.writeFile(FILE_PATH+FILE_NAME2,dto.toString(),true);
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                first = false;
            }else {
                System.out.println("错误:url= "+url);
            }

        }
        exportExcel(retAll,FILE_NAME_XLS2+time+".xls");
        return ret;
    }

    private static List spiderHZH(String url){
        Document doc = getDocument(url);
        List ret = new ArrayList<>();
        ret.addAll(parseList(doc,"oseexh1"));

        ret.addAll(parseList(doc,"oseexh2"));
//        ret.addAll(parseList(doc,"oseexh3"));
        ret.stream().forEach(dto->{
            FileWriteReadUtils.writeFile(FILE_PATH+FILE_NAME,dto.toString(),true);
        });
        System.out.println("开始导出 excel");
        exportExcel(ret,FILE_NAME_XLS);
        return ret;
    }

    private static List parseList(Document doc,String tab){
        Element oseexh1 = doc.getElementById(tab);
        Elements ul = oseexh1.children();

        ExhibitionParser parser = new ExhibitionParser();
        List ret = parser.parseElements(ul,new ExhibitionMapper());
        return ret;
    }

    public static Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(5000).userAgent("Mozilla").get();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }

    private static void writeToFile(){

    }

}
