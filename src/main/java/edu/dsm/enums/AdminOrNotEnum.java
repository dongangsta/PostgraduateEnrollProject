package edu.dsm.enums;

public enum AdminOrNotEnum {
    ADMIN((byte) 1,"管理员"),
    USER((byte)0,"用户");
    private byte code;
    private String identity;

    AdminOrNotEnum(byte code, String identity) {
        this.code = code;
        this.identity = identity;
    }
}
