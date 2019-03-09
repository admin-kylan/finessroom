package com.yj.service.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.yj.dal.dao.CustomerConsumeHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className FrCustomerConsumeHistoryServiceImpl
 * by Kylan
 *
 * @author Kylan
 * @date 2019-03-01 23:23
 */
@Service
public class FrCustomerConsumeHistoryServiceImpl {


    @Autowired
    private CustomerConsumeHistoryMapper customerConsumeHistoryMapper;


    public Page findConsumeList(String clientId, String beginDate, String endDate, String pageIndex, String pageSize, String code){
        Page page = new Page();
        List<Map<String, Object>> list = customerConsumeHistoryMapper.findConsumeList(pageSize, clientId, code, beginDate, endDate, pageIndex);
        Map<String, Integer> map = customerConsumeHistoryMapper.findConsumeListCount(pageSize, clientId, code, beginDate, endDate, pageIndex);
        page.setSize(Integer.parseInt(pageSize));
        page.setTotal(map.get("totalSize"));
        page.setRecords(list);
        return page;
    }
}
