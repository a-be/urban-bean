package com.hirath.urbanbean.source;

import java.util.function.Function;

import com.hirath.urbanbean.Matcher;
import com.hirath.urbanbean.Score;

public class SimpleSource implements Source{
    private Matcher<SourceMessage> matcher;
    private Function<SourceMessage,Object> factory;
    private boolean publishable;

    public SimpleSource(Matcher<SourceMessage> matcher, Function<SourceMessage, Object> factory, boolean publishable) {
        this.matcher = matcher;
        this.factory = factory;
        this.publishable = publishable;
    }

    @Override
    public boolean isPublishable() {
        return publishable;
    }

    @Override
    public Score evaluate(SourceMessage message) {
        return matcher.evaluate(message);
    }

    @Override
    public Object get(SourceMessage message) {
        return factory.apply(message);
    }
}
