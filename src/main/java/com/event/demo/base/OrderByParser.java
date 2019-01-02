package com.event.demo.base;


import com.event.demo.base.constant.OrderBy;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wu
 * @date 2018/08/24
 * @Description:
 */
public class OrderByParser {
    public static final String PLUS_TOKEN = "+";
    public static final String MINUS_TOKEN = "-";

    public List<OrderByCondition> parse(String orderBy) {
        List<OrderByCondition> orderByConditions = new ArrayList<>();
        if (StringUtils.isBlank(orderBy)) {
            return orderByConditions;
        }
        String[] orderBys = orderBy.split(",");
        for (int i = 0; i < orderBys.length; i++) {
            String s = orderBys[i];
            if (s.indexOf(PLUS_TOKEN) != -1) {
                orderByConditions.add(new OrderByCondition(s.substring(0, s.indexOf(PLUS_TOKEN)), OrderBy.ASC));
            } else if (s.indexOf(MINUS_TOKEN) != -1) {
                orderByConditions.add(new OrderByCondition(s.substring(0, s.indexOf(MINUS_TOKEN)), OrderBy.DESC));
            }
        }
        return orderByConditions;
    }

    public class OrderByCondition {
        private String field;
        private OrderBy orderBy;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public OrderBy getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(OrderBy orderBy) {
            this.orderBy = orderBy;
        }

        public OrderByCondition(String field, OrderBy orderBy) {
            this.field = field;
            this.orderBy = orderBy;
        }
    }
}
