package com.taotao.controller;

import com.taotao.common.util.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {
    @Test
    public void testFtp() throws Exception {
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();

        //创建ftp连接，默认是21端口
        ftpClient.connect("192.168.5.131", 21);
        //登录ftp服务器，使用用户名和密码
        ftpClient.login("ftpuser", "ftpuser");
        ftpClient.enterLocalPassiveMode();
        //上传文件
        //读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("F:\\tupian\\apple.jpg"));
        //设置上传的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        //修改上传文件的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //第一个参数：服务器端文档名
        //第二个参数：上传文件的inputStream

        ftpClient.storeFile("hello.jpg", inputStream);
        //关闭连接
        ftpClient.logout();
    }
   @Test
    public void  testFptUtil()throws  Exception{
        FileInputStream inputStream = new FileInputStream(new File("F:\\tupian\\apple.jpg"));
        FtpUtil.uploadFile("192.168.5.131",21,"ftpuser","ftpuser","/home/ftpuser/www/images","/2018/8/31","hello6.jpg",inputStream);
    }
}