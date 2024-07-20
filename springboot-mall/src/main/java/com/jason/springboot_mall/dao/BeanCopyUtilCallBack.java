package com.jason.springboot_mall.dao;

@FunctionalInterface
public interface BeanCopyUtilCallBack <S,T>
{
    void callBack(S t, T s);
}
