package com.mecca.spider.exhibition.parser;

import com.mecca.spider.exhibition.Mapper;
import com.mecca.spider.exhibition.ParseElements;
import com.mecca.spider.exhibition.domain.UserDTO;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/13.
 */
public class CnenaExhibitionParser implements ParseElements<UserDTO> {
    private static Queue<String> urlQueue = new LinkedBlockingQueue<String>();

    @Override
    public List parseElements(Elements e, Mapper mapper) {
        Element srpnel = e.parents().get(0);
        List datas = srpnel.children().stream().map(li -> {
            UserDTO u = mapper.mapper(li);
            if (u!=null && u.getNextPageUrl()!=null){
                urlQueue.add(u.getNextPageUrl());
                return null;
            }else{
                return u;
            }
        })
                .filter(a -> a != null).collect(Collectors.toList());
        return datas;
    }

    public String popUrl(){
        return urlQueue.remove();
    }

    public boolean pushUrl(String url){
        boolean flag = true;
        try {
            urlQueue.add(url);
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;

    }
    public int queueLenth(){
        return urlQueue.size();
    }
}
