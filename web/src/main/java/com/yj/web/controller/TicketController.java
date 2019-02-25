package com.yj.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yj.common.exception.YJException;
import com.yj.common.result.JsonResult;
import com.yj.common.util.PageUtils;
import com.yj.dal.param.PostDelTicketParam;
import com.yj.service.service.ITicketService;


@RestController
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private ITicketService ticketService;
	@GetMapping("/getTicketList")
	    public JsonResult getTicketList() throws YJException {
		  PageUtils planList =  ticketService.getTicketList();
	        if (planList != null) {
	            return JsonResult.success(planList);
	        } else {
	            return JsonResult.fail();
	        }
	    }
	
	@GetMapping("/getTicketSet")
    public JsonResult getTicketSet() throws YJException {
	  PageUtils planList =  ticketService.getTicketSet();
        if (planList != null) {
            return JsonResult.success(planList);
        } else {
            return JsonResult.fail();
        }
    }
	@PostMapping("/postDelTicket")
	public JsonResult postDelTicket(@RequestBody PostDelTicketParam postDelTicketParam)  throws YJException{
		
		
		 	if ("1".equals(ticketService.PostDelTicket(postDelTicketParam))) {
	            return JsonResult.success();
	        } else {
	            return JsonResult.fail();
	        }
	}
}
