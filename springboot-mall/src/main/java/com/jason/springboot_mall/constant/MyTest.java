package com.jason.springboot_mall.constant;

public class MyTest {
    public static void main(String[] args) {
        ProductCategory category=ProductCategory.FOOD;
        System.out.println(category);

        String s2="CAR";
        ProductCategory category1=ProductCategory.valueOf(s2);
    }
}
