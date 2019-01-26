package com.yj.common.util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * 导入Excel
 */
public class ImportExcelUtils {
    private static Logger logger = Logger.getLogger(ImportExcelUtils.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回
     *
     * @param file
     * @param flag
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> readExcel(MultipartFile file, int flag) throws IOException {
        // 检查文件
        checkFile(file);
        // 获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        // 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<Map<String, Object>> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                // 获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                // 获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                // 获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                // 循环除了第一行的所有行
                for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++) {
                    // 获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    // 获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    // 循环当前行
                    Map<String, Object> map = new HashMap<>();
                    int number = 0;
                    for (int cellNum = firstCellNum; cellNum >= 0; cellNum++) {
                        if (number == lastCellNum) {
                            break;
                        }
                        Cell cell = row.getCell(cellNum);
                        if (cell != null) {
                            number++;
                        }
                        map.putAll(excelToMap(getCellValue(cell), cellNum, flag));
                    }
                    // for (int cellNum = firstCellNum; cellNum < lastCellNum;
                    // cellNum++) {
                    // Cell cell = row.getCell(cellNum);
                    // map.putAll(excelToMap(getCellValue(cell), cellNum,
                    // flag));
                    // }
                    list.add(map);
                }
            }
            workbook.close();
        }
        return list;
    }

    public static void checkFile(MultipartFile file) throws IOException {
        // 判断文件是否存在
        if (null == file) {
            logger.error("文件不存在！");
            throw new FileNotFoundException("文件不存在！");
        }
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            InputStream is = file.getInputStream();
            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                // 2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                // 2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return workbook;
    }

    @SuppressWarnings("deprecation")
    public static Object getCellValue(Cell cell) {
        Object cellValue = null;
        if (cell == null) {
            return cellValue;
        }

        // 把数字当成String来读，避免出现1读成1.0的情况

        // 判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    if (org.apache.poi.ss.usermodel.DateUtil.isValidExcelDate(cell.getNumericCellValue())) {
                        CellStyle style = cell.getCellStyle();
                        if (style == null)
                            return false;
                        int i = style.getDataFormat();
                        String f = style.getDataFormatString();
                        boolean isDate = org.apache.poi.ss.usermodel.DateUtil.isADateFormat(i, f);
                        if (isDate) {
                            Date date = cell.getDateCellValue();
                            return cellValue = date;
                        }

                    }
                    // cell.setCellType(Cell.CELL_TYPE_STRING);
                }
                if ((int) cell.getNumericCellValue() != cell.getNumericCellValue()) {
                    // double 类型
                    cellValue = cell.getNumericCellValue();
                } else {
                    cellValue = (int) cell.getNumericCellValue();
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = null;
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    /**
     * excel 转换数据
     *
     * @param cellValue 获取到的参数
     * @param cellNum   位数
     * @param flag      类型
     * @return
     */
    private static Map<String, Object> excelToMap(Object cellValue, int cellNum, int flag) {
        Map<String, Object> map = new HashMap<>();
        switch (flag) {
            // 判断类型 1：现有会员
            case 1:
                switch (cellNum) {
                    case 0: // 门店名称
                        map.put("shopName", cellValue);
                        break;
                    case 1: // 姓名
                        map.put("clientName", cellValue);
                        break;
                    case 2: // 性别
                        if (cellValue instanceof String) {
                            String sex = (String) cellValue;
                            switch (sex) {
                                case "男":
                                    map.put("sex", 0);
                                    break;
                                case "女":
                                    map.put("sex", 1);
                                    break;
                                default:
                                    logger.debug("性别错误...");
                            }
                        } else {
                            logger.debug("性别错误...");
                        }
                        break;
                    case 3: // 生日
                        map.put("birthday", cellValue);
                        break;
                    case 4: // 手机号码
                        map.put("mobile", cellValue);
                        break;
                    case 5: // 卡号
                        map.put("cardNo", cellValue);
                        break;

                    //fr_card_type
                    case 6:// 会员卡种
                        map.put("cardFlag", cellValue);
                        break;
                    case 7://会员卡名
                        map.put("cardTypeName",cellValue);
                    case 8:// 卡价格
                        map.put("originalPrice", cellValue);
                        break;
                    case 9:// 购卡价格
                        map.put("needPrice", cellValue);
                        break;
                    case 10:// 刷卡
                        map.put("swipingCard", cellValue);
                        break;
                    case 11:// 现金
                        map.put("cash", cellValue);
                        break;
                    case 12:// 微信
                        map.put("wechat", cellValue);
                        break;
                    case 13:// 支付宝
                        map.put("alipay", cellValue);
                        break;
                    case 14:// 销售员
                        map.put("consultantName", cellValue);
                        break;
                    case 15:// 服务会籍
                        map.put("fwhjName", cellValue);
                        break;
                    case 16:// 有效时长
                        map.put("validTime", cellValue);
                        break;
                    case 17:// 开卡时间
                        map.put("bindTime", cellValue);
                        break;
                    case 18:// 失效时间
                        map.put("invalidTime", cellValue);
                        break;
                    case 19:// 卡状态
                        map.put("cardStatus", cellValue);
                        break;
                    case 20:// 购买权益
                        map.put("buyRightsNum", cellValue);
                        break;
                    case 21:// 赠送权益
                        map.put("giveRightsNum", cellValue);
                        break;
                    case 22:// 可用权益
                        map.put("haveRightsNum", cellValue);
                        break;
                    case 23:// 剩余次数或小时数
                        map.put("businessScope", cellValue);
                        break;
                    case 24:// 剩余金额
                        map.put("cardHavePrice", cellValue);
                        break;
                    case 25:// 备注
                        map.put("note", cellValue);
                        break;
                    case 26:// 操作时间
                        map.put("createTime", cellValue);
                        break;
                    case 27:// 操作人
                        map.put("createUserName", cellValue);
                        break;

                        //协议号规则表
                    case 28:// 协议编号
                        map.put("agreementNo", cellValue);
                        break;

                        //根据父考号 查询会员卡列表   关联id添加到 子卡表
                    case 29:// 增购父卡号
                        map.put("parentCardNo", cellValue);
                        break;
                    case 30:// 储值
                        map.put("businessScope", cellValue);
                        break;
                    case 31:// 会员等级
                        map.put("level", cellValue);
                        break;

                        //会员卡停卡、订单冻结
                    case 32:// 开始停卡时间
                        map.put("startTime", cellValue);
                        break;
                    case 33:// 结束停卡时间
                        map.put("estEndTime", cellValue);
                        break;

                    case 34:// 付款类型
                        map.put("businessScope", cellValue);
                        break;
                    case 35:// 停卡收费
                        map.put("totalPrice", cellValue);
                        break;
                    case 36:// 停卡原因
                        map.put("flag", cellValue);
                        break;

                        //子卡表
                    case 37:// 外部卡号
                        map.put("outCardNo", cellValue);
                        break;
                }
                break;
            //潜在客户
            case 2:
                switch (cellNum) {
                    case 0: // 门店名称
                        map.put("shopName", cellValue);
                        break;
                    case 1: // 姓名
                        map.put("clientName", cellValue);
                        break;
                    case 2: // 性别
                        if (cellValue instanceof String) {
                            String sex = (String) cellValue;
                            switch (sex) {
                                case "男":
                                    map.put("sex", 0);
                                    break;
                                case "女":
                                    map.put("sex", 1);
                                    break;
                                default:
                                    logger.debug("性别错误...");
                            }
                        } else {
                            logger.debug("性别错误...");
                        }
                        break;
                    case 3: // 生日
                        map.put("birthday", cellValue);
                        break;
                    case 4: // 手机号码
                        map.put("mobile", cellValue);
                        break;
                    case 5:// 销售员
                        map.put("salespersonName", cellValue);
                        break;
                    case 6:// 备注
                        map.put("note", cellValue);
                        break;
                    case 7:// 操作时间
                        map.put("createTime", cellValue);
                        break;
                    case 8:// 操作人
                        map.put("createUserName", cellValue);
                        break;
                }
                break;
        }
        return map;
    }


    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            //false   文件名不是excel格式;
            return false;
        }
        return true;
    }

    /**
     * 是否是2003的excel，返回true是2003
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel，返回true是2007
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}

