package com.lucas.demo.utils.designmodel.builder;

/**
 * @author Lucasfen
 * @Date 2021/06/25
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
