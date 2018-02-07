package com.hirath.urbanbean;

import static com.hirath.urbanbean.Score.of;

import java.util.function.Predicate;

public abstract class Matchers {

    public static <B,MESSAGE extends Message> Matcher<MESSAGE> objectType(Class<B> type) {
        return satisfy(message -> type.isInstance(message.getBean()));
    }

    public static <MESSAGE extends Message> Matcher<MESSAGE> descriptionMatches(String regex) {
        return satisfy(message -> message.getDescription() != null && message.getDescription().matches(regex));
    }

    public static <MESSAGE extends Message> Matcher<MESSAGE> tagMatches(String regex) {
        return satisfy(message -> message.getTag() != null && message.getTag().matches(regex));
    }

    public static <B,MESSAGE extends Message> Matcher<MESSAGE> objectSatisfies(Predicate<B> objectMatcher) {
        return satisfy(message ->  objectMatcher.test((B) message.getBean()));
    }

    public static <MESSAGE extends Message> Matcher<MESSAGE> satisfy(Predicate<MESSAGE> condition){
        return message -> condition.test(message) ? of(1) : of(0);
    }
}
