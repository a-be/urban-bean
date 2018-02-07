package com.hirath.urbanbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import com.hirath.urbanbean.source.SourceMessage;

public class MatcherFinderTest {
    private MatcherFinder<Message, Matcher<Message>> matcherFinder;

    @Test
    public void findSouldReturnHighestScoreMatcher() {
        Matcher<Message> matcher1 = Mockito.mock(Matcher.class);
        Mockito.when(matcher1.evaluate(Mockito.any())).thenReturn(Score.of(1));
        Matcher<Message> matcher2 = Mockito.mock(Matcher.class);
        List<Matcher<Message>> matchers = Arrays.asList(matcher1, matcher2);
        Mockito.when(matcher2.evaluate(Mockito.any())).thenReturn(Score.of(0));
        Message message = new SourceMessage(null, null, null);
        matcherFinder = new MatcherFinder<>(matchers);

        Optional<Matcher<Message>> matcher = matcherFinder.find(message);

        Assertions.assertThat(matcher).hasValue(matcher1);
    }

    @Test
    public void findSouldReturnEmptyIfNoMatcherFound() {
        Message message = new SourceMessage(null, null, null);
        matcherFinder = new MatcherFinder<>(new ArrayList<>());

        Optional<Matcher<Message>> matcher = matcherFinder.find(message);

        Assertions.assertThat(matcher).isEmpty();
    }

}
