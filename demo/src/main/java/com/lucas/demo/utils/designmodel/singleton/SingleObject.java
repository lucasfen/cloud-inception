package com.lucas.demo.utils.designmodel.singleton;

/**
 * @author Lucasfen
 * @Date 2021/06/24
 */
public class SingleObject {

    private static SingleObject instance = new SingleObject();

    private SingleObject(){}

    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello world!");
    }
}
