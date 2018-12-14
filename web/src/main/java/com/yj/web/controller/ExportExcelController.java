package com.yj.web.controller;

import com.yj.common.result.JsonResult;
import com.yj.common.util.ExportExcel;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.MyPotentialClientDTO;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel导出
 */
@RestController
@RequestMapping("/excel")
public class ExportExcelController {

    @PostMapping("/myPotential")
    public JsonResult myPotential(@RequestBody  List<MyPotentialClientDTO> param){
        ExportExcel<MyPotentialClientDTO> ex = new ExportExcel<>();
        String[] headers =
                { "会员姓名", "性别", "联系方式", "保护天数", "微信"
                        ,"总联系数", "客户等级", "意向门店", "渠道来源", "跟进总次数"
                        ,"最早消费时间", "最近消费时间", "消费总数", "频率(天/次)", "建档时间"
                        ,"最近来访时间", "购买意向", "服务会籍", "跟进人", "首次跟进时间"
                        ,"最近跟进时间", "跟进内容", "意向卡类别", "意向卡名称", "意向卡价格"
                };
        try
        {
            String path="E://"+ UUIDUtils.generateGUID()+".xls";
            OutputStream out = new FileOutputStream(path);
            ex.exportExcel(headers, param, out);
            out.close();
            return JsonResult.successMessage("导出成功,路径为:"+path);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return JsonResult.failMessage("导出失败");
    }

    }


