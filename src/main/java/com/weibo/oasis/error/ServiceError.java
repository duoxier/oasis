package com.weibo.oasis.error;

import com.ne.boot.common.exception.IError;

public enum ServiceError implements IError {
    OASIS_SERVICE_ERROR("9999", "内部服务错误"),
    PHONE_EXIST("0001", "手机号已存在"),
    USERNAME_EXIST("0002", "用户名已存在"),
    EMAIL_EXIST("0003", "邮箱已存在"),
    USER_NOT_EXIST("0004", "用户不存在"),
    PASSWORD_NULL("0005","密码不能为空"),
    STATUS_NULL("0006", "用户状态不能为空"),
    PHONE_OR_PASSWORD_INCORRECT("0007","手机号或密码错误"),
    USER_STATUS_ERROR("0008","用户状态不正确，请联系管理员"),
    PHONE_NULL("0009", "手机号不能为空"),
    TOKEN_ERROR("0010", "无效token"),
    TOKEN_EXPIRED("0011","用户登录已失效"),
    USERNAME_NULL("9999","用户名不能为空")
    ;

    String errorCode;
    String errorMessage;
    private static final String ns = "OASIS";

    ServiceError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getNamespace() {
        return ns;
    }

    @Override
    public String getErrorCode() {
        return ns + "_" + errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
