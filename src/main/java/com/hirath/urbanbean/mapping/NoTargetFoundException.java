package com.hirath.urbanbean.mapping;

import com.hirath.urbanbean.target.TargetMessage;

public class NoTargetFoundException extends RuntimeException {
    public NoTargetFoundException(TargetMessage message) {
        super("No target found for : "+ message);
    }
}
