package com.hirath.urbanbean;

public class Score implements Comparable<Score>{
    private int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score of(int value){
        return new Score(value);
    }

    public int getValue() {
        return value;
    }

    public Score and(Score score){
        return new Score(this.getValue() * score.getValue());
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Score score = (Score) o;

        return value == score.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
