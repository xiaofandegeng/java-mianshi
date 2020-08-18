package com.ning4256.countDownLatch;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum CountLatchDownEnum {
    ONE(1, "赵国"), TWO(2, "燕国"), THREE(3, "齐国"), FOUR(4, "魏国"), FIVE(5, "韩国");

    private Integer resCode;
    private String resMessage;

    CountLatchDownEnum(Integer resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    public Integer getResCode() {
        return resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public static String getMessage(Integer key) {
        CountLatchDownEnum[] values = CountLatchDownEnum.values();
        for (CountLatchDownEnum element:values) {
            if (element.getResCode() == key)
                return element.getResMessage();
        }
        return null;
    }
}
