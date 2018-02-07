package com.hirath.urbanbean.mapping;

import com.hirath.urbanbean.source.SourceMessage;

public class NoSourceFoundException extends RuntimeException {
    public NoSourceFoundException(SourceMessage message) {
        super("No source found for : "+ message);
    }
}
