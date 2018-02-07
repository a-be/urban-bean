package com.hirath.urbanbean.mapping;

import java.util.Map;

import com.hirath.urbanbean.MatcherFinder;
import com.hirath.urbanbean.MatcherFinder.Pair;
import com.hirath.urbanbean.source.Source;
import com.hirath.urbanbean.source.SourceMessage;
import com.hirath.urbanbean.target.Target;
import com.hirath.urbanbean.target.TargetMessage;

public class Router {
    private Trafic trafic;
    private MatcherFinder<SourceMessage, Source> sourceMatcherFinder;
    private MatcherFinder<TargetMessage, Target> targetMatcherFinder;

    public Router(Trafic trafic, MatcherFinder<SourceMessage,Source> sourceMatcherFinder, MatcherFinder<TargetMessage,Target> targetMatcherFinder) {
        this.trafic = trafic;
        this.sourceMatcherFinder = sourceMatcherFinder;
        this.targetMatcherFinder = targetMatcherFinder;
    }

    public void route(Object bean, String tag, Map<String,String> descriptions){
        descriptions.forEach((keyDescription, valueDescription) -> route(bean, tag, keyDescription, valueDescription));
    }

    public void route(Object bean, String tag, String keyDescription, String valueDescription){
        try {
            route(bean, tag, keyDescription, keyDescription, valueDescription);
        } catch (NoSourceFoundException e) {
            route(bean, tag, keyDescription, valueDescription, keyDescription);
        }
    }

    private void route(Object bean, String tag, String publishDescription, String firstDescription, String secondDescription){
        SourceMessage sourceMessage = new SourceMessage(bean, tag, firstDescription);
        Pair<Source> source = sourceMatcherFinder.find(sourceMessage);
        source.evaluate(sourceMessage);



        Object argument = source.get(sourceMessage);

        TargetMessage targetMessage = new TargetMessage(bean, argument, tag, secondDescription);
        Target target = targetMatcherFinder.find(targetMessage);
        target.accept(targetMessage);

        if(source.isPublishable() && target.isPublishable())
            trafic.introduce(publishDescription, argument);
    }
}
