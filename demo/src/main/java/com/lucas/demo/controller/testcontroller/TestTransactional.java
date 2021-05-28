package com.lucas.demo.controller.testcontroller;

import com.lucas.dal.mysql.mapper.lucasoperation.CommissionMapper;
import com.lucas.dal.mysql.mapper.lucasoperation.RedeemCouponMapper;
import com.lucas.dal.mysql.model.lucasoperation.Commission;
import com.lucas.dal.mysql.model.lucasoperation.RedeemCoupon;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucasfen
 * @Date 2021/05/27
 */
@RestController
@RequestMapping("/test/transactional")
public class TestTransactional {

    @Autowired
    private CommissionMapper commissionMapper;

    @Autowired
    private RedeemCouponMapper redeemCouponMapper;

    @PostMapping("/dal")
    public void testTransactional() {
        updateCommission();
        insertRedeemCoupon();
    }

    @Transactional
    public void insertRedeemCoupon() {
       RedeemCoupon redeemCoupon = new RedeemCoupon();
       redeemCoupon.setAccountId("1234");
       redeemCoupon.setCouponId(1);
       redeemCouponMapper.insertSelective(redeemCoupon);
    }

    @Transactional
    public void updateCommission() {
        Commission commission = commissionMapper.selectByPrimaryKey(1);
        commission.setRedeemCommission(commission.getRedeemCommission() + 10);
        commission.setAvailableCommission(commission.getAvailableCommission() - 10);
        commissionMapper.updateByPrimaryKeySelective(commission);
        throw new RuntimeException();
    }
}
