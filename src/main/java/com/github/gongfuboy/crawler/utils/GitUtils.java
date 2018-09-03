package com.github.gongfuboy.crawler.utils;

import org.eclipse.jgit.api.Git;

import java.io.File;

/**
 * Created by ZhouLiMing on 2018/9/3.
 */
public class GitUtils {

    public static void main(String[] args) throws Exception {
//        cloneRepositories("https://github.com/GongFuBoy/gongfuboy-uitls.git");
    }

    /**
     * clone指定Repositories
     * @param url
     * @param filePath
     * @throws Exception
     */
    public static boolean cloneRepositories(String url, String filePath) {
        boolean result = true;
        Git call = null;
        try {
            call = Git.cloneRepository().setURI(url).setDirectory(new File(filePath)).call();
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            call.close();
        }
        return result;
    }

}
