package pl.zmudzin.genetics.crossing;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.util.ProbabilityUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This implementation of {@code Crossover} relies on a random
 * chromosome division in n points.
 *
 * <p>Each chromosome in the given population has an equal crossover
 * probability determined by the cross probability hyperparameter. When the
 * number of chromosomes selected for crossing is not even then the last
 * selected chromosome is removed from the pool.</p>
 *
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class NPointCrossover<C extends Chromosome> implements Crossover<C, C> {

    private Random random;
    private double probability;
    private int divisionCount;
    private ChromosomeFactory<C> factory;

    public NPointCrossover(Random random, double probability, int divisionCount, ChromosomeFactory<C> factory) {
        this.random = Objects.requireNonNull(random);
        this.probability = ProbabilityUtil.validate(probability);
        this.divisionCount = validateDivisionCount(divisionCount);
        this.factory = Objects.requireNonNull(factory);
    }

    private int validateDivisionCount(int divisionCount) {
        if (divisionCount < 1) {
            throw new IllegalArgumentException("Division count can't be lower than 1");
        }
        return divisionCount;
    }

    @Override
    public Population<C> apply(Population<? extends C> population) {
        List<C> result = new ArrayList<>();

        List<C> selected = population.stream().filter(chromosome -> {
            if (random.nextDouble() <= probability) {
                return true;
            }
            result.add(chromosome);
            return false;
        }).collect(Collectors.toList());

        if (selected.size() % 2 != 0) {
            result.add(selected.remove(selected.size() - 1));
        }
        Collections.shuffle(selected, random);

        for (int i = 0; i < selected.size(); i += 2) {
            result.addAll(cross(selected.get(i), selected.get(i + 1)).asList());
        }
        return new ImmutablePopulation<>(result);
    }

    private Pair<C> cross(C chromosome1, C chromosome2) {
        SortedSet<Integer> crossoverPoints = randomCrossoverPoints(chromosome1.size());
        crossoverPoints.add(chromosome1.size());

        List<Gene> genes1 = new ArrayList<>(chromosome1.genes());
        List<Gene> genes2 = new ArrayList<>(chromosome2.genes());

        int i = 0;
        int previousCrossoverPoint = 0;

        for (int crossoverPoint : crossoverPoints) {
            if (i++ % 2 != 0) {
                continue;
            }
            swap(genes1, genes2, previousCrossoverPoint, crossoverPoint);
            previousCrossoverPoint = crossoverPoint;

            List<Gene> temporary = genes1;
            genes1 = genes2;
            genes2 = temporary;
        }
        return new Pair<>(factory.create(genes1), factory.create(genes2));
    }

    private SortedSet<Integer> randomCrossoverPoints(int chromosomeSize) {
        if (divisionCount >= chromosomeSize) {
            throw new IllegalArgumentException("Division count must be lower than chromosome size");
        }
        SortedSet<Integer> crossoverPoints = new TreeSet<>();

        while (crossoverPoints.size() < divisionCount) {
            crossoverPoints.add(randomCrossoverPoint(chromosomeSize));
        }
        return crossoverPoints;
    }

    private int randomCrossoverPoint(int chromosomeSize) {
        return random.nextInt(chromosomeSize - 1) + 1;
    }

    private void swap(List<Gene> genes1, List<Gene> genes2, int start, int stop) {
        for (int i = start; i < stop; i++) {
            genes1.set(i, genes2.set(i, genes1.get(i)));
        }
    }
}
