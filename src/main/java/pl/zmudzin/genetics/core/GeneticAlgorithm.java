package pl.zmudzin.genetics.core;

import pl.zmudzin.genetics.evaluation.Evaluation;
import pl.zmudzin.genetics.evaluation.Evaluator;
import pl.zmudzin.genetics.initialization.Initializer;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;

/**
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @see Evaluation
 * @author Piotr Żmudzin
 */
public interface GeneticAlgorithm<C extends Chromosome> {

    GeneticAlgorithm<C> generations(int generations);

    void forEach(Consumer<? super Population<C>> consumer);

    <R> R collect(Collector<Population<C>, List<Population<C>>, R> collector);

    interface Builder<T extends Chromosome, R extends Chromosome & Evaluation> {

        GeneticAlgorithmImpl.BuilderImpl<T, R> initializer(Initializer<T> initializer);

        GeneticAlgorithmImpl.BuilderImpl<T, R> evaluator(Evaluator<T, R> evaluator);

        GeneticAlgorithmImpl.BuilderImpl<T, R> flow(Function<Population<? extends R>, Population<R>> flow);

        GeneticAlgorithmImpl<R> build();
    }
}
