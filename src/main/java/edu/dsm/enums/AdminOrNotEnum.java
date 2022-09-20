package edu.dsm.enums;

public enum AdminOrNotEnum {
    ADMIN(1,"管理员"),
    USER(0,"用户");
    private Integer code;
    private String identity;

    AdminOrNotEnum(Integer code, String identity) {
        this.code = code;
        this.identity = identity;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
