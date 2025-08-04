package statisticker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import statisticker.Statistics;
import statisticker.Stats;

import org.junit.Test;

public class StatisticsTest 
{
    @Test
    public void reportsAverageMinMaxx() {
        Float[] numbers = {1.5f, 8.9f, 3.2f, 4.5f};
        List<Float> numberList = Arrays.asList(numbers);

        Stats s = Statistics.getStatistics(numberList);

        float epsilon = 0.001f;
        assertEquals(4.525f, s.average, epsilon);
        assertEquals(1.5f, s.min, epsilon);
        assertEquals(8.9f, s.max, epsilon);
    }

    @Test
    public void reportsNaNForEmptyInput() {
        List<Float> emptyList = new ArrayList<>();

        Stats s = Statistics.getStatistics(emptyList);

        assertTrue(Float.isNaN(s.average));
        assertTrue(Float.isNaN(s.min));
        assertTrue(Float.isNaN(s.max));
    }

    @Test
    public void singleElementList() {
        List<Float> singleElement = Collections.singletonList(42.0f);

        Stats s = Statistics.getStatistics(singleElement);

        float epsilon = 0.001f;
        assertEquals(42.0f, s.average, epsilon);
        assertEquals(42.0f, s.min, epsilon);
        assertEquals(42.0f, s.max, epsilon);
    }

    @Test
    public void negativeNumbers() {
        List<Float> negatives = Arrays.asList(-5.0f, -10.5f, -3.3f, -7.7f);

        Stats s = Statistics.getStatistics(negatives);

        float epsilon = 0.001f;
        assertEquals(-6.625f, s.average, epsilon);
        assertEquals(-10.5f, s.min, epsilon);
        assertEquals(-3.3f, s.max, epsilon);
    }

    @Test
    public void mixedPositiveNegativeNumbers() {
        List<Float> mixed = Arrays.asList(-1.0f, 0.0f, 1.0f, 10.0f);

        Stats s = Statistics.getStatistics(mixed);

        float epsilon = 0.001f;
        assertEquals(2.5f, s.average, epsilon);
        assertEquals(-1.0f, s.min, epsilon);
        assertEquals(10.0f, s.max, epsilon);
    }

    @Test
    public void listWithNaNValues() {
        List<Float> withNaN = Arrays.asList(1.0f, Float.NaN, 3.0f);

        Stats s = Statistics.getStatistics(withNaN);

        // NaN values should be ignored in the computation.
        float epsilon = 0.001f;
        assertEquals(2.0f, s.average, epsilon);
        assertEquals(1.0f, s.min, epsilon);
        assertEquals(3.0f, s.max, epsilon);
    }
}
