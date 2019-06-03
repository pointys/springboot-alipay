package com.kicol.git;

import java.io.Serializable;

/**
 * @Author: 韩老魔
 * 后端分支切换
 * @Date: 2019/6/3 0003 12:39
 */
public class gitTest implements Serializable {
    private static final long serialVersionUID = -3606178821921526644L;

    private Integer id;
    private String name;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
