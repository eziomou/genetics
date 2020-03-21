package pl.zmudzin.genetics.evaluation;

/**
 * This interface represent the evaluation of the generated solution to the
 * problem.
 *
 * @author Piotr Żmudzin
 */
public interface Evaluation extends Comparable<Evaluation> {

    double getValue();

    @Override
    default int compareTo(Evaluation other) {
        return Double.compare(getValue(), other.getValue());
    }
}
