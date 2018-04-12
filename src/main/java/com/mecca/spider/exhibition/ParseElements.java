package com.mecca.spider.exhibition;

import org.jsoup.nodes.Element;

import java.util.List;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public interface ParseElements<T> {
    List<T> parseElements(Element e,Mapper mapper);

}
