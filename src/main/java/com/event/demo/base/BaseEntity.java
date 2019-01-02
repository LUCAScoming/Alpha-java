package com.event.demo.base;

import java.io.Serializable;

/**
 * @author:菲你莫属123
 * @description:
 **/
public abstract class BaseEntity<PK extends Serializable> implements Serializable{


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     * @return 返回实体类的主键
     */
    abstract public PK getPK();

    /**
     *
     */
    public void setPK(PK pk){

    }

}
