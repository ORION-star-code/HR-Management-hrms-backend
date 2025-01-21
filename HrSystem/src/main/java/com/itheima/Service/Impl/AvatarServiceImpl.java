package com.itheima.Service.Impl;

import com.itheima.Mapper.EmployeeMapper;
import com.itheima.Service.AvatarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class AvatarServiceImpl implements AvatarService {

    @Value("${avatar.upload.dir}")
    private String uploadDir;  // 头像存储路径

    @Autowired
    private EmployeeMapper employeeMapper;  // 注入 EmployeeMapper

    // 保存头像文件
    @Override
    public String saveAvatar(MultipartFile file, int employeeId) throws Exception {
        // 判断文件是否为空
        if (file.isEmpty()) {
            throw new Exception("文件为空！");
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 创建存储路径
        Path path = Paths.get(uploadDir, fileName);

        // 创建文件目录（如果目录不存在）
        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        try {
            // 保存文件到本地
            file.transferTo(path);

            // 获取文件的URL（根据实际情况调整）
            String fileUrl = "/avatars/" + fileName;

            // 更新数据库中的头像URL
            employeeMapper.updateAvatarUrl( employeeId, fileUrl); // 更新员工的头像URL

            log.info("头像上传成功: {}", fileUrl);

            // 返回文件的相对路径
            return fileUrl;
        } catch (IOException e) {
            throw new Exception("文件保存失败：" + e.getMessage());
        }
    }

    // 删除头像
    @Override
    public void deleteAvatar(int employeeId) throws Exception {
        // 获取员工的当前头像URL
        String avatarUrl = employeeMapper.getAvatarUrlById(employeeId);

        if (avatarUrl == null || avatarUrl.isEmpty()) {
            throw new Exception("员工没有设置头像");
        }

        // 删除数据库中的头像URL
        employeeMapper.deleteAvatar( employeeId);

        // 删除本地文件
        String filePath = uploadDir + avatarUrl.substring("/avatars/".length());
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                log.info("头像文件删除成功: {}", filePath);
            } else {
                log.error("头像文件删除失败: {}", filePath);
            }
        }
    }
}
