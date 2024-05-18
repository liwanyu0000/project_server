package top.liwanyu.project_server.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import top.liwanyu.project_server.aspect.GlobalException;
import top.liwanyu.project_server.constant.consist.FileConfig;
import top.liwanyu.project_server.constant.enums.ResultStatus;
import top.liwanyu.project_server.service.intf.FlieIntf;
import top.liwanyu.project_server.utils.FileUtils;

@Component
public class FileImpl implements FlieIntf {

    @Override
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) throw new GlobalException(ResultStatus.FILE_NOT_NULL);
        FileUtils fileUtils = new FileUtils(file);
        String suffixName = fileUtils.getSuffixName();
        String filePath;
        String fileUrl;
        // 文件分类
        if (FileConfig.IMG_SUFFIX.contains(suffixName)) {
            filePath = FileConfig.IMG_FILE_PATH;
            fileUrl = FileConfig.IMG_FILE_URL;
        } else if (FileConfig.VIDEO_SUFFIX.contains(suffixName)) {
            filePath = FileConfig.VIDEO_FILE_PATH;
            fileUrl = FileConfig.VIDEO_FILE_URL;
        } else {
            filePath = FileConfig.other_FILE_PATH;
            fileUrl = FileConfig.other_FILE_URL;
        }
        String fileName = fileUtils.saveFile(filePath);
        System.out.println(fileUrl + fileName);
        return fileUrl + fileName;
    }

    @Override
    public String[] uploadFiles(MultipartFile[] files) {
        String[] filePaths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String fileName;
            try {
                fileName = uploadFile(files[i]);
            } catch (Exception e) {
                fileName = e.getMessage();
            }
            filePaths[i] = fileName;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return filePaths;
    }

    @Override
    public String uploadImg(MultipartFile file) {
        FileUtils fileUtils = new FileUtils(file);
        String suffixName = fileUtils.getSuffixName();
        if (!FileConfig.IMG_SUFFIX.contains(suffixName)) throw new GlobalException(ResultStatus.FILE_TYPE_ERROR);
        String fileName = fileUtils.saveFile(FileConfig.IMG_FILE_PATH);
        return FileConfig.IMG_FILE_URL + fileName;
    }

    @Override
    public String[] uploadImgs(MultipartFile[] files) {
        String[] filePaths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String fileName;
            try {
                fileName = uploadImg(files[i]);
            } catch (Exception e) {
                fileName = e.getMessage();
            }
            filePaths[i] = fileName;
        }
        return filePaths;
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        FileUtils fileUtils = new FileUtils(file);
        String suffixName = fileUtils.getSuffixName();
        if (!FileConfig.VIDEO_SUFFIX.contains(suffixName)) throw new GlobalException(ResultStatus.FILE_TYPE_ERROR);
        String fileName = fileUtils.saveFile(FileConfig.VIDEO_FILE_PATH);
        return FileConfig.VIDEO_FILE_URL + fileName;
    }

    @Override
    public String[] uploadVideos(MultipartFile[] files) {
        String[] filePaths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String fileName;
            try {
                fileName = uploadVideo(files[i]);
            } catch (Exception e) {
                fileName = e.getMessage();
            }
            filePaths[i] = fileName;
        }
        return filePaths;
    }

}
