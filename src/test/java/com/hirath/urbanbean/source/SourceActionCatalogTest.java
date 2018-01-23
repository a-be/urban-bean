package com.hirath.urbanbean.source;

import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.hirath.urbanbean.TestDataGenerator;
import com.hirath.urbanbean.source.SourceActionCatalog.SourceProducer;

@RunWith(MockitoJUnitRunner.class)
public class SourceActionCatalogTest {
    @Test
    public void produceShouldCallProducer() {
        SourceProducer<Object, Object> sourceProducer = Mockito.mock(SourceProducer.class);
        Source source = SourceMatcherCatalog.when().satisfy( m -> true).then().publish().and().generate(sourceProducer);

        SourceMessage message = new SourceMessage(null, null, null);
        source.get(message);

        Mockito.verify(sourceProducer).produce(null, null, null);
    }

    @Test
    public void produceFromBeanShouldCallProducer() {
        String bean = TestDataGenerator.randomString();
        Function<Object,Object> factory = Mockito.mock(Function.class);
        Source source = SourceMatcherCatalog.when().satisfy( m -> true).then().publish().and().produceFromBean(factory);

        SourceMessage message = new SourceMessage(bean, null, null);
        source.get(message);

        Mockito.verify(factory).apply(bean);
    }

    @Test
    public void produceFromDescriptionShouldCallProducer() {
        String description = TestDataGenerator.randomString();
        Function<String,Object> factory = Mockito.mock(Function.class);
        Source source = SourceMatcherCatalog.when().satisfy( m -> true).then().publish().and().produceFromDescription(factory);

        SourceMessage message = new SourceMessage(null, null, description);
        source.get(message);

        Mockito.verify(factory).apply(description);
    }

    @Test
    public void generateShouldCallProducer() {
        Supplier<Object> factory= Mockito.mock(Supplier.class);
        Source source = SourceMatcherCatalog.when().satisfy( m -> true).then().publish().and().generate(factory);

        SourceMessage message = new SourceMessage(null, null, null);
        source.get(message);

        Mockito.verify(factory).get();
    }
}
