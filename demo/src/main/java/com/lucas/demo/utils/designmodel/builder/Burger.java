package com.lucas.demo.utils.designmodel.builder;

/**
 * @author Lucasfen
 * @Date 2021/06/24
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
