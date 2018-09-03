package com.github.gongfuboy.crawler.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouLiMing on 2018/9/3.
 */
public class FileAnalyseUtils {

    /**
     * 获取当前Repository是否存在配置文件
     * @param repositoriesFilePath
     * @return
     */
    public static List<File> analyseFile(String repositoriesFilePath) {
        List<File> result = new ArrayList<>();
        File file = new File(repositoriesFilePath);
        if (!file.exists()) {
            return result;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            for (File sourceFile : childFile) {
                List<File> files = analyseFile(sourceFile.getAbsolutePath());
                result.addAll(files);
            }
        }
        if (isUsefulFile(file)) {
            result.add(file);
        }
        return result;
    }

    /**
     * 判断当前文件是否一个可用的文件
     * @param file
     * @return
     */
    public static boolean isUsefulFile(File file){
        FileInputStream fileInputStream = null;
        if (!isPropertyFile(file)) {
            return false;
        }
        try {
            fileInputStream = new FileInputStream(file);
            List<String> strings = IOUtils.readLines(fileInputStream);
            for (String tempString : strings) {
                return tempString.contains("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
        return false;
    }

    /**
     * 判断一个文件是否为一个property文件
     * @param file
     * @return
     */
    public static boolean isPropertyFile(File file) {
        String name = file.getName();
        if (name.endsWith(".properties")) {
            return true;
        } else {
            return false;
        }
    }

}
