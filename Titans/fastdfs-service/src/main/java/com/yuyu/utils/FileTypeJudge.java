package com.yuyu.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
/**
 * 文件类型判断类
 */
public final class FileTypeJudge {
     
    /**
     * 将文件头转换成16进制字符串
     * 
     * @param
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] b){
         
        StringBuilder stringBuilder = new StringBuilder();   
        if (b == null || b.length <= 0) {   
            return null;   
        }   
        for (int i = 0; i < b.length; i++) {   
            int v = b[i] & 0xFF;   
            String str = Integer.toHexString(v);   
            if (str.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(str);   
        }   
        return stringBuilder.toString();   
    }
    
    /**
     * 得到文件头
     * 
     * @param filePath 文件路径
     * @return 文件头
     * @throws IOException
     */
    private static String getFileContent(String filePath) throws IOException {
         
        byte[] b = new byte[28];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            inputStream.read(b, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
        }
        return bytesToHexString(b);
    }
     
    /**
     * 判断文件类型
     * 
     * @param filePath 文件路径
     * @return 文件类型
     */
    public static FileType getType(String filePath) throws IOException {
         
        String fileHead = getFileContent(filePath);
        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }
         
        fileHead = fileHead.toUpperCase();
        System.out.println(fileHead);
        FileType[] fileTypes = FileType.values();
        for (FileType type : fileTypes) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }
        return null;
    }

        public static void main(String args[]) throws Exception {
            System.out.println(FileTypeJudge.getType("E:\\a.zip"));
//            byte[] a=new byte[]{'F','F', 'D','8'};
//            System.out.println(bytesToHexString(a));;
    }

}