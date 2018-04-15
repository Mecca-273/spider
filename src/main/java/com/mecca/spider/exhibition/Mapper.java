package com.mecca.spider.exhibition;

import org.jsoup.nodes.Element;

import java.util.regex.Pattern;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public interface Mapper {
    String htmlTag = "<[^>]*>|&nbsp;";
    Pattern p = Pattern.compile("(联\\s*系\\s*人.*?|电\\s*话|电话Tel|参展咨询).*?[：:]\\s*(?<name>[\\u4e00-\\u9fa5]*|[\\u4e00-\\u9fa5]*\\s*[\\u4e00-\\u9fa5]*|[\\u4e00-\\u9fa5]*.*?)(\\s*|\\s*手\\s*机)(?<mobile>\\d{11}|\\d{3,4}[-]?\\d{7,8})");
    <T> T mapper(Element e);
}
