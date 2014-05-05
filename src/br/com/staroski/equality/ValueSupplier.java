package br.com.staroski.equality;

/**
 * Interface para fornecer valores às {@link EqualityStrategy estratégias} criadas pelo método {@link EqualityStrategy#valueBased(ValueSupplier) valueBased(ValueSupplier)}.
 * 
 * @author Ricardo Artur Staroski
 * @see EqualityStrategy#valueBased(ValueSupplier)
 */
public interface ValueSupplier {

    /**
     * Obtém os valores utilizados no cálculo do <code>hashCode</code> e <code>equals</code>.
     * 
     * @return Um array com os valores utilizados no cálculo do <code>hashCode</code> e <code>equals</code>.
     */
    Object[] values();
}
