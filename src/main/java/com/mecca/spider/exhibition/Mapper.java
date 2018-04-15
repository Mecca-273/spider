package com.mecca.spider.exhibition;

import org.jsoup.nodes.Element;

import java.util.regex.Pattern;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public interface Mapper {
    String htmlTag = "<[^>]*>|&nbsp;";
    <T> T mapper(Element e);
}
