package com.yj.web.controller;


import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.dto.ArchivesDTO;
import com.yj.dal.model.FrClientArchivesRelate;
import com.yj.service.service.IFrClientArchivesRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 客户档案（皮肤、头发、塑性抗衰 档案）  内容保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-11-13
 */
@RestController
@RequestMapping("/frClientArchivesRelate")
public class FrClientArchivesRelateController {

    @Autowired
    IFrClientArchivesRelateService iFrClientArchivesRelateService;

    @Autowired
    FileController fileController;



    @PostMapping("/getRelate")
    public JsonResult getRelate(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                               String cid,Integer type) throws YJException {
        ArchivesDTO archivesDTO = iFrClientArchivesRelateService.getRelate(date, cid, type);
        if (archivesDTO != null) {
            return JsonResult.success(archivesDTO);
        } else {
            return JsonResult.failMessage("未保存过数据");
        }

    }

    @PostMapping("/updateRelate")
    public JsonResult updateRelate(String[] relateIds, String text, Integer type) throws YJException {
        return iFrClientArchivesRelateService.updateRelate(relateIds, text, type);
    }

    @PostMapping("/saveRelate")
    public JsonResult saveRelate(String[] typeIds, String text, String cid, Integer type,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, HttpServletRequest request) throws YJException {
//        @RequestParam("file") MultipartFile[] files,
//        String childPath = null;
//        if (files != null) {
//            if (type == 1) {
//                childPath = "skin";
//            } else if (type == 2) {
//                childPath = "hair";
//            } else if (type == 3) {
//                childPath = "plasticity";
//            }
//        }
//        StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
//        imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
//        List<String> imagesList = this.getImgUrlList(files, childPath);

        return iFrClientArchivesRelateService.saveRelate(typeIds, text, cid, type, date, request);
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

