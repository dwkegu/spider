package com.dwkegu.spiderlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSaver {
    private static final String path = "f:/tmp/nytimes/dataset/";
    private File rootFile;

    public FileSaver(){
        rootFile = new File(path);
        if(!rootFile.exists()){
            if(!rootFile.mkdirs()){
                rootFile = null;
            }
        }
    }
    public void save(String category,String fileName, String content){
        if(rootFile==null){
            return;
        }
        File savePath = new File(path+category);
        if(!savePath.exists()){
            if(!savePath.mkdirs()){
                return;
            }
        }
        String saveFilePath = path+category+fileName;
        if(!category.endsWith("/")){
            saveFilePath = path+category+"/"+fileName;
        }
        try(FileOutputStream fos = new FileOutputStream(saveFilePath,true)) {
            fos.write(content.getBytes("UTF-8"));
            fos.write("\n".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
