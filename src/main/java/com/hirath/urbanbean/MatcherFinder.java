package com.hirath.urbanbean;

import static java.util.Comparator.comparing;

import java.util.List;
import java.util.function.Function;

public class MatcherFinder<MESSAGE extends Message, MATCHER extends Matcher<MESSAGE>> {
    private List<MATCHER> matchers;
    private Function<MESSAGE,? extends RuntimeException> exceptionProvider;

    public MatcherFinder(List<MATCHER> matchers, Function<MESSAGE, ? extends RuntimeException> exceptionProvider) {
        this.matchers = matchers;
        this.exceptionProvider = exceptionProvider;
    }

    public Pair<MATCHER> find(MESSAGE message){
        return matchers.stream()
                       .map(matcher -> new Pair<>(matcher, matcher.evaluate(message)))
                       .sorted(comparing(pair -> pair.score))
                       .findFirst()
                       .orElseThrow(() -> exceptionProvider.apply(message));
    }

    public static class Pair<MATCHER>{
        public final MATCHER matcher;
        public final Score score;

        public Pair(MATCHER matcher, Score score) {
            this.matcher = matcher;
            this.score = score;
        }
    }
}
