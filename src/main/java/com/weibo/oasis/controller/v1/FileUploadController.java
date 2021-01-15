package com.weibo.oasis.controller.v1;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "FileUploadController")
@RestController
@RequestMapping("/api/v1/*")
public class FileUploadController {

    @Resource
    private FileUploadService fileUploadService;

    @ApiOperation("文件上传")
    @RequestMapping(value = "/files/uploads", method = RequestMethod.POST)
    public RestResponse create(MultipartFile file, Integer userId){
        fileUploadService.upload(file, "/usr/local/fileUploads", userId);
        return RestResponse.success();
    }
}
