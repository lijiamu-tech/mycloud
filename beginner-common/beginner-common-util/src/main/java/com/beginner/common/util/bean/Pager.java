package com.beginner.common.util.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager<T> {
    // 分页参数
    private Integer page;
    private Integer size;
    // 数据
    private T data;
    // 总数
    private Long total;
    // 行数据
    private List<T> rows;
}
