package com.lucas.demo.controller.testcontroller;

import com.lucas.demo.dto.activiti.CompleteApproveReqData;
import com.lucas.demo.service.ActivitiService;
import jodd.introspector.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/10/23
 */
@RestController
@RequestMapping("/test/activiti")
public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    @PostMapping("/completeApprove")
    public void completeApprove(
            @RequestBody CompleteApproveReqData request) {
        activitiService.completeApprove(request);
    }

}
