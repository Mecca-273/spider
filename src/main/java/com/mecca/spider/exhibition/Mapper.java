package com.mecca.spider.exhibition;

import org.jsoup.nodes.Element;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public interface Mapper {
    <T> T mapper(Element e);
}
