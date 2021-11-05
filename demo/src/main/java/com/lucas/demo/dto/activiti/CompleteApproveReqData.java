package com.lucas.demo.dto.activiti;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Lucasfen
 * @Date 2021/10/23
 */
@Data
public class CompleteApproveReqData {
    @NotNull(message = "bizType should not be null")
    private Integer bizType;
    @NotNull(message = "申请单ID未传入")
    private Integer id;
    @NotBlank(message = "流程ID未传入")
    private String instanceId;
    private String suggest;
}
