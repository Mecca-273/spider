package com.mecca.spider.utils;

import java.io.*;

/**
 * @author Created by Aijun Zhang
 * @since 18/4/14.
 */
public class FileWriteReadUtils {
    public static void main(String[] args) {
        writeFile("/Users/Mecca/Documents/work/mine/Git/spider/src/main/resources/test","asdfasd\n",true);
        writeFile("/Users/Mecca/Documents/work/mine/Git/spider/src/main/resources/test","哦呀哦",true);
    }
    public static void writeFile(String saveFilename,String data,boolean append) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(saveFilename,append), "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.write(data);
        out.close();
    }
}
