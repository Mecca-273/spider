package com.mecca.spider.exhibition.mapper;

import com.mecca.spider.exhibition.Mapper;
import com.mecca.spider.exhibition.domain.UserDTO;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public class ExhibitionMapper implements Mapper {
    private static Pattern lipath = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})&nbsp;(.*?)&nbsp;&nbsp;(.*?)&nbsp;");

    @Override
    public UserDTO mapper(Element e) {
        String s = e.html();
        Matcher m = lipath.matcher(s);
        UserDTO a = null;
        if (m.find()) {
            a = new UserDTO();
            a.setName(e.children().attr("title"));
            a.setTime(m.group(1));
            a.setType(m.group(2));
            a.setCity(m.group(3));
            a.setUrl(e.children().attr("href"));

        }
        return a;
    }
}
