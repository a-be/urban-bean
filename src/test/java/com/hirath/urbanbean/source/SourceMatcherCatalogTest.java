package com.hirath.urbanbean.source;

import java.util.function.Function;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SourceMatcherCatalogTest {
    @Test
    public void objectTypeWithStringObjectShouldScoreToOne(){
        matcherShouldReturnScore("", "", "", 1, b -> b.objectType(String.class));
    }

    @Test
    public void objectTypeWithNullShouldScoreToZero(){
        matcherShouldReturnScore(null, "", "", 0, b -> b.objectType(String.class));
    }

    @Test
    public void objectTypeWithBooleanShouldScoreToZero(){
        matcherShouldReturnScore(true, "", "", 0, b -> b.objectType(String.class));
    }

    @Test
    public void descriptionMatchesWithNullShouldScoreToZero(){
        matcherShouldReturnScore("", "", null, 0, b -> b.descriptionMatches(".*"));
    }

    @Test
    public void descriptionMatchesWithMatchingStringShouldScoreToOne(){
        matcherShouldReturnScore("", "", "description", 1, b -> b.descriptionMatches(".*"));
    }

    @Test
    public void descriptionMatchesWithUnmatchingStringShouldScoreToZero(){
        matcherShouldReturnScore("", "", "test", 0, b -> b.descriptionMatches("a"));
    }

    @Test
    public void tagMatchesWithNullShouldScoreToZero(){
        matcherShouldReturnScore("", null, "", 0, b -> b.tagMatches(".*"));
    }

    @Test
    public void tagMatchesWithMatchingStringShouldScoreToOne(){
        matcherShouldReturnScore("", "tag", "", 1, b -> b.descriptionMatches(".*"));
    }

    @Test
    public void tagMatchesWithUnmatchingStringShouldScoreToOne(){
        matcherShouldReturnScore("", "test", "", 0, b -> b.descriptionMatches("a"));
    }

    @Test
    public void objectSatisfiesShouldReturnPredicateResult(){
        matcherShouldReturnScore("", "", "", 1, b -> b.objectSatisfies(o -> true));
    }

    @Test
    public void tagAndDescriptionMatchesWithMatchingTagOnlyShouldScoreToZero(){
        matcherShouldReturnScore("", "my tag", null, 0, b -> b.descriptionMatches("^.*description.*$").and().tagMatches("^.*tag.*$"));
    }

    @Test
    public void tagAndBeanMatchesWithMatchingTagOnlyShouldScoreToZero(){
        matcherShouldReturnScore(null, "my tag", null, 0, b -> b.tagMatches("^.*tag.*$").and().objectType(String.class));
    }

    @Test
    public void tagAndBeanMatchesWithAllMatchingShouldScoreToZero(){
        matcherShouldReturnScore("", "my tag", null, 1, b -> b.tagMatches("^.*tag.*$").and().objectType(String.class));
    }

    @Test
    public void tagAndDescriptionMatchesWithAllMatchingShouldScoreToOne(){
        matcherShouldReturnScore("", "my tag", "description", 1, b -> b.descriptionMatches("^.*description.*$").and().tagMatches("^.*tag.*$"));
    }

    @Test
    public void tagMatchesAndDescriptionMatchesWithMatchingTagAndDescriptionShouldScoreToOne(){
        matcherShouldReturnScore("", "my tag", "my description", 1, b -> b.descriptionMatches("^.*description.*$").and().tagMatches("^.*tag.*$"));
    }

    private void matcherShouldReturnScore(Object bean, String tag, String description, int score,
            Function<SourceMatcherCatalog<Object, Object>, SourceAssociationCatalog<?, ?>> matcherBuilder) {
        Source source = matcherBuilder.apply(SourceMatcherCatalog.when()).then().generate((bn, tg, desc) -> null);
        SourceMessage message = new SourceMessage(bean,tag,description);
        Assertions.assertThat(source.evaluate(message).getValue()).isEqualTo(score);
    }
}
