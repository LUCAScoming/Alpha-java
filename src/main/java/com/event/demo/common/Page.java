package com.event.demo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

    public static final int defaultCurrent = 1;
    public static final int defaultSize = 10;

    private static final long serialVersionUID = 1L;

    private int totalPage = 1;

    private int current = 1;

    private int size = 10;

    private long totalElements = 0;

    private List<T> list = new ArrayList<>();

    public Page(){}
    public Page(com.github.pagehelper.Page<T> helperPage) {
        this.totalElements = helperPage.getTotal();
        this.totalPage = helperPage.getPages();
        this.current = helperPage.getPageNum();
        this.size = helperPage.getPageSize();
        this.list = helperPage.getResult();
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return current;
    }

    public void setCurrentPage(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
