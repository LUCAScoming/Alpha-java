package com.event.demo.common;

import lombok.Data;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xiang
 */
@Data
public class PageQuery {
    public int current;
    public int size;
    private Map<String,String> orders;

    public RowBounds getRowBounds(){
        return new RowBounds((current-1)*size,size);
    }

    public PageQuery(){
        current = 1;
        size = 10;
        orders= new HashMap<>();
    }

    public Map<String, String> getOrders() {

        return orders;
    }

    public void setOrders(Map<String, String> orders) {
        this.orders = orders;
    }
}
