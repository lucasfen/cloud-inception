package com.lucas.demo.utils.designmodel.singleton;

/**
 * @author Lucasfen
 * @Date 2021/06/24
 */
public class SingletonDemo {

    public static void main(String[] args) {
        SingleObject singleObject = SingleObject.getInstance();
        singleObject.showMessage();
    }
}
