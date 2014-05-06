package br.com.staroski.equality;

/**
 * Interface para fornecer valores &agrave;s {@link EqualityStrategy estrat&eacute;gias} criadas pelo m&eacute;todo {@link EqualityStrategy#valueBased(ValueSupplier) valueBased(ValueSupplier)}.
 * 
 * @author Ricardo Artur Staroski
 * @see EqualityStrategy#valueBased(ValueSupplier)
 */
public interface ValueSupplier {

    /**
     * Obt&eacute;m os valores utilizados no cálculo do <code>hashCode</code> e <code>equals</code>.
     * 
     * @return Um array com os valores utilizados no cálculo do <code>hashCode</code> e <code>equals</code>.
     */
    Object[] values();
}
