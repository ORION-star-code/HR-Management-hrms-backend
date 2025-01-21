package com.itheima.Controller;

import com.itheima.Service.AvatarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("upload")
@CrossOrigin(origins = "*") // 跨域支持
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    // 头像上传接口
    @PostMapping("/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("employeeId") int employeeId) {
        try {
            // 调用服务层方法保存文件并返回文件的URL
            String fileUrl = avatarService.saveAvatar(file, employeeId);
            return ResponseEntity.ok().body(fileUrl); // 返回文件URL
        } catch (Exception e) {
            log.error("头像上传失败", e);
            return ResponseEntity.status(500).body("上传失败: " + e.getMessage());
        }
    }

    // 删除头像接口
    @DeleteMapping("/avatar/{employeeId}")
    public ResponseEntity<String> deleteAvatar(@PathVariable int employeeId) {
        try {
            avatarService.deleteAvatar(employeeId);
            return ResponseEntity.ok("头像删除成功");
        } catch (Exception e) {
            log.error("头像删除失败", e);
            return ResponseEntity.status(500).body("删除失败: " + e.getMessage());
        }
    }
}
