package com.hirath.urbanbean.source;

import static com.hirath.urbanbean.Score.of;

import java.util.function.Function;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hirath.urbanbean.Matcher;
import com.hirath.urbanbean.Score;
import com.hirath.urbanbean.TestDataGenerator;

@RunWith(MockitoJUnitRunner.class)
public class SimpleSourceTest {
    private SimpleSource source;
    @Mock
    private Matcher<SourceMessage> matcher;
    @Mock
    private Function<SourceMessage,Object> factory;
    @Mock
    private SourceMessage message;

    @Before
    public void setUp() {
        source = new SimpleSource(matcher,factory, true);
    }

    @Test
    public void evaluateShouldReturnMatcherScore() {
        Score score = of(10);
        Mockito.when(matcher.evaluate(message)).thenReturn(score);

        Score result = source.evaluate(message);

        Assertions.assertThat(result).isEqualTo(score);
    }

    @Test
    public void getShouldReturnObjectCreatedByFactory() {
        String value = TestDataGenerator.randomString();
        Mockito.when(factory.apply(message)).thenReturn(value);

        Object result = source.get(message);

        Assertions.assertThat(result).isEqualTo(result);
    }
}
