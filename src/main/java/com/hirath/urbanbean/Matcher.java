package com.hirath.urbanbean;

public interface Matcher<T extends Message> {
    Score evaluate(T message);
}
