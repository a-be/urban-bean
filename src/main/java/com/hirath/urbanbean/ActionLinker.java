package com.hirath.urbanbean;

import java.util.function.BiFunction;

public class ActionLinker<ACTION_CATALOG, MESSAGE extends Message> {
    private Matcher<MESSAGE> matcher;
    private boolean publishable;
    private BiFunction<Matcher<MESSAGE>, Boolean, ACTION_CATALOG> actionCatalogFactory;

    public ActionLinker(Matcher<MESSAGE> matcher, boolean publishable, BiFunction<Matcher<MESSAGE>, Boolean, ACTION_CATALOG> actionCatalogFactory) {
        this.matcher = matcher;
        this.publishable = publishable;
        this.actionCatalogFactory = actionCatalogFactory;
    }

    public ACTION_CATALOG and() {
        return actionCatalogFactory.apply(matcher, publishable);
    }
}
