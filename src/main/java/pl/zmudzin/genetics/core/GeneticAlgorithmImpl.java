package pl.zmudzin.genetics.core;

import pl.zmudzin.genetics.evaluation.Evaluation;
import pl.zmudzin.genetics.evaluation.Evaluator;
import pl.zmudzin.genetics.initialization.Initializer;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @param <C> type of chromosome
 * @see GeneticAlgorithm
 * @see Population
 * @see Chromosome
 * @see Gene
 * @see Evaluation
 * @author Piotr Żmudzin
 */
public class GeneticAlgorithmImpl<C extends Chromosome> implements GeneticAlgorithm<C> {

    private Stream<Population<C>> stream;

    private GeneticAlgorithmImpl(Population<C> population, Function<Population<? extends C>, Population<C>> function) {
        this(Stream.iterate(population, function::apply));
    }

    private GeneticAlgorithmImpl(Stream<Population<C>> stream) {
        this.stream = Objects.requireNonNull(stream);
    }

    @Override
    public GeneticAlgorithmImpl<C> generations(int generations) {
        return new GeneticAlgorithmImpl<>(stream.limit(generations));
    }

    @Override
    public void forEach(Consumer<? super Population<C>> consumer) {
        stream.forEach(consumer);
    }

    @Override
    public <R> R collect(Collector<Population<C>, List<Population<C>>, R> collector) {
        return stream.collect(collector);
    }

    public static <T extends Chromosome, R extends Chromosome & Evaluation> BuilderImpl<T, R> builder() {
        return new BuilderImpl<>();
    }

    public static class BuilderImpl<T extends Chromosome, R extends Chromosome & Evaluation> implements GeneticAlgorithm.Builder<T, R> {

        private Initializer<T> initializer;
        private Evaluator<T, R> evaluator;
        private Function<Population<? extends R>, Population<R>> flow;

        private BuilderImpl() {
        }

        @Override
        public BuilderImpl<T, R> initializer(Initializer<T> initializer) {
            this.initializer = Objects.requireNonNull(initializer);
            return this;
        }

        @Override
        public BuilderImpl<T, R> evaluator(Evaluator<T, R> evaluator) {
            this.evaluator = Objects.requireNonNull(evaluator);
            return this;
        }

        @Override
        public BuilderImpl<T, R> flow(Function<Population<? extends R>, Population<R>> flow) {
            this.flow = Objects.requireNonNull(flow);
            return this;
        }

        @Override
        public GeneticAlgorithmImpl<R> build() {
            return new GeneticAlgorithmImpl<>(evaluator.apply(initializer.initialize()), flow);
        }
    }
}
