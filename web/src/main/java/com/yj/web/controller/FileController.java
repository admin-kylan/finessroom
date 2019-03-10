package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.exception.YJExceptionEnum;
import com.yj.common.result.JsonResult;
import com.yj.common.result.JsonReturnCode;
import com.yj.common.util.CookieUtils;
import com.yj.common.util.FileUtil;
import com.yj.web.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈文件控制器〉
 *
 * @author 欧俊俊
 * @create 2018/9/6
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${fitness.uploadPath}")
    private String filePath;

    @Value("${fitness.url}")
    private String url;

    @Value("${fitness.imgPath}")
    private String imgPath;


    private final Logger log = LoggerFactory.getLogger(FileController.class);

    /**
     * @Description: 上传
     * @Author: 欧俊俊
     * @Date: 2018/9/6 10:25
     */
    @PostMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file, @RequestParam("childPath") String childPath, HttpServletRequest request) throws YJException {
        Map<String, String> map = new HashMap<>();
        this.toUpdateLoad(file, childPath, map);
        String message = map.get("msg");
        map.put("imgUrl",url+imgPath+map.get("imgUrl"));
        if ("true".equals(message)) {
            map.put("msg", "上传成功");
        }
        return JsonResult.success(map);
    }

    public Map<String, String> toUpdateLoad(MultipartFile file, String childPath, Map<String, String> map) throws YJException {
        if (file.isEmpty()) {
            throw new YJException(YJExceptionEnum.FILE_NOT_FOUND);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        log.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        File dest = new File(filePath + childPath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            map.put("msg", "true");
            map.put("imgUrl", childPath.concat(fileName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取批量上传的文件路径
     *
     * @param files
     * @param childPath
     * @return
     * @throws YJException
     */
    public List<String> getImgUrlList(MultipartFile[] files, String childPath) throws YJException {
        Map<String, String> map = new HashMap<>();
        List<String> imgUrlList = new ArrayList<>();
        String imgURL;
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                this.toUpdateLoad(file, childPath, map);
                String message = map.get("msg");
                if ("true".equals(message)) {
                    imgUrlList.add(map.get("imgUrl"));
                }
                map = new HashMap<>();
            }
        }
        return imgUrlList;
    }


}
