package com.lucas.demo.utils.designmodel.factory;

/**
 * @author Lucasfen
 * @Date 2021/06/24
 */
public class FactoryDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = shapeFactory.getShape("rectangle");
        shape2.draw();
        Shape shape3 = shapeFactory.getShape("square");
        shape3.draw();
    }
}
