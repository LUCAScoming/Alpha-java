package com.event.demo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wu
 * @date 2018/08/24
 * @Description:
 */
@ApiModel(value="通用分页查询Vo")
@Data
public class BaseQueryVo<T> {
    @ApiModelProperty(value="实体查询条件,传对应实体的json")
    private T t;
    @ApiModelProperty(value="页码")
    private int page = 1;
    @ApiModelProperty(value="每页数量")
    private int pageSize = 10;
    @ApiModelProperty(value="排序条件，如按id正序查询：'id+'")
    private String orderBy;

}
