package com.itheima.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    // 文件上传
    public static String uploadFile(MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 设定文件上传路径，假设文件保存在 "resumes" 文件夹
        String filePath = "resumes/" + fileName;
        try {
            // 创建目标文件
            File dest = new File(filePath);
            // 转存文件
            file.transferTo(dest);
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
