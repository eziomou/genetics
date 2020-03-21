package pl.zmudzin.genetics.util;

/**
 * @author Piotr Żmudzin
 */
public class ProbabilityUtil {

    public static double validate(double probability) {
        if (probability < 0) {
            throw new IllegalArgumentException("Probability can't be lower than 0");
        }
        if (probability > 1) {
            throw new IllegalArgumentException("Probability cant't be greater than 1");
        }
        return probability;
    }
}
