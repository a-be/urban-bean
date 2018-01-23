package com.hirath.urbanbean.source;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.hirath.urbanbean.Matcher;
import com.hirath.urbanbean.Matchers;

public class SourceMatcherCatalog<B,A> {
    private UnaryOperator<Matcher<SourceMessage>> matcherFactory;

    public SourceMatcherCatalog() {
        matcherFactory = UnaryOperator.identity();
    }

    public SourceMatcherCatalog(UnaryOperator<Matcher<SourceMessage>> matcherFactory) {
        this.matcherFactory = matcherFactory;
    }

    public static SourceMatcherCatalog<Object,Object> when(){
        return new SourceMatcherCatalog<>();
    }

    public <B> SourceAssociationCatalog<B,A> objectType(Class<B> type) {
        return create(Matchers.objectType(type));
    }

    public <A> SourceAssociationCatalog<B,A> returnType(Class<A> type) {
        return create(Matchers.satisfy(message -> true));
    }

    public SourceAssociationCatalog<B,A> descriptionMatches(String regex) {
        return create(Matchers.descriptionMatches(regex));
    }

    public SourceAssociationCatalog<B,A> tagMatches(String regex) {
        return create(Matchers.tagMatches(regex));
    }

    public SourceAssociationCatalog<B,A> objectSatisfies(Predicate<B> objectMatcher) {
        return create(Matchers.objectSatisfies(objectMatcher));
    }

    public SourceAssociationCatalog<B,A> satisfy(Predicate<SourceMessage> condition) {
        return create(Matchers.satisfy(condition));
    }

    private <B,A> SourceAssociationCatalog<B,A> create(Matcher<SourceMessage> matcher){
        return new SourceAssociationCatalog<>(matcherFactory.apply(matcher));
    }
}
