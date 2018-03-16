package com.yuyu.vo;

//import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class User {
    String name;
    String pass;
    /*
     如果不想返回remarks
     deserialize是否需要序列化属性
     */
//    @JSONField(serialize = false)
    String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    Date createTime;//创建时间
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}