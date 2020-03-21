package pl.zmudzin.genetics.mutation;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.util.ProbabilityUtil;

import java.util.Objects;
import java.util.Random;

/**
 * This implementation of the {@code Mutator} randomly negates the chromosome gene
 * with the probability determined by the mutation probability hyperparameter.
 *
 * <p>It is important that the probability of mutation applies to each
 * chromosome gene, not the chromosome itself.</p>
 *
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class NegationMutator<C extends Chromosome> implements Mutator<C, C> {

    private Random random;
    private double probability;
    private ChromosomeFactory<C> factory;

    public NegationMutator(Random random, double probability, ChromosomeFactory<C> factory) {
        this.random = Objects.requireNonNull(random);
        this.probability = ProbabilityUtil.validate(probability);
        this.factory = Objects.requireNonNull(factory);
    }

    @Override
    public Population<C> apply(Population<? extends C> population) {
        return population.stream()
                .map(this::transform)
                .collect(Population.collector(ImmutablePopulation::new));
    }

    public C transform(C chromosome) {
        return chromosome.stream().map(gene -> {
            if (random.nextDouble() <= probability) {
                return Gene.of(!gene.booleanValue());
            }
            return gene;
        }).collect(Chromosome.collector(factory));
    }
}
