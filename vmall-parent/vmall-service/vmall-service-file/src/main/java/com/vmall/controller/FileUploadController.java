package com.vmall.controller;

import com.vmall.FastDFSFile;
import com.vmall.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
 * @ClassName: FileUploadController
 * @Description:
 * @Author: Se7en
 * @Date: 2020/7/14 5:30
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/upload")
@CrossOrigin
public class FileUploadController {

    /***
     * 文件上传
     */
    @PostMapping
    public Result upload(@RequestParam(value = "file")MultipartFile file) throws Exception{
        //封装文件信息
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename()));

        //调用FastDFSUtil工具类将文件传入到FastDFS中
        String[] uploads = FastDFSUtil.upload(fastDFSFile);

        //拼接访问地址 url = localhost:8080/group1/M00/00/00/imm.jpg
        String url = "http://192.168.211.132:8080/" + uploads[0] + "/" + uploads[1];
        return new Result(true, StatusCode.OK, "上传成功", url);
    }
}
