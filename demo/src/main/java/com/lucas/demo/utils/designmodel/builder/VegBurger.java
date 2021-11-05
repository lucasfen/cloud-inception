package com.lucas.demo.utils.designmodel.builder;

/**
 * @author Lucasfen
 * @Date 2021/06/25
 */
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
