package com.hirath.urbanbean.mapping;

import static com.hirath.urbanbean.TestDataGenerator.randomString;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hirath.urbanbean.MatcherFinder;
import com.hirath.urbanbean.source.Source;
import com.hirath.urbanbean.source.SourceMessage;
import com.hirath.urbanbean.target.Target;
import com.hirath.urbanbean.target.TargetMessage;

@RunWith(MockitoJUnitRunner.class)
public class RouterTest {
    private Router router;
    @Mock
    private Trafic trafic;
    @Mock
    private MatcherFinder<SourceMessage, Source> sourceMatcherFinder;
    @Mock
    private MatcherFinder<TargetMessage, Target> targetMatcherFinder;
    @Mock
    private Source source;
    @Mock
    private Target target;
    @Captor
    private ArgumentCaptor<TargetMessage> targetMessageCaptor;

    @Before
    public void setUp() {
        router = new Router(trafic, sourceMatcherFinder, targetMatcherFinder);
    }

    @Test
    public void routeShouldRouteFromSourceToTarget() {
        String bean = randomString();
        String argument = randomString();
        String tag = randomString();
        String description = randomString();

        Mockito.when(sourceMatcherFinder.find(Mockito.any())).thenReturn(Optional.of(source));
        Mockito.when(targetMatcherFinder.find(Mockito.any())).thenReturn(Optional.of(target));
        Mockito.when(source.get(Mockito.any())).thenReturn(argument);

        router.route(bean, tag, randomString(), description);

        Mockito.verify(target).accept(targetMessageCaptor.capture());
        TargetMessage message = targetMessageCaptor.getValue();
        Assertions.assertThat(message.getBean()).isEqualTo(bean);
        Assertions.assertThat(message.getArgument()).isEqualTo(argument);
        Assertions.assertThat(message.getTag()).isEqualTo(tag);
        Assertions.assertThat(message.getDescription()).isEqualTo(description);
    }

    @Test
    public void route1() {
    }

}
