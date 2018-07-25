package me.mingshan.config.web;

import me.mingshan.config.JdbcConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    JdbcConfigBean jdbcConfigBean;

    @RequestMapping(value = "/hi")
    public String hi(){
        return jdbcConfigBean.toString();
    }
}
