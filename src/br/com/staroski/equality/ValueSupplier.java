package br.com.staroski.equality;

/**
 * Interface para fornecer valores �s {@link EqualityStrategy estrat�gias} criadas pelo m�todo {@link EqualityStrategy#valueBased(ValueSupplier) valueBased(ValueSupplier)}.
 * 
 * @author Ricardo Artur Staroski
 * @see EqualityStrategy#valueBased(ValueSupplier)
 */
public interface ValueSupplier {

    /**
     * Obt�m os valores utilizados no c�lculo do <code>hashCode</code> e <code>equals</code>.
     * 
     * @return Um array com os valores utilizados no c�lculo do <code>hashCode</code> e <code>equals</code>.
     */
    Object[] values();
}
