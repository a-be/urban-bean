package com.hirath.urbanbean.mapping;

import static com.hirath.urbanbean.TestDataGenerator.randomString;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TraficTest {
    private Trafic trafic;
    @Mock
    private Radar radar1;
    @Mock
    private Radar radar2;

    @Before
    public void setUp() {
        trafic = new Trafic(Arrays.asList(radar1, radar2));
    }

    @Test
    public void introduceShouldCallAllRadars() {
        String description = randomString();
        String value = randomString();

        trafic.introduce(description, value);

        Mockito.verify(radar1).routed(description, value);
        Mockito.verify(radar2).routed(description, value);
    }
}
