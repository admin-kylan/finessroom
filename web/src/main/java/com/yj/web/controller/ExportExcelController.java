package com.yj.web.controller;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.ExportExcel;
import com.yj.common.util.UUIDUtils;
import com.yj.dal.dto.CollarClientDTO;
import com.yj.dal.dto.CustomerAllocationDTO;
import com.yj.dal.dto.MyExistenceClientDTO;
import com.yj.dal.dto.MyPotentialClientDTO;
import com.yj.dal.model.FrClientArchivesRelate;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel导出
 */
@RestController
@RequestMapping("/excel")
public class ExportExcelController {
    /**
     * 导出我的潜在客户
     * @param param
     * @return
     */
    @PostMapping("/myPotential")
    public JsonResult myPotential(@RequestBody List<MyPotentialClientDTO> param)  {
        ExportExcel<MyPotentialClientDTO> ex = new ExportExcel<>();
        String[] headers =
                {"会员姓名", "性别", "联系方式", "保护天数", "微信"
                        , "总联系数", "客户等级", "意向门店", "渠道来源", "跟进总次数"
                        , "最早消费时间", "最近消费时间", "消费总数", "频率(天/次)", "建档时间"
                        , "最近来访时间", "购买意向", "服务会籍", "跟进人", "首次跟进时间"
                        , "最近跟进时间", "跟进内容", "意向卡类别", "意向卡名称", "意向卡价格"
                };
        OutputStream out=null;
        try {
            String path = "E:/finessroomExcel/myPotential/" + UUIDUtils.generateGUID() + ".xls";
            File file = new File("E:/finessroomExcel/myPotential");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
             out = new FileOutputStream(path);
            ex.exportExcel(headers, param, out);
            out.close();
            return JsonResult.successMessage("导出成功,路径为:" + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return JsonResult.failMessage("导出失败");
    }

    /**
     * 导出我的现有客户
     * @param param
     * @return
     */
    @PostMapping("/myCustomer")
    public JsonResult myCustomer(@RequestBody List<MyExistenceClientDTO> param) {
        ExportExcel<MyExistenceClientDTO> ex = new ExportExcel<>();
        String[] headers =
                {"会员姓名", "客户等级", "性别", "联系方式", "微信"
                        , "总联系数", "保护天数", "建档时间", "最近来访时间", "服务会籍"
                        , "销售顾问", "跟进人", "跟进总次数", "首次跟进时间"
                        , "最近跟进时间", "跟进记录"
                };
        try {
            String path = "E:/finessroomExcel/myCustomer/" + UUIDUtils.generateGUID() + ".xls";
            File file = new File("E:/finessroomExcel/myCustomer");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(path);
            ex.exportExcel(headers, param, out);
            out.close();
            return JsonResult.successMessage("导出成功,路径为:" + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.failMessage("导出失败");
    }

    /**
     * 导出可认领客户
     * @param param
     * @return
     */
    @PostMapping("/claimingPotential")
    public JsonResult claimingPotential(@RequestBody List<CollarClientDTO> param) {
        ExportExcel<CollarClientDTO> ex = new ExportExcel<>();
        String[] headers =
                {"姓名", "性别", "联系方式", "建档时间", "最近跟进日期"
                        , "手动跟进次数", "自动跟进次数", "跟进内容"
                        , "多少天无人跟进", "销售顾问", "服务会籍"
                };
        try {
            String path = "E:/finessroomExcel/claimingPotential/" + UUIDUtils.generateGUID() + ".xls";
            File file = new File("E:/finessroomExcel/claimingPotential");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(path);
            ex.exportExcel(headers, param, out);
            out.close();
            return JsonResult.successMessage("导出成功,路径为:" + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.failMessage("导出失败");
    }

    /**
     * 导出客户分配
     * @param param
     * @return
     */
    @PostMapping("/customerAllocation")
    public JsonResult customerAllocation(@RequestBody List<CustomerAllocationDTO> param) {
        ExportExcel<CustomerAllocationDTO> ex = new ExportExcel<>();
        String[] headers =
                {"客户类型", "姓名", "性别", "联系方式", "建档时间", "最近跟进日期"
                        , "手动跟进次数", "自动跟进次数", "跟进内容"
                        , "多少天无人跟进", "销售顾问", "服务会籍", "操作人"
                };
        try {
            String path = "E:/finessroomExcel/customerAllocation/" + UUIDUtils.generateGUID() + ".xls";
            File file = new File("E:/finessroomExcel/customerAllocation");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(path);
            ex.exportExcel(headers, param, out);
            out.close();
            return JsonResult.successMessage("导出成功,路径为:" + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.failMessage("导出失败");
    }

    /**
     * 导出皮肤档案
     * @param param
     * @return
     * @throws YJException
     */
    @PostMapping("/getSkin")
    public JsonResult getSkin(@RequestBody List<FrClientArchivesRelate> param) throws YJException {

        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件

        HSSFSheet sheet = workbook.createSheet("皮肤档案");// 创建一个Excel的Sheet

        List<FrClientArchivesRelate> list0 = new ArrayList<>();
        List<FrClientArchivesRelate> list1 = new ArrayList<>();
        List<FrClientArchivesRelate> list2 = new ArrayList<>();
        List<FrClientArchivesRelate> list3 = new ArrayList<>();
        List<FrClientArchivesRelate> list4 = new ArrayList<>();
        List<FrClientArchivesRelate> list5 = new ArrayList<>();
        List<FrClientArchivesRelate> list6 = new ArrayList<>();
        List<FrClientArchivesRelate> list7 = new ArrayList<>();
        List<FrClientArchivesRelate> list8 = new ArrayList<>();
        List<FrClientArchivesRelate> list9 = new ArrayList<>();
        List<FrClientArchivesRelate> list10 = new ArrayList<>();
        FrClientArchivesRelate relate1 = new FrClientArchivesRelate();
        List<FrClientArchivesRelate> list11 = new ArrayList<>();
        List<FrClientArchivesRelate> list12 = new ArrayList<>();
        List<FrClientArchivesRelate> list13 = new ArrayList<>();
        List<FrClientArchivesRelate> list14 = new ArrayList<>();
        List<FrClientArchivesRelate> list15 = new ArrayList<>();
        List<FrClientArchivesRelate> list16 = new ArrayList<>();
        List<FrClientArchivesRelate> list17 = new ArrayList<>();
        FrClientArchivesRelate relate2 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate3 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate4 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate5 = new FrClientArchivesRelate();
        List<FrClientArchivesRelate> list18 = new ArrayList<>();
        List<FrClientArchivesRelate> list19 = new ArrayList<>();
        FrClientArchivesRelate relate6 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate7 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate8 = new FrClientArchivesRelate();
        for (FrClientArchivesRelate clientArchivesRelate : param) {
            if (clientArchivesRelate.getType() == 1) {
                list0.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 2) {
                list1.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 3) {
                list2.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 4) {
                list3.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 5) {
                list4.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 6) {
                list5.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 7) {
                list6.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 8) {
                list7.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 9) {
                list8.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 10) {
                list9.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 11) {
                list10.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 12) {
                list11.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 13) {
                list12.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 14) {
                list13.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 15) {
                list14.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 16) {
                list15.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 17) {
                list16.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 18) {
                list17.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 19) {
                list18.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 20) {
                list19.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 21) {
                relate1 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 22) {
                relate2 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 23) {
                relate3 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 24) {
                relate4 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 25) {
                relate5 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 26) {
                relate6 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 27) {
                relate7 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 28) {
                relate8 = clientArchivesRelate;

            }
        }
        HSSFRow row1 = sheet.createRow(0);// 创建第一行
        HSSFCell column1 = row1.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString = new HSSFRichTextString("皮肤实况档案");
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);
        richTextString.applyFont(font3);
        column1.setCellValue(richTextString);

        HSSFRow row2 = sheet.createRow(1);// 创建第二行
        HSSFCell column2 = row2.createCell(0);// 创建第一行第一列
        column2.setCellValue(new HSSFRichTextString("护理状态:"));
        for (int i = 0; i < list0.size(); i++) {
            HSSFCell cleN = row2.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list0.get(i).getArchivesTypeContent()));
        }
        HSSFRow row3 = sheet.createRow(2);// 创建第三行
        HSSFCell column3 = row3.createCell(0);// 创建第一行第一列
        column3.setCellValue(new HSSFRichTextString("皮肤类型:"));
        for (int i = 0; i < list1.size(); i++) {
            HSSFCell cleN = row3.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list1.get(i).getArchivesTypeContent()));
        }
        HSSFRow row4 = sheet.createRow(3);// 创建第四行
        HSSFCell column4 = row4.createCell(0);// 创建第一行第一列
        column4.setCellValue(new HSSFRichTextString("皮肤观察:"));
        for (int i = 0; i < list2.size(); i++) {
            HSSFCell cleN = row4.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list2.get(i).getArchivesTypeContent()));
        }
        HSSFRow row5 = sheet.createRow(4);// 创建第五行
        HSSFCell column5 = row5.createCell(0);// 创建第一行第一列
        column5.setCellValue(new HSSFRichTextString("额头:"));
        for (int i = 0; i < list3.size(); i++) {
            HSSFCell cleN = row5.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list3.get(i).getArchivesTypeContent()));
        }
        HSSFRow row6 = sheet.createRow(5);// 创建第六行
        HSSFCell column6 = row6.createCell(0);// 创建第一行第一列
        column6.setCellValue(new HSSFRichTextString("眼部:"));
        for (int i = 0; i < list4.size(); i++) {
            HSSFCell cleN = row6.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list4.get(i).getArchivesTypeContent()));
        }
        HSSFRow row7 = sheet.createRow(6);// 创建第七行
        HSSFCell column7 = row7.createCell(0);// 创建第一行第一列
        column7.setCellValue(new HSSFRichTextString("面颊:"));
        for (int i = 0; i < list5.size(); i++) {
            HSSFCell cleN = row7.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list5.get(i).getArchivesTypeContent()));
        }
        HSSFRow row8 = sheet.createRow(7);// 创建第八行
        HSSFCell column8 = row8.createCell(0);// 创建第一行第一列
        column8.setCellValue(new HSSFRichTextString("鼻子:"));
        for (int i = 0; i < list6.size(); i++) {
            HSSFCell cleN = row8.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list6.get(i).getArchivesTypeContent()));
        }
        HSSFRow row9 = sheet.createRow(8);// 创建第九行
        HSSFCell column9 = row9.createCell(0);// 创建第一行第一列
        column9.setCellValue(new HSSFRichTextString("嘴唇:"));
        for (int i = 0; i < list7.size(); i++) {
            HSSFCell cleN = row9.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list7.get(i).getArchivesTypeContent()));
        }
        HSSFRow row10 = sheet.createRow(9);// 创建第十行
        HSSFCell column10 = row10.createCell(0);// 创建第一行第一列
        column10.setCellValue(new HSSFRichTextString("颈部:"));
        for (int i = 0; i < list8.size(); i++) {
            HSSFCell cleN = row10.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list8.get(i).getArchivesTypeContent()));
        }
        HSSFRow row11 = sheet.createRow(10);// 创建第十一行
        HSSFCell column11 = row11.createCell(0);// 创建第一行第一列
        column11.setCellValue(new HSSFRichTextString("胸部:"));
        for (int i = 0; i < list9.size(); i++) {
            HSSFCell cleN = row11.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list9.get(i).getArchivesTypeContent()));
        }
        HSSFRow row12 = sheet.createRow(11);// 创建第十二行
        HSSFCell column12 = row12.createCell(0);// 创建第一行第一列
        column12.setCellValue(new HSSFRichTextString("腰部:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row12.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list10.get(i).getArchivesTypeContent()));
        }
        HSSFRow row13 = sheet.createRow(12);// 创建第十三行
        HSSFCell column13 = row13.createCell(0);// 创建第一行第一列
        column13.setCellValue(new HSSFRichTextString("其他原因:"));
        HSSFCell ce13_1 = row13.createCell(1);
        ce13_1.setCellValue(new HSSFRichTextString(relate1.getArchivesTypeContent()));

        HSSFRow row14 = sheet.createRow(13);// 创建第十四行
        HSSFCell column14 = row14.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString2 = new HSSFRichTextString("皮肤实况档案");
        richTextString2.applyFont(font3);
        column14.setCellValue(richTextString2);

        HSSFRow row15 = sheet.createRow(14);// 创建第十五行
        HSSFCell column15 = row15.createCell(0);// 创建第一行第一列
        column15.setCellValue(new HSSFRichTextString("面疱原因:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row15.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list11.get(i).getArchivesTypeContent()));
        }
        HSSFRow row16 = sheet.createRow(15);// 创建第十六行
        HSSFCell column16 = row16.createCell(0);// 创建第一行第一列
        column16.setCellValue(new HSSFRichTextString("黑斑原因:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row16.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list12.get(i).getArchivesTypeContent()));
        }
        HSSFRow row17 = sheet.createRow(16);// 创建第十七行
        HSSFCell column17 = row17.createCell(0);// 创建第一行第一列
        column17.setCellValue(new HSSFRichTextString("皱纹原因:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row17.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list13.get(i).getArchivesTypeContent()));
        }
        HSSFRow row18 = sheet.createRow(17);// 创建第十八行
        HSSFCell column18 = row18.createCell(0);// 创建第一行第一列
        column18.setCellValue(new HSSFRichTextString("过敏原因:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row18.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list14.get(i).getArchivesTypeContent()));
        }
        HSSFRow row19 = sheet.createRow(18);// 创建第十九行
        HSSFCell column19 = row19.createCell(0);// 创建第一行第一列
        column19.setCellValue(new HSSFRichTextString("毛孔粗大原因:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row19.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list15.get(i).getArchivesTypeContent()));
        }
        HSSFRow row20 = sheet.createRow(19);// 创建第二十行
        HSSFCell column20 = row20.createCell(0);// 创建第一行第一列
        column20.setCellValue(new HSSFRichTextString("维他命需求原因:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row20.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list16.get(i).getArchivesTypeContent()));
        }
        HSSFRow row21 = sheet.createRow(20);// 创建第二十一行
        HSSFCell column21 = row21.createCell(0);// 创建第一行第一列
        column21.setCellValue(new HSSFRichTextString("过敏史:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row21.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list17.get(i).getArchivesTypeContent()));
        }
        HSSFRow row22 = sheet.createRow(21);// 创建第二十二行
        HSSFCell column22 = row22.createCell(0);// 创建第一行第一列
        column22.setCellValue(new HSSFRichTextString("使用过护肤品:"));
        HSSFCell column22_1 = row22.createCell(1);
        column22_1.setCellValue(new HSSFRichTextString(relate2.getArchivesTypeContent()));

        HSSFRow row23 = sheet.createRow(22);// 创建第二十三行
        HSSFCell column23 = row23.createCell(0);// 创建第一行第一列
        column23.setCellValue(new HSSFRichTextString("正使用护肤品:"));
        HSSFCell column23_1 = row23.createCell(1);
        column23_1.setCellValue(new HSSFRichTextString(relate3.getArchivesTypeContent()));

        HSSFRow row24 = sheet.createRow(23);// 创建第二十四行
        HSSFCell column24 = row24.createCell(0);// 创建第一行第一列
        column24.setCellValue(new HSSFRichTextString("其他说明:"));
        HSSFCell column24_1 = row24.createCell(1);
        column24_1.setCellValue(new HSSFRichTextString(relate4.getArchivesTypeContent()));


        HSSFRow row25 = sheet.createRow(24);// 创建第二十五行
        HSSFCell column25 = row25.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString3 = new HSSFRichTextString("专业建议(填写具体情况)");
        richTextString3.applyFont(font3);
        column25.setCellValue(richTextString3);

        HSSFRow row26 = sheet.createRow(25);// 创建第二十六行
        HSSFCell column26 = row26.createCell(0);// 创建第一行第一列
        column26.setCellValue(new HSSFRichTextString("希望改善问题:"));
        HSSFCell column26_1 = row26.createCell(1);
        column26_1.setCellValue(new HSSFRichTextString(relate5.getArchivesTypeContent()));

        HSSFRow row27 = sheet.createRow(26);// 创建第二十七行
        HSSFCell column27 = row27.createCell(0);// 创建第一行第一列
        column27.setCellValue(new HSSFRichTextString("皮肤意愿:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row27.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list18.get(i).getArchivesTypeContent()));
        }

        HSSFRow row28 = sheet.createRow(27);// 创建第二十八行
        HSSFCell column28 = row28.createCell(0);// 创建第一行第一列
        column28.setCellValue(new HSSFRichTextString("身体意愿:"));
        for (int i = 0; i < list10.size(); i++) {
            HSSFCell cleN = row28.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list19.get(i).getArchivesTypeContent()));
        }

        HSSFRow row29 = sheet.createRow(28);// 创建第二十九行
        HSSFCell column29 = row29.createCell(0);// 创建第一行第一列
        column29.setCellValue(new HSSFRichTextString("建议及注意事项:"));
        HSSFCell column29_1 = row29.createCell(1);
        column29_1.setCellValue(new HSSFRichTextString(relate6.getArchivesTypeContent()));

        HSSFRow row30 = sheet.createRow(29);// 创建第三十行
        HSSFCell column30 = row30.createCell(0);// 创建第一行第一列
        column30.setCellValue(new HSSFRichTextString("护理建议及注意事项:"));
        HSSFCell column30_1 = row30.createCell(1);
        column30_1.setCellValue(new HSSFRichTextString(relate7.getArchivesTypeContent()));

        HSSFRow row31 = sheet.createRow(30);// 创建第三十一行
        HSSFCell column31 = row31.createCell(0);// 创建第一行第一列
        column31.setCellValue(new HSSFRichTextString("产品搭配建议:"));
        HSSFCell column31_1 = row31.createCell(1);
        column31_1.setCellValue(new HSSFRichTextString(relate8.getArchivesTypeContent()));


        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 5);


        String path = "E:/finessroomExcel/skin/" + UUIDUtils.generateGUID() + ".xls";
        try {
            File file = new File("E:/finessroomExcel/skin");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            OutputStream   ouputStream = new FileOutputStream(path);
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.successMessage("导出成功,路径为:" + path);
    }

    /**
     * 导出头发档案
     * @param param
     * @return
     * @throws YJException
     */
    @PostMapping("/getHair")
    public JsonResult getHair(@RequestBody List<FrClientArchivesRelate> param) throws YJException {

        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件

        HSSFSheet sheet = workbook.createSheet("头发档案");// 创建一个Excel的Sheet

        List<FrClientArchivesRelate> list0 = new ArrayList<>();
        List<FrClientArchivesRelate> list1 = new ArrayList<>();
        List<FrClientArchivesRelate> list2 = new ArrayList<>();
        List<FrClientArchivesRelate> list3 = new ArrayList<>();
        FrClientArchivesRelate relate1 = new FrClientArchivesRelate();
        List<FrClientArchivesRelate> list4 = new ArrayList<>();
        List<FrClientArchivesRelate> list5 = new ArrayList<>();
        List<FrClientArchivesRelate> list6 = new ArrayList<>();
        List<FrClientArchivesRelate> list7 = new ArrayList<>();
        List<FrClientArchivesRelate> list8 = new ArrayList<>();

        FrClientArchivesRelate relate2 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate3 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate4 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate5 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate6 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate7 = new FrClientArchivesRelate();
        for (FrClientArchivesRelate clientArchivesRelate : param) {
            if (clientArchivesRelate.getType() == 1) {
                list0.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 2) {
                list1.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 3) {
                list2.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 5) {
                list3.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 6) {
                list4.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 7) {
                list5.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 8) {
                list6.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 9) {
                list7.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 10) {
                list8.add(clientArchivesRelate);
            }  else if (clientArchivesRelate.getType() == 11) {
                relate1 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 12) {
                relate2 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 13) {
                relate3 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 14) {
                relate4 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 15) {
                relate5 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 16) {
                relate6 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 17) {
                relate7 = clientArchivesRelate;
            }
        }
        HSSFRow row1 = sheet.createRow(0);// 创建第一行
        HSSFCell column1 = row1.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString = new HSSFRichTextString("头发实况档案");
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);
        richTextString.applyFont(font3);
        column1.setCellValue(richTextString);

        HSSFRow row2 = sheet.createRow(1);// 创建第二行
        HSSFCell column2 = row2.createCell(0);// 创建第一行第一列
        column2.setCellValue(new HSSFRichTextString("头发颜色:"));
        for (int i = 0; i < list0.size(); i++) {
            HSSFCell cleN = row2.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list0.get(i).getArchivesTypeContent()));
        }
        HSSFRow row3 = sheet.createRow(2);// 创建第三行
        HSSFCell column3 = row3.createCell(0);// 创建第一行第一列
        column3.setCellValue(new HSSFRichTextString("颜色原因:"));
        for (int i = 0; i < list1.size(); i++) {
            HSSFCell cleN = row3.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list1.get(i).getArchivesTypeContent()));
        }
        HSSFRow row4 = sheet.createRow(3);// 创建第四行
        HSSFCell column4 = row4.createCell(0);// 创建第一行第一列
        column4.setCellValue(new HSSFRichTextString("头发种类:"));
        for (int i = 0; i < list2.size(); i++) {
            HSSFCell cleN = row4.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list2.get(i).getArchivesTypeContent()));
        }
        HSSFRow row5 = sheet.createRow(4);// 创建第五行
        HSSFCell column5 = row5.createCell(0);// 创建第一行第一列
        column5.setCellValue(new HSSFRichTextString("头发受损原因:"));
        for (int i = 0; i < list3.size(); i++) {
            HSSFCell cleN = row5.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list3.get(i).getArchivesTypeContent()));
        }
        HSSFRow row6 = sheet.createRow(5);// 创建第六行
        HSSFCell column6 = row6.createCell(0);// 创建第一行第一列
        column6.setCellValue(new HSSFRichTextString("其他原因:"));
        HSSFCell column6_1 = row6.createCell(1);
        column6_1.setCellValue(new HSSFRichTextString(relate1.getArchivesTypeContent()));


        HSSFRow row7 = sheet.createRow(6);// 创建第七行
        HSSFCell column7 = row7.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString2 = new HSSFRichTextString("皮肤实况档案");
        richTextString2.applyFont(font3);
        column7.setCellValue(richTextString2);

        HSSFRow row8 = sheet.createRow(7);// 创建第八行
        HSSFCell column8 = row8.createCell(0);// 创建第一行第一列
        column8.setCellValue(new HSSFRichTextString("烫发方式:"));
        for (int i = 0; i < list4.size(); i++) {
            HSSFCell cleN = row8.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list4.get(i).getArchivesTypeContent()));
        }
        HSSFRow row9 = sheet.createRow(8);// 创建第九行
        HSSFCell column9 = row9.createCell(0);// 创建第一行第一列
        column9.setCellValue(new HSSFRichTextString("护发频率:"));
        for (int i = 0; i < list5.size(); i++) {
            HSSFCell cleN = row9.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list5.get(i).getArchivesTypeContent()));
        }
        HSSFRow row10 = sheet.createRow(9);// 创建第十行
        HSSFCell column10 = row10.createCell(0);// 创建第一行第一列
        column10.setCellValue(new HSSFRichTextString("护理方式:"));
        for (int i = 0; i < list6.size(); i++) {
            HSSFCell cleN = row10.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list6.get(i).getArchivesTypeContent()));
        }
        HSSFRow row11 = sheet.createRow(10);// 创建第十一行
        HSSFCell column11 = row11.createCell(0);// 创建第一行第一列
        column11.setCellValue(new HSSFRichTextString("喜欢头发类型:"));
        for (int i = 0; i < list7.size(); i++) {
            HSSFCell cleN = row11.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list7.get(i).getArchivesTypeContent()));
        }
        HSSFRow row12 = sheet.createRow(11);// 创建第十二行
        HSSFCell column12 = row12.createCell(0);// 创建第一行第一列
        column12.setCellValue(new HSSFRichTextString("洗发水类型:"));
        for (int i = 0; i < list8.size(); i++) {
            HSSFCell cleN = row12.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list8.get(i).getArchivesTypeContent()));
        }

        HSSFRow row13 = sheet.createRow(12);// 创建第十三行
        HSSFCell column13 = row13.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString3 = new HSSFRichTextString("专业建议(填写具体情况)");
        richTextString3.applyFont(font3);
        column13.setCellValue(richTextString3);

        HSSFRow row14 = sheet.createRow(13);// 创建第十四行
        HSSFCell column14 = row14.createCell(0);// 创建第一行第一列
        column14.setCellValue(new HSSFRichTextString("希望效果:"));
        HSSFCell column14_1 = row14.createCell(1);
        column14_1.setCellValue(new HSSFRichTextString(relate2.getArchivesTypeContent()));

        HSSFRow row15 = sheet.createRow(14);// 创建第十五行
        HSSFCell column15 = row15.createCell(0);// 创建第一行第一列
        column15.setCellValue(new HSSFRichTextString("希望颜色:"));
        HSSFCell ce15_1 = row15.createCell(1);
        ce15_1.setCellValue(new HSSFRichTextString(relate3.getArchivesTypeContent()));

        HSSFRow row16 = sheet.createRow(15);// 创建第十六行
        HSSFCell column16 = row16.createCell(0);// 创建第一行第一列
        column16.setCellValue(new HSSFRichTextString("希望发型:"));
        HSSFCell ce16_1 = row16.createCell(1);
        ce16_1.setCellValue(new HSSFRichTextString(relate4.getArchivesTypeContent()));

        HSSFRow row17 = sheet.createRow(16);// 创建第十七行
        HSSFCell column17 = row17.createCell(0);// 创建第一行第一列
        column17.setCellValue(new HSSFRichTextString("建议及注意事项:"));
        HSSFCell ce17_1 = row17.createCell(1);
        ce17_1.setCellValue(new HSSFRichTextString(relate5.getArchivesTypeContent()));


        HSSFRow row18 = sheet.createRow(17);// 创建第十八行
        HSSFCell column18 = row18.createCell(0);// 创建第一行第一列
        column18.setCellValue(new HSSFRichTextString("护理建议及注意事项:"));
        HSSFCell ce18_1 = row18.createCell(1);
        ce18_1.setCellValue(new HSSFRichTextString(relate6.getArchivesTypeContent()));

        HSSFRow row19 = sheet.createRow(18);// 创建第十九行
        HSSFCell column19 = row19.createCell(0);// 创建第一行第一列
        column19.setCellValue(new HSSFRichTextString("产品搭配建议:"));
        HSSFCell ce19_1 = row19.createCell(1);
        ce19_1.setCellValue(new HSSFRichTextString(relate7.getArchivesTypeContent()));

        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 5);


        String path = "E:/finessroomExcel/hair/" + UUIDUtils.generateGUID() + ".xls";
        try {
            File file = new File("E:/finessroomExcel/hair");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            OutputStream   ouputStream = new FileOutputStream(path);
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.successMessage("导出成功,路径为:" + path);
    }

    /**
     * 导出塑形抗衰
     * @param param
     * @return
     * @throws YJException
     */
    @PostMapping("/getPlasticity")
    public JsonResult getPlasticity(@RequestBody List<FrClientArchivesRelate> param) throws YJException {

        HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件

        HSSFSheet sheet = workbook.createSheet("塑形抗衰");// 创建一个Excel的Sheet

        List<FrClientArchivesRelate> list0 = new ArrayList<>();
        List<FrClientArchivesRelate> list1 = new ArrayList<>();
        List<FrClientArchivesRelate> list2 = new ArrayList<>();
        List<FrClientArchivesRelate> list3 = new ArrayList<>();
        List<FrClientArchivesRelate> list4 = new ArrayList<>();
        List<FrClientArchivesRelate> list5 = new ArrayList<>();
        List<FrClientArchivesRelate> list6 = new ArrayList<>();
        List<FrClientArchivesRelate> list7 = new ArrayList<>();
        List<FrClientArchivesRelate> list8 = new ArrayList<>();
        FrClientArchivesRelate relate1 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate2 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate3 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate4 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate5 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate6 = new FrClientArchivesRelate();
        FrClientArchivesRelate relate7 = new FrClientArchivesRelate();
        for (FrClientArchivesRelate clientArchivesRelate : param) {
            if (clientArchivesRelate.getType() == 1) {
                list0.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 2) {
                list1.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 3) {
                list2.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 4) {
                list3.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 5) {
                list4.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 6) {
                list5.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 7) {
                list6.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 8) {
                list7.add(clientArchivesRelate);
            } else if (clientArchivesRelate.getType() == 9) {
                relate1 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 10) {
                relate2 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 11) {
                relate3 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 12) {
                relate4 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 13) {
                relate5 = clientArchivesRelate;
            } else if (clientArchivesRelate.getType() == 14) {
                relate6 = clientArchivesRelate;
            }
        }
        HSSFRow row1 = sheet.createRow(0);// 创建第一行
        HSSFCell column1 = row1.createCell(0);// 创建第一行第一列
        HSSFRichTextString richTextString = new HSSFRichTextString("塑形抗衰档案");
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLUE.index);
        richTextString.applyFont(font3);
        column1.setCellValue(richTextString);

        HSSFRow row2 = sheet.createRow(1);// 创建第二行
        HSSFCell column2 = row2.createCell(0);// 创建第一行第一列
        column2.setCellValue(new HSSFRichTextString("脸型:"));
        for (int i = 0; i < list0.size(); i++) {
            HSSFCell cleN = row2.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list0.get(i).getArchivesTypeContent()));
        }
        HSSFRow row3 = sheet.createRow(2);// 创建第三行
        HSSFCell column3 = row3.createCell(0);// 创建第一行第一列
        column3.setCellValue(new HSSFRichTextString("鼻型:"));
        for (int i = 0; i < list1.size(); i++) {
            HSSFCell cleN = row3.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list1.get(i).getArchivesTypeContent()));
        }
        HSSFRow row4 = sheet.createRow(3);// 创建第四行
        HSSFCell column4 = row4.createCell(0);// 创建第一行第一列
        column4.setCellValue(new HSSFRichTextString("胸型:"));
        for (int i = 0; i < list2.size(); i++) {
            HSSFCell cleN = row4.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list2.get(i).getArchivesTypeContent()));
        }
        HSSFRow row5 = sheet.createRow(4);// 创建第五行
        HSSFCell column5 = row5.createCell(0);// 创建第一行第一列
        column5.setCellValue(new HSSFRichTextString("腰型:"));
        for (int i = 0; i < list3.size(); i++) {
            HSSFCell cleN = row5.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list3.get(i).getArchivesTypeContent()));
        }
        HSSFRow row6 = sheet.createRow(5);// 创建第六行
        HSSFCell column6 = row6.createCell(0);// 创建第一行第一列
        column6.setCellValue(new HSSFRichTextString("手型:"));
        for (int i = 0; i < list4.size(); i++) {
            HSSFCell cleN = row6.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list4.get(i).getArchivesTypeContent()));
        }


        HSSFRow row7 = sheet.createRow(6);// 创建第七行
        HSSFCell column7 = row7.createCell(0);// 创建第一行第一列
        column7.setCellValue(new HSSFRichTextString("腿型:"));
        for (int i = 0; i < list5.size(); i++) {
            HSSFCell cleN = row7.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list5.get(i).getArchivesTypeContent()));
        }

        HSSFRow row8 = sheet.createRow(7);// 创建第八行
        HSSFCell column8 = row8.createCell(0);// 创建第一行第一列
        column8.setCellValue(new HSSFRichTextString("臀型:"));
        for (int i = 0; i < list6.size(); i++) {
            HSSFCell cleN = row8.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list6.get(i).getArchivesTypeContent()));
        }
        HSSFRow row9 = sheet.createRow(8);// 创建第九行
        HSSFCell column9 = row9.createCell(0);// 创建第一行第一列
        column9.setCellValue(new HSSFRichTextString("过敏史:"));
        for (int i = 0; i < list7.size(); i++) {
            HSSFCell cleN = row9.createCell(i + 1);
            cleN.setCellValue(new HSSFRichTextString(list7.get(i).getArchivesTypeContent()));
        }
        HSSFRow row10 = sheet.createRow(9);// 创建第十行
        HSSFCell column10 = row10.createCell(0);// 创建第一行第一列
        column10.setCellValue(new HSSFRichTextString("希望效果:"));
        HSSFCell column10_1 = row10.createCell(1);
        column10_1.setCellValue(new HSSFRichTextString(relate1.getArchivesTypeContent()));


        HSSFRow row11 = sheet.createRow(10);// 创建第十一行
        HSSFCell column11 = row11.createCell(0);// 创建第一行第一列
        column11.setCellValue(new HSSFRichTextString("用到产品:"));
        HSSFCell column11_1 = row11.createCell(1);
        column11_1.setCellValue(new HSSFRichTextString(relate2.getArchivesTypeContent()));


        HSSFRow row12 = sheet.createRow(11);// 创建第十二行
        HSSFCell column12 = row12.createCell(0);// 创建第一行第一列
        column12.setCellValue(new HSSFRichTextString("专家建议:"));
        HSSFCell column12_1 = row12.createCell(1);
        column12_1.setCellValue(new HSSFRichTextString(relate3.getArchivesTypeContent()));

        HSSFRow row13 = sheet.createRow(12);// 创建第十三行
        HSSFCell column13 = row13.createCell(0);// 创建第一行第一列
        column13.setCellValue(new HSSFRichTextString("拟定时间:"));
        HSSFCell column13_1 = row13.createCell(1);
        column13_1.setCellValue(new HSSFRichTextString(relate4.getArchivesTypeContent()));

        HSSFRow row14 = sheet.createRow(13);// 创建第十四行
        HSSFCell column14 = row14.createCell(0);// 创建第一行第一列
        column14.setCellValue(new HSSFRichTextString("实际情况:"));
        HSSFCell column14_1 = row14.createCell(1);
        column14_1.setCellValue(new HSSFRichTextString(relate5.getArchivesTypeContent()));

        HSSFRow row15 = sheet.createRow(14);// 创建第十五行
        HSSFCell column15 = row15.createCell(0);// 创建第一行第一列
        column15.setCellValue(new HSSFRichTextString("禁忌:"));
        HSSFCell ce15_1 = row15.createCell(1);
        ce15_1.setCellValue(new HSSFRichTextString(relate6.getArchivesTypeContent()));

        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 5);


        String path = "E:/finessroomExcel/plasticity/" + UUIDUtils.generateGUID() + ".xls";
        try {
            File file = new File("E:/finessroomExcel/plasticity");
            if (!file.isDirectory()) {
                file.mkdirs();
            }
            OutputStream   ouputStream = new FileOutputStream(path);
            workbook.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.successMessage("导出成功,路径为:" + path);
    }
}


