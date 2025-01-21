package com.itheima.Service;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {
    String saveAvatar(MultipartFile file, int employeeId) throws Exception;

    void deleteAvatar(int employeeId) throws Exception;
}
