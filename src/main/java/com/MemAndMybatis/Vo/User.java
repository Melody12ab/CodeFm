package com.MemAndMybatis.Vo;

import java.io.Serializable;

/**
 * Created by xiaobai on 15-11-10.
 */
public class User implements Serializable{
    private static final long seralVersionUID=-3896608900471191953L;
    private int uid;
    private String uname;
    private String upass;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }
}
