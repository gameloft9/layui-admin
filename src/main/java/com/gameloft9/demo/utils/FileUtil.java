package com.gameloft9.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.UUID;


/**
 * 文件操作类
 * create by gameloft9 2017-12-28
 */
@Slf4j
public class FileUtil {

    /**
     * 新建目录
     *
     * @param folderPath String 如 c:/fqf
     */
    public static void newFolder(String folderPath) throws Exception {
        String filePath = folderPath;
        File myFilePath = new File(filePath);
        if (!myFilePath.exists()) {
            myFilePath.mkdir();
        }
    }

    /**
     * 新建文件
     *
     * @param filePathAndName 文件路径及名称 如c:/fqf.txt
     * @param fileContent     String 文件内容
     * @return boolean
     */
    public static void newFile(String filePathAndName, String fileContent) throws Exception {

        String filePath = filePathAndName;
        filePath = filePath.toString();
        File myFilePath = new File(filePath);
        if (!myFilePath.exists()) {
            myFilePath.createNewFile();
        }
        FileWriter resultFile = new FileWriter(myFilePath);
        PrintWriter myFile = new PrintWriter(resultFile);
        String strContent = fileContent;
        myFile.println(strContent);
        resultFile.close();
    }

    /**
     * 新建文件
     *
     * @param filePath
     * @param fileName
     * @param fileContent
     * @throws Exception
     */
    public static void newFile(String filePath, String fileName, String fileContent)
            throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        File myFilePath = new File(f, fileName);
        if (!myFilePath.exists()) {
            myFilePath.createNewFile();
        }
        FileWriter resultFile = new FileWriter(myFilePath);
        PrintWriter myFile = new PrintWriter(resultFile);
        String strContent = fileContent;
        myFile.println(strContent);
        resultFile.close();
    }

    /**
     * 删除文件
     *
     * @param filePathAndName 文件路径及名称 如c:/fqf.txt
     * @return boolean
     */
    public static void delFile(String filePathAndName) throws Exception {
        String filePath = filePathAndName;
        File myDelFile = new File(filePath);
        myDelFile.delete();
    }

    /**
     * 判断一个文件是否存在
     *
     * @param filePath 文件路径
     * @return 存在返回true，否则返回false
     */
    public static boolean isExist(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 保存文件到服务器返回访问url。
     *
     * @param fileObj               文件对象
     * @param bizType               业务类型
     * @param fileName              文件名称
     * @param downloadServerRootUrl 服务器访问根路径
     * @param fileRootPath          文件保存根路径
     * @return 访问url
     */
    public static String saveAndReturnUrl(byte[] fileObj, String bizType, String fileName, String downloadServerRootUrl, String fileRootPath) throws Exception {
        String fileRootDirectory = fileRootPath;
        // 上传文件保存文件夹
        String fileTypeDateDirectory = bizType + File.separator
                + getCurrentDay();
        // 文件保存路径 -- 不包含根路径
        String fileSavedPath = fileTypeDateDirectory
                + File.separator
                + UUID.randomUUID().toString()
                + fileName.substring(fileName.lastIndexOf(".",
                fileName.length()));
        // 文件保存全路径
        String savedPath = fileRootDirectory + fileSavedPath;

        // 将文件保存至目标路径
        saveTheFileToDestination(fileObj, fileRootDirectory
                + fileTypeDateDirectory, savedPath);

        //网页访问路径
        String httpUrl = downloadServerRootUrl + bizType + File.separator +  fileSavedPath;
        return httpUrl;
    }

    /**********************************私有方法区***********************************************/
    /**
     * 得到当前日期，组成文件夹格式
     *
     * @return 当前日期 例: 2014-11-12
     */
    private static String getCurrentDay() {
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        return format.format(new java.util.Date());
    }

    /**
     * 将文件保存到知道目录
     *
     * @param fileObj               要保存的文件的二进制流
     * @param fileTypeDateDirectory 要保存的文件的文联类型的路径
     * @param savedPath             保存路径
     * @throws Exception
     */
    private static void saveTheFileToDestination(byte[] fileObj,
                                                 String fileTypeDateDirectory, String savedPath) throws Exception {

        FileOutputStream fos = null;
        try {

            File newfile = new File(fileTypeDateDirectory);
            boolean createDirSuccess = true;
            // 先判定父文件夹是否存在，如果不存在，新建一个 --判定fileType一层
            if (!newfile.getParentFile().exists()) {
                createDirSuccess = newfile.getParentFile().mkdir();
            }
            // 判定当前文件的所在的文件夹是否存在 -- 判定date 文件夹一层
            if (!newfile.exists()) {
                createDirSuccess = newfile.mkdir();
            }
            // 如果创建失败，则抛出错误，提示文件夹路径
            if (!createDirSuccess) {
                throw new Exception("创建文件夹失败:"
                        + newfile.getParentFile().getPath());
            }
            log.info("save to path:" + savedPath);
            // 创建当前文件的对象
            newfile = new File(savedPath);
            if (!newfile.exists()) {// 文件不存在则创建
                newfile.createNewFile();
            }
            // 做输出文件流，输出文件
            fos = new FileOutputStream(newfile);

            fos.write(fileObj);// 写入文件内容
            fos.flush();
            fos.close();
        } catch (IOException e) {
            log.error("文件创建失败" + e.getMessage(), e);
            throw new Exception("文件创建失败" + e.getMessage());
        } catch (Exception e) {
            log.error("保存文件失败失败" + e.getMessage(), e);
            throw new Exception("保存文件失败失败" + e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("文件流关闭失败" + e.getMessage(), e);
                    throw new Exception("文件流关闭失败" + e.getMessage());
                }
            }
        }
    }

    /**
     * 获取保存路径
     * @param fileRootPath 保存根路径
     * @param bizType 业务类型
     * @param fileName 文件名
     * */
    private String getSavePath(String fileRootPath,String bizType,String fileName){
        String fileTypeDateDirectory = bizType + File.separator + getCurrentDay();
        // 文件保存路径,不包含根路径
        String fileSavedPath = fileTypeDateDirectory
                + File.separator
                + UUID.randomUUID().toString()
                + fileName.substring(fileName.lastIndexOf(".",
                fileName.length()));
        // 文件保存全路径
        String savedPath = fileRootPath + fileSavedPath;

        return savedPath;
    }

}
