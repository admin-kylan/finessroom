package com.yj.service.service;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;

import java.util.List;
import java.util.Map;

public interface IImportExcelService {

    /**
     * 现有客户信息导入
     * @param list
     * @return
     */
    JsonResult clientUpload(List<Map<String, Object>> list);

    /**
     * 潜在客户导入
     * @param list
     * @return
     */
    JsonResult prospectiveClient(List<Map<String, Object>> list);

    /**
     * 私教项目导入
     * @param list
     * @return
     */
    JsonResult personalTrainer(List<Map<String, Object>> list);
}
