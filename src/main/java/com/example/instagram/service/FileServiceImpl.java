package com.example.instagram.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    //properties에 작성한 데이터 가져옴
    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final List<String> ALLOWED_EXTENSIONS
            = Arrays.asList("jpg", "jpeg", "png", "gif", "bmp");

    @Override
    public String fileUpload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String fileName = saveFile(file);
        return "/" + uploadDir + "/" + fileName;
    }

    private String saveFile(MultipartFile file) {
        try{
            if(file == null || file.isEmpty()){
                return null;
            }

            String originalFilename = file.getOriginalFilename();
            String extension = getExtenstion(originalFilename);
            if(!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())){
                throw new RuntimeException("Invalid file extension");
            }

            Path uploadPath = Paths.get(uploadDir);
            if(Files.notExists(uploadPath)){ //해당 경로헤 폴더가 없다면
                Files.createDirectories(uploadPath);//폴더 생성
            }

            //UUID(범용 고유 식별자)
            String savedFileName = UUID.randomUUID() + "." + extension;
            //해당 경로에 파일 이름을 합침(즉, uploads/{savedFileName}.{extension})
            Path filePath = uploadPath.resolve(savedFileName);
            Files.copy(file.getInputStream(), filePath);
            return savedFileName;
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage() + ": 파일 저장 중 오류 발생!!");
        }
    }

    //파일 확장자 조회
    private String getExtenstion(String fileName){
        if(fileName == null || !fileName.contains(".")){
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
