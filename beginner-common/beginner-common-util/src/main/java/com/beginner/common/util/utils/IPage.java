package com.beginner.common.util.utils;

import java.util.List;

public interface IPage<T> {
    //获取页码
    int getPageNum();
//获取每页显示的记录数
    int getPageSize();
//获取当前页的记录
    List<T> getRecords();
//设置页码
    IPage<T> setPageNum(int pageNum);
//设置每页显示的记录数
    IPage<T> setPageSize(int pageSize);
//设置当前页的记录
    IPage<T> setRecords(List<T> records);
//获取总记录数
    int getTotal();
//设置总记录数
    IPage<T> setTotal(int total);
//是否进行总数统计
    boolean isCount();
//设置是否进行总数统计
    IPage<T> setCount(boolean count);
}
