package com.lucas.demo.service;

import javax.annotation.Resource;

import com.lucas.dal.mysql.mapper.lucasoperation.CommissionMapper;
import com.lucas.dal.mysql.mapper.lucasoperation.RedeemCashMapper;
import com.lucas.dal.mysql.mapper.lucasoperation.RedeemCouponMapper;
import com.lucas.dal.mysql.model.lucasoperation.Commission;
import com.lucas.dal.mysql.model.lucasoperation.RedeemCash;
import com.lucas.dal.mysql.model.lucasoperation.RedeemCoupon;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lucasfen
 * @Date 2021/05/20
 */
@Service
public class ValidateService {

    @Resource
    private CommissionMapper commissionMapper;

    @Resource
    private RedeemCouponMapper redeemCouponMapper;

    public void test() {
        Commission commission = new Commission();
        commission.setId(1);
        commission.setAccountId("2");
        commission.setAvailableCommission(10.0);
        commission.setRedeemCommission(20.0);
        int result = commissionMapper.updateByPrimaryKeySelective(commission);
        System.out.println(result);
        RedeemCoupon redeemCoupon = new RedeemCoupon();
        redeemCoupon.setAccountId("1");
        redeemCoupon.setCouponId(1);
        int resultInsert = redeemCouponMapper.insertSelective(redeemCoupon);
        System.out.println(resultInsert);
        throw new RuntimeException();
    }
}
