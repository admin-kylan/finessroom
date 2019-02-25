package com.yj.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.dal.model.Department;
import com.yj.service.service.IDepartmentService;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author MP自动生成
 * @since 2019-02-25
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	IDepartmentService service;

	@GetMapping("/queryDepartmentList")
	public JsonResult queryDepartmentList() throws YJException {
		//service.sel
		//PageUtils planList = service.getServiceRecordList(cid, currPage, status);
		List<Department> list = service.selectList(null);
//		System.out.println("Department>>>"+list);
		for (Department department : list) {
			System.out.println(department.getDepartName()+"<<Department>>>"+department);
			

		}
		if (list != null) {
			return JsonResult.success(list);
		} else {
			return JsonResult.fail();
		}

	}
}
