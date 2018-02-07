package com.hirath.urbanbean;

import static com.hirath.urbanbean.Score.of;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ScoreTests {

    @Test
    public void scoreOfOneAndScoreOfOneShouldReturnScoreOfOne() {
        firstScoreAndSecondScoreShouldReturnMultiplicationScore(1, 1, 1);
    }

    @Test
    public void scoreOfOneAndScoreOfTwoShouldReturnScoreOfTwo() {
        firstScoreAndSecondScoreShouldReturnMultiplicationScore(1, 2, 2);
    }

    private void firstScoreAndSecondScoreShouldReturnMultiplicationScore(int first, int second, int result) {
        Score firstScore = of(first);
        Score secondScore = of(second);

        Score resultScore = firstScore.and(secondScore);

        Assertions.assertThat(resultScore.getValue()).isEqualTo(result);
    }

}
