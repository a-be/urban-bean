package com.hirath.urbanbean;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class AssociationCatalog<MATCHER_CATALOG, ACTION_CATALOG, MESSAGE extends Message,B,A> {
    private Function<UnaryOperator<Matcher<MESSAGE>>, MATCHER_CATALOG> matcherCatalogFactory;
    private Function<Matcher<MESSAGE>, ACTION_CATALOG> actionCatalogFactory;
    protected Matcher<MESSAGE> matcher;

    public AssociationCatalog(Function<UnaryOperator<Matcher<MESSAGE>>, MATCHER_CATALOG> matcherCatalogFactory,
            Function<Matcher<MESSAGE>, ACTION_CATALOG> actionCatalogFactory, Matcher<MESSAGE> matcher) {
        this.matcherCatalogFactory = matcherCatalogFactory;
        this.actionCatalogFactory = actionCatalogFactory;
        this.matcher = matcher;
    }

    public MATCHER_CATALOG and(){
        return matcherCatalogFactory.apply(m -> msg -> m.evaluate(msg).and(matcher.evaluate(msg)));
    }

    public ACTION_CATALOG then(){
        return actionCatalogFactory.apply(matcher);
    }
}
