package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.service.service.IFrClientArchivesRelatePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存图片 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frClientArchivesRelatePic")
public class FrClientArchivesRelatePicController {
    @Autowired
    IFrClientArchivesRelatePicService iFrClientArchivesRelatePicService;

    @Autowired
    FileController fileController;

    @Value("${fitness.uploadPath}")
    private String filePath;


    @PostMapping("/savePic")
    public JsonResult savePic(@RequestParam("file") MultipartFile[] files,String childPath,Integer imgType,
                              Integer type, HttpServletRequest request, String cid, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws YJException {
            StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
            imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
            List<String> imagesList = this.getImgUrlList(files, childPath);
            return iFrClientArchivesRelatePicService.savePic(imagesList, request, type,date,cid,imgType);
    }

        @GetMapping("/delPic")
    public JsonResult delPic(String[] pids) throws YJException {

        return iFrClientArchivesRelatePicService.delPic(pids,filePath);
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
        for (MultipartFile file : files) {
            fileController.toUpdateLoad(file, childPath, map);
            String message = map.get("msg");
            if ("true".equals(message)) {
                imgUrlList.add(map.get("imgUrl"));
            }
            map = new HashMap<>();
        }
        return imgUrlList;
    }
}

