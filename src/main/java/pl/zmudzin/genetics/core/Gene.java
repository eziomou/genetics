package pl.zmudzin.genetics.core;

import java.util.Objects;

/**
 * Binary implementation of gene.
 *
 * <p>The gene represents a single feature of an individual. In this
 * implementation, the allele of each gene can have one of two values:
 * {@code false} or {@code true}.</p>
 *
 * <p>This implementation guarantees immutability</p>
 *
 * @author Piotr Żmudzin
 */
public class Gene {

    protected static final Gene NEGATIVE_GENE = new Gene(false);
    protected static final Gene POSITIVE_GENE = new Gene(true);

    protected boolean allele;

    protected Gene(boolean allele) {
        this.allele = allele;
    }

    public boolean booleanValue() {
        return allele;
    }

    public int intValue() {
        return allele ? 1 : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Gene)) {
            return false;
        }
        Gene other = (Gene) obj;
        return Objects.equals(allele, other.allele);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allele);
    }

    @Override
    public String toString() {
        return Integer.toString(intValue());
    }

    public static Gene of(boolean allele) {
        return allele ? POSITIVE_GENE : NEGATIVE_GENE;
    }

    public static Gene of(int allele) {
        return allele > 0 ? POSITIVE_GENE : NEGATIVE_GENE;
    }
}
