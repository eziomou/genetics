package pl.zmudzin.genetics.core;

import java.util.List;

/**
 * @param <C> type of chromosome
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface ChromosomeFactory<C extends Chromosome> {

    C create(List<? extends Gene> genes);
}
