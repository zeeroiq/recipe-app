package com.shri.springboot.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: ZeeroIQ
 * @Date: 8/16/2019 1:27 AM
 */
public interface ImageService {
    void saveImageFile(Long id, MultipartFile imageFile) throws IOException;
}
