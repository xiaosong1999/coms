package com.dfbz.util;

import java.io.FileOutputStream;

public class File {
    //文件保存的方法
    public static void uploadFile(byte[] file,String filePath,String fileName) throws Exception {

        if(filePath.contains("%20")){
            filePath = filePath.replace("%20"," ");
        };
        java.io.File targetFile = new java.io.File(filePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
//        System.out.println(filePath+fileName);
        out.write(file);
        out.flush();
//        System.out.println("上传成功");
        out.close();

    }

    /**
     *
     * @param filePath
     * @param fileName
     */
    public static void delFile(String filePath,String fileName) {
        try {
            if(filePath.contains("%20")){
                filePath = filePath.replace("%20"," ");
            };
            java.io.File myDelFile = new java.io.File(filePath+fileName);
            myDelFile.delete();
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
    }
}
