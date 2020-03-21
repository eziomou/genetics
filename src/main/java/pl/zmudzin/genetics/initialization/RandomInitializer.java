package pl.zmudzin.genetics.initialization;

import pl.zmudzin.genetics.core.*;

import java.util.Random;
import java.util.stream.Stream;

/**
 * This implementation of the {@code Initializer} randomly initializes population.
 *
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class RandomInitializer<C extends Chromosome> implements Initializer<C> {

    private Random random;
    private int chromosomeSize;
    private int populationSize;
    private ChromosomeFactory<C> factory;

    public RandomInitializer(Random random, int chromosomeSize, int populationSize, ChromosomeFactory<C> factory) {
        this.random = random;
        this.chromosomeSize = validateChromosomeSize(chromosomeSize);
        this.populationSize = validatePopulationSize(populationSize);
        this.factory = factory;
    }

    private int validateChromosomeSize(int chromosomeSize) {
        if (chromosomeSize < 1) {
            throw new IllegalArgumentException("Chromosome size can't be lower than 1");
        }
        return chromosomeSize;
    }

    private int validatePopulationSize(int populationSize) {
        if (populationSize < 1) {
            throw new IllegalArgumentException("Population size can't be lower than 1");
        }
        return populationSize;
    }

    @Override
    public Population<C> initialize() {
        return Stream
                .generate(this::randomChromosome)
                .limit(populationSize)
                .collect(Population.collector(ImmutablePopulation::new));
    }

    public C randomChromosome() {
        return Stream
                .generate(this::randomGene)
                .limit(chromosomeSize)
                .collect(Chromosome.collector(factory));
    }

    public Gene randomGene() {
        return Gene.of(random.nextBoolean());
    }
}
