package com.hirath.urbanbean.source;

import com.hirath.urbanbean.Matcher;
import com.hirath.urbanbean.Publishable;

public interface Source extends Matcher<SourceMessage>, Publishable {
    Object get(SourceMessage sourceMessage);
}
