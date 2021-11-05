package com.lucas.demo.utils.designmodel.factory;

/**
 * @author Lucasfen
 * @Date 2021/06/24
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
