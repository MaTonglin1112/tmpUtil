package com.mtl.util;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class CpVidioToTmp {
    private int i = 0;
    @Test
    public void myStart(){
        File file = new File("D:\\MSI\\Downloads\\尚硅谷大数据\\20_尚硅谷大数据技术之Scala-9.8-9.12\\4.video");
        doFile(file);
    }

    private void doFile(File file) {
        File[] files = file.listFiles();
        for (File f :
                files) {
            if (f.isDirectory()) {
                doFile(f);
            }else {
                String name = f.getName();
                if (name.endsWith(".avi")){
                    try {
                        Files.copy(f.toPath(), new FileOutputStream("D:/tmp/"+getNumber(i++)+"_" + f.getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String getNumber(int i) {
        if (i < 10){
            return "0" + i;
        }else {
            return String.valueOf(i);
        }
    }
}
