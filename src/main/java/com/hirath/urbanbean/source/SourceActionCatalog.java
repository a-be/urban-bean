package com.hirath.urbanbean.source;

import java.util.function.Function;
import java.util.function.Supplier;

import com.hirath.urbanbean.ActionLinker;
import com.hirath.urbanbean.Matcher;

public class SourceActionCatalog<B,A> {
    private Matcher<SourceMessage> matcher;
    private boolean publishable;

    public SourceActionCatalog(Matcher<SourceMessage> matcher, boolean publishable) {
        this.matcher = matcher;
        this.publishable = publishable;
    }

    public SourceActionCatalog(Matcher<SourceMessage> matcher) {
        this.matcher = matcher;
        this.publishable = true;
    }

    public ActionLinker<SourceActionCatalog<B,A>, SourceMessage> publish(){
        return new ActionLinker<>(matcher, true, SourceActionCatalog::new);
    }

    public Source produceFromDescription(Function<String,A> factory){
        return this.generate((bean, description, tag) -> factory.apply(description));
    }

    public Source produceFromBean(Function<B,A> factory){
        return this.generate((bean, description, tag) -> factory.apply(bean));
    }

    public Source generate(Supplier<A> factory){
        return this.generate((bean, description, tag) -> factory.get());
    }

    public Source generate(SourceProducer<B,A> sourceProducer){
        return new SimpleSource(matcher,
                message -> sourceProducer.produce((B) message.getBean(), message.getDescription(), message.getTag()),
                publishable);
    }

    public interface SourceProducer<B,A>{
        A produce(B bean, String description, String tag);
    }
}
