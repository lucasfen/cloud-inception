package com.lucas.demo.utils.designmodel.builder;

/**
 * @author Lucasfen
 * @Date 2021/06/25
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
