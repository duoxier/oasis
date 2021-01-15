package com.weibo.oasis.service;

import com.weibo.oasis.RestResponse;
import com.weibo.oasis.dao.FileDao;
import com.weibo.oasis.error.ServiceError;
import com.weibo.oasis.po.FilePO;
import com.weibo.oasis.utils.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class FileUploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    @Resource
    FileDao fileDao;

    public RestResponse upload(MultipartFile file, String baseDir, Integer userId) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || "".equals(originalFilename)){
                return RestResponse.error(ServiceError.FILE_NAME_NULL);
            }
            FilePO filePO = new FilePO();
            filePO.setOldName(originalFilename);
            filePO.setName("上传文件名称");
            String fileLocation = null;
            if (baseDir != null){
                fileLocation = FileUploadUtils.upload(baseDir, file);
            }else {
                fileLocation = FileUploadUtils.upload(file);
            }
            filePO.setLocation(fileLocation);
            filePO.setUserId(userId);
            fileDao.create(filePO);
            return RestResponse.success();
        }catch (Exception e){
            LOGGER.error("FileUploadService create error {}", e);
            return RestResponse.error(ServiceError.OASIS_SERVICE_ERROR);
        }
    }

}
