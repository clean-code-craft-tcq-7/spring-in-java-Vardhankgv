package statisticker;

import java.util.*;
import java.util.stream.*;

public class Statistics 
{
    /**
    * Computes statistical metrics (average, minimum, and maximum) for a given list of Float numbers.
    * <p>
    * This method processes the input list by filtering out any {@code null} elements and any values
    * that are {@link Float#NaN NaN}. It then calculates the average, minimum, and maximum of the
    * remaining valid numbers using Java 8 streams and the {@link DoubleSummaryStatistics} utility.
    * <p>
    * If the input list is {@code null}, empty, or contains only {@code null} or {@code NaN} values,
    * the method returns a {@link Stats} object with all fields set to {@link Float#NaN}.
    * <p>
    * Note that the returned statistics are of type {@code float}, so any intermediate
    * calculations done in {@code double} precision are cast back to {@code float} before returning.
    *
    * @param numbers a {@link List} of {@link Float} values to analyze; may contain {@code null} or {@code NaN} values
    * @return a {@link Stats} object containing the computed average, minimum, and maximum values;
    *         if the input list is {@code null}, empty, or contains no valid numbers, all fields in
    *         the returned {@code Stats} object will be {@link Float#NaN}
    */
    public static Stats getStatistics(List<Float> numbers) {
    float average, min, max;

    if (numbers == null || numbers.isEmpty()) {
        average = min = max = Float.NaN;
    } else {
        DoubleSummaryStatistics stats = numbers.stream()
            .filter(f -> f != null && !f.isNaN())
            .mapToDouble(Float::doubleValue)
            .summaryStatistics();

        if (stats.getCount() == 0) {
            average = min = max = Float.NaN;
        } else {
            average = (float) stats.getAverage();
            min = (float) stats.getMin();
            max = (float) stats.getMax();
        }
    }
     return new Stats(average, min, max);
    }
}
