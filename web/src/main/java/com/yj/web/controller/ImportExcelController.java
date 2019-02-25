package com.yj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.common.result.JsonResult;
import com.yj.common.util.ImportExcelUtils;
import com.yj.service.service.IImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Excel导入
 * 2019.01.17
 * zyd
 */
@RestController
@RequestMapping("/import_excel")
public class ImportExcelController {

    @Autowired
    IImportExcelService iImportExcelService;

    /**客户信息导入
     *
     *
     * @param excel
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/client_info")
    public JsonResult orgUpload(@RequestParam(value = "excel", required = false) MultipartFile excel) throws Exception {
        if(excel != null){
            List<Map<String, Object>> list = new ArrayList<>();
            if (!excel.isEmpty()) {
                list = new ImportExcelUtils().readExcel(excel, 1);
                return iImportExcelService.clientUpload(list);

            }
            return JsonResult.failMessage("Excel导入失败");
        }
        return JsonResult.failMessage("Excel文件不能为空");
    }
    /**
     * 潜在客户导入
     *
     * @param excel
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/prospective_client")
        public JsonResult prospectiveClient(@RequestParam(value = "excel", required = false) MultipartFile excel) throws Exception {
        if(excel == null){
            return JsonResult.failMessage("Excel文件不能为空");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        if (!excel.isEmpty()) {
            list = new ImportExcelUtils().readExcel(excel, 2);
            return iImportExcelService.prospectiveClient(list);
        }
        return JsonResult.failMessage("Excel文件错误");
    }
    /**
     * 私教项目导入
     *
     * @param excel
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/personal_trainer")
    public JsonResult personalTrainer(@RequestParam(value = "excel", required = false) MultipartFile excel) throws Exception {
        if(excel == null){
            return JsonResult.failMessage("Excel文件不能为空");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        if (!excel.isEmpty()) {
            list = new ImportExcelUtils().readExcel(excel, 3);
            return iImportExcelService.personalTrainer(list);
        }
        return JsonResult.failMessage("Excel文件错误");
    }
}
