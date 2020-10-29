package com.common.result;

public class StatusCode {

    public static final int OK=2000;  //成功

    public static final int ERROR=3000;  //失败

    public static final int LOGINERROR=30001;  // 用户名或者密码错误

    public static final int ACCESSERROR=30002;  //权限不足

    public static final int REERROR=30003;  //重复操作

    public static final int FEIGNERROR=30004;  //远程调用失败

}
