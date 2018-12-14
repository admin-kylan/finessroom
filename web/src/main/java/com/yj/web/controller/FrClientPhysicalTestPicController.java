package com.yj.web.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.CookieUtils;
import com.yj.dal.model.FrClientPhysicalTestPic;
import com.yj.service.service.IFrClientPhysicalTestPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 体能测试图片保存 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2018-12-10
 */
@RestController
@RequestMapping("/frClientPhysicalTestPic")
public class FrClientPhysicalTestPicController {

    @Autowired
    IFrClientPhysicalTestPicService frClientPhysicalTestPicService;

    @Autowired
    FileController fileController;

    @Value("${fitness.uploadPath}")
    private String filePath;


    @GetMapping("/getPic")
    public JsonResult getPic(String tid) {
        List<FrClientPhysicalTestPic> frClientPhysicalTestPics = frClientPhysicalTestPicService.selectList(
                new EntityWrapper<FrClientPhysicalTestPic>().where("fr_client_physical_test_id={0}", tid)
        );
        return JsonResult.success(frClientPhysicalTestPics);
    }

    @PostMapping("/savePic")
    public JsonResult savePic(@RequestParam("file") MultipartFile[] files, String childPath, String tid,
                              HttpServletRequest request, String cid) throws YJException {
        StringBuffer imagePath = new StringBuffer(CookieUtils.getCookieValue(request, "url", true));
        imagePath.append(CookieUtils.getCookieValue(request, "imgPath", true));
        List<String> imagesList = this.getImgUrlList(files, childPath);
        return frClientPhysicalTestPicService.savePic(imagesList, request, cid, tid);
    }

    @GetMapping("/delPic")
    public JsonResult delPic(String[] pids) throws YJException {

        return frClientPhysicalTestPicService.delPic(pids, filePath);
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

