package com.yj.web;

import com.yj.common.util.ExportExcel;
import com.yj.dal.dto.MyPotentialClientDTO;

import javax.swing.*;
import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args)
    {
        // 测试学生
        ExportExcel<MyPotentialClientDTO> ex = new ExportExcel<MyPotentialClientDTO>();
        String[] headers =
                { "会员姓名", "性别", "联系方式", "保护天数", "微信"
                ,"总联系数", "客户等级", "意向门店", "渠道来源", "跟进总次数"
                ,"最早消费时间", "最近消费时间", "消费总数", "频率(天/次)", "建档时间"
                ,"最近来访时间", "购买意向", "服务会籍", "跟进人", "首次跟进时间"
                 ,"最近跟进时间", "跟进内容", "意向卡类别", "意向卡名称", "意向卡价格"
                };
        List<MyPotentialClientDTO> dataset = new ArrayList<MyPotentialClientDTO>();

        MyPotentialClientDTO potentialClientDTO=new MyPotentialClientDTO();
        potentialClientDTO.setClientName("张三");
        potentialClientDTO.setSex(0);
        potentialClientDTO.setMobile("136987456");
        potentialClientDTO.setProtectDay(10);
        potentialClientDTO.setContactCount(3);
        dataset.add(potentialClientDTO);
        // 测试图书
//        ExportExcel<Book> ex2 = new ExportExcel<Book>();
//        String[] headers2 =
//                { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社", "封面图片" };
//        List<Book> dataset2 = new ArrayList<Book>();
        try
        {
//            BufferedInputStream bis = new BufferedInputStream(
//                    new FileInputStream("d://book.bmp"));
//            byte[] buf = new byte[bis.available()];
//            while ((bis.read(buf)) != -1)
//            {
//                //
//            }

            OutputStream out = new FileOutputStream("E://a.xls");
//            OutputStream out2 = new FileOutputStream("E://b.xls");
            ex.exportExcel(headers, dataset, out);
//            ex2.exportExcel(headers2, dataset2, out2);
            out.close();
            JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
