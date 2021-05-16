package com.lucas.demo.service;

import com.lucas.demo.dto.CouponTypeEnum;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author Lucasfen
 * @Date 2021/05/08
 */
@Service
public class ValidateService {

    public void validateTest(ValidateTestReq validateTestReq) {
        System.out.println("test");
    }


    @Data
    public static class ValidateTestReq {

        private CouponTypeEnum couponType;
    }
}
