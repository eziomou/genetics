package pl.zmudzin.genetics.selection;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.evaluation.Evaluation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This implementation imitates the behavior of the popular roulette game.
 * Each chromosome of a given population receives a part of the roulette
 * wheel based on its adaptation to the environment.
 *
 * @param <C> type of evaluated chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class RouletteSelector<C extends Evaluation & Chromosome> implements Selector<C, C> {

    private Random random;

    public RouletteSelector(Random random) {
        this.random = Objects.requireNonNull(random);
    }

    @Override
    public Population<C> apply(Population<? extends C> population) {
        List<Double> evaluations = getEvaluations(population);
        List<Double> normalizedEvaluations = normalize(evaluations);
        List<Double> probabilities = countProbabilities(normalizedEvaluations);
        List<Double> distributedProbabilities = cumulativeDistribution(probabilities);
        List<C> selectedChromosomes = select(population.chromosomes(), distributedProbabilities);
        return new ImmutablePopulation<>(selectedChromosomes);
    }

    private List<Double> getEvaluations(Population<? extends C> population) {
        return population.stream()
                .map(Evaluation::getValue)
                .collect(Collectors.toList());
    }

    private List<Double> normalize(List<Double> evaluations) {
        DoubleSummaryStatistics statistics = evaluations.stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue));

        if (statistics.getMin() != statistics.getMax()) {
            return evaluations.stream()
                    .map(evaluation -> normalize(evaluation, statistics.getMin(), statistics.getMax()))
                    .collect(Collectors.toList());
        }
        return evaluations;
    }

    private double normalize(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    private static List<Double> countProbabilities(List<Double> evaluations) {
        double sum = evaluations.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return evaluations.stream()
                .map(evaluation -> evaluation / sum)
                .collect(Collectors.toList());
    }

    private List<Double> cumulativeDistribution(List<Double> probabilities) {
        List<Double> distributedProbabilities = new ArrayList<>();

        if (probabilities.size() != 0) {
            distributedProbabilities.add(probabilities.get(0));
        }
        for (int i = 1; i < probabilities.size(); i++) {
            distributedProbabilities.add(distributedProbabilities.get(i - 1) + probabilities.get(i));
        }
        return distributedProbabilities;
    }

    private List<C> select(List<? extends C> chromosomes, List<Double> distributedProbabilities) {
        List<C> selectedChromosomes = new ArrayList<>();

        for (int i = 0; i < chromosomes.size(); i++) {
            double d = random.nextDouble();

            if (d < distributedProbabilities.get(0)) {
                selectedChromosomes.add(chromosomes.get(0));
            } else {
                for (int j = 1; j < distributedProbabilities.size(); j++) {
                    if (d >= distributedProbabilities.get(j - 1) && d < distributedProbabilities.get(j)) {
                        selectedChromosomes.add(chromosomes.get(j));
                        break;
                    }
                }
            }
        }
        return selectedChromosomes;
    }
}
