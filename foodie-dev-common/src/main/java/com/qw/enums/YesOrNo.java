package com.qw.enums;


/**
 * @Desc: y/n enum
 */
public enum YesOrNo {
    NO(0, "no"),
    YES(1, "yes");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value){
        this.type=type;
        this.value=value;
    }



}
