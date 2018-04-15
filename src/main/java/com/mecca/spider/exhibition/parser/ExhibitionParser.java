package com.mecca.spider.exhibition.parser;

import com.mecca.spider.exhibition.Mapper;
import com.mecca.spider.exhibition.ParseElements;
import com.mecca.spider.exhibition.domain.UserDTO;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public class ExhibitionParser implements ParseElements<UserDTO> {

    @Override
    public List parseElements(Elements ul, Mapper mapper) {
        Element e = ul.get(0);
        List datas = e.children().stream().map(li -> mapper.mapper(li))
                .filter(a -> a != null).collect(Collectors.toList());
        return datas;
    }

}
