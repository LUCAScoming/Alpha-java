package com.event.demo.common.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class SelectBySqlProvider extends MapperTemplate {


    public SelectBySqlProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectBySqlAndCondition(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        setResultType(ms,entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass,this.tableName(entityClass)));
        sql.append(SqlHelper.whereAllIfColumns(entityClass,this.isNotEmpty()));
//        sql.append( "<if test=\"orderBySql != null\">${orderBySql}</if>");
        return sql.toString();
    }
}
