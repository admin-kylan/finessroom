package com.yj.web.controller;

import com.yj.service.service.impl.FrEducationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * className FrEducationAppRest
 * by Kylan
 *
 * @author Kylan
 * @date 2019-01-05 11:22
 */
@RestController
@RequestMapping("/frEducation")
public class FrEducationController {


    @Autowired
    private FrEducationServiceImpl frEducationService;
}
