package top.liwanyu.project_server.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

import top.liwanyu.project_server.aspect.GlobalException;

public class FileUtils {
    private MultipartFile file;

    public FileUtils(MultipartFile file) {
        this.file = file;
    }
    
    public String getNetFileName() {
        return file.getOriginalFilename();
    }

    public String getSuffixName() {
        String netFileName = getNetFileName();
        if (netFileName == null) 
            return "";
        return netFileName.substring(netFileName.lastIndexOf("."));
    }

    public String saveFile(String filePath) {
        String suffixName = getSuffixName();
        String fileName = System.currentTimeMillis() + suffixName;
        Path path = Paths.get(filePath + fileName);
        try {
            file.transferTo(path);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
        return fileName;
    }
    
}
