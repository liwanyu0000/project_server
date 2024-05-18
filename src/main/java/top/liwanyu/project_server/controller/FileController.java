package top.liwanyu.project_server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import top.liwanyu.project_server.service.intf.FlieIntf;

@RestController
@RequestMapping("/upload")
public class FileController {

    @Resource
    private FlieIntf flieIntf;

    @PostMapping("/file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return flieIntf.uploadFile(file);
    }

    @PostMapping("/files")
    public String[] uploadFiles(@RequestParam("files") MultipartFile[] files) {
        return flieIntf.uploadFiles(files);
    }

    @PostMapping("/img")
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        return flieIntf.uploadImg(file);
    }

    @PostMapping("/imgs")
    public String[] uploadImgs(@RequestParam("files") MultipartFile[] files) {
        return flieIntf.uploadImgs(files);
    }

    @PostMapping("/video")
    public String uploadVideo(@RequestParam("file") MultipartFile file) {
        return flieIntf.uploadVideo(file);
    }

    @PostMapping("/videos")
    public String[] uploadVideos(@RequestParam("files") MultipartFile[] files) {
        return flieIntf.uploadVideos(files);
    }
    
}
