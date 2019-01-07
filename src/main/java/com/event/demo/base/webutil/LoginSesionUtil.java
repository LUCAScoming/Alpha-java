package com.event.demo.base.webutil;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:Lucas
 * @description:
 * @Date:2019/1/7
 **/
@Data
public class LoginSesionUtil {
    private final static Logger LOG = LoggerFactory.getLogger(com.yisi.stiku.web.util.LoginSesionUtil.class);
    private String UserName;

}
