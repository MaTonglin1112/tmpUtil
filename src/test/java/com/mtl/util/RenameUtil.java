package com.mtl.util;

import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RenameUtil {
    HashSet<String> strings = new HashSet<>();

    @Test
    public void testTmp() throws Exception {
        File file = new File("D:\\MSI\\Downloads\\尚硅谷大数据");
        printEnd(file);
        for (String mty :
                strings) {
            System.out.println(mty);
        }

    }

    private void printEnd(File file) {

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File tmpFile = files[i];
            if (tmpFile.isDirectory()) {
                printEnd(tmpFile);
            } else {
                String name = tmpFile.getName();

            }
        }
    }

    @Test
    public void testDate() throws ParseException {
        File file = new File("E:\\07_学习视频\\01_2018新概念英语\\2018新东方霍娜新概念1册\\2018版新概念1册课文精讲");
        boolean exists = file.exists();
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file1 = files[i];
            System.out.println(files[i].getName());
            if (file1.isDirectory()) {
                File[] files1 = file1.listFiles();
                for (int f = 0; f < files1.length; f++) {
                    File file2 = files1[f];
//                    System.out.println(file.getPath() + "/" + file2.getName());
                    file2.renameTo(new File(file.getPath() + "/" + file2.getName()));
                }
            }
        }

    }

    @Test
    public void rename() throws Exception {

        File file = new File("E:\\07_学习视频\\07_架构师");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        Pattern compile = Pattern.compile("(\\d{4}-\\d*-\\d*-\\d*-\\d*-\\d*)");
        Pattern compile1 = Pattern.compile("(【更多分享关注微信公众号：Java技术栈】)");
//        renanmeFile(file, simpleDateFormat, compile);
        renameDirectory(file, compile1, "");
    }

    private void renameDirectory(File file, Pattern compile, String target) throws Exception {
        File[] files = file.listFiles();
        for (File file1 :
                files) {
            if (file1.isDirectory()) {
                renameDirectory(file1, compile, target);
                String name = file1.getName();
                String replace = name.replace("【更多分享关注微信公众号：架构师小秘圈】", target);
                boolean b = file1.renameTo(new File(file.getPath() + "/" + replace));
                if (!b) {
                    System.out.println(file1.getPath());
                    System.out.println(file.getPath() + "/" + replace);
                    throw new Exception("");
                }
            }
        }

    }

    private void renanmeFile(File file, SimpleDateFormat simpleDateFormat, Pattern compile) throws ParseException {
        File[] files = file.listFiles();
        String path = file.getPath();
        for (File tmpFile :
                files) {
            if (tmpFile.isDirectory()) {
                renanmeFile(tmpFile, simpleDateFormat, compile);
            } else {
                String tmpFileName = tmpFile.getName();//2016-01-17-hadoop1
                if (tmpFileName.endsWith(".mp4")) {//处理条件
                    Matcher matcher = compile.matcher(tmpFileName);
                    while (matcher.find()) {
                        String group = matcher.group(1);
                        //匹配后的处理
                        String format = simpleDateFormat.format(simpleDateFormat.parse(group));

                        tmpFileName = tmpFileName.replace(group, format);
                    }
                    String targetName = path + "/" + tmpFileName;
                    boolean b = tmpFile.renameTo(new File(targetName));
                    if (!b) {
                        System.out.println(tmpFile.getPath() + "-->" + targetName);
                    }
                }
            }

        }
    }
}
