package com.qw.enums;


/**
 * @Desc: y/n enum
 */
public enum CommentLevel {
    GOOD(1, "good"),
    NORMAL(2, "NORMAL"),
    BAD(3, "BAD");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value){
        this.type=type;
        this.value=value;
    }



}
