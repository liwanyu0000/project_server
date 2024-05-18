package top.liwanyu.project_server.service.intf;


import org.springframework.web.multipart.MultipartFile;

public interface FlieIntf {
    // 上传文件
    public String uploadFile(MultipartFile file);
    // 上传多个文件
    public String[] uploadFiles(MultipartFile[] files);
    // 上传图片
    public String uploadImg(MultipartFile file);
    // 上传多个图片
    public String[] uploadImgs(MultipartFile[] files);
    // 上传视频
    public String uploadVideo(MultipartFile file);
    // 上传多个视频
    public String[] uploadVideos(MultipartFile[] files);
    
}
