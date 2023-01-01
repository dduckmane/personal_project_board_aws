package com.project.board.domain.board.domain;

import com.project.board.global.util.FileUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UploadFile {
    private String uploadFileName;
    private String storeFileName;
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
    public static UploadFile createUploadFile(MultipartFile multipartFile,String uploadPath){
        //custom File Utils 제작
        String storeFileName = FileUtils.uploadFile(multipartFile, uploadPath);
        String uploadFileName = multipartFile.getOriginalFilename();
        UploadFile uploadFile = new UploadFile(uploadFileName, storeFileName);

        return uploadFile;
    }
}
