package com.hirath.urbanbean.source;

import com.hirath.urbanbean.AssociationCatalog;
import com.hirath.urbanbean.Matcher;

public class SourceAssociationCatalog<B,A> extends
        AssociationCatalog<SourceMatcherCatalog<B,A>,SourceActionCatalog<B,A>,SourceMessage,B,A> {
    public SourceAssociationCatalog(Matcher<SourceMessage> matcher) {
        super(SourceMatcherCatalog::new, SourceActionCatalog::new, matcher);
    }
}
