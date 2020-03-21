package pl.zmudzin.genetics.core;

import java.util.Collection;

/**
 * @param <P> type of population
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface PopulationFactory<P extends Population<C>, C extends Chromosome> {

    P create(Collection<? extends C> chromosomes);
}
