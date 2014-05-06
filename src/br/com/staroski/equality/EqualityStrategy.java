package br.com.staroski.equality;

/**
 * Superclasse para criar estrat&eacute;gias de equival&ecirc;ncia para objetos.<BR>
 * Estas estrat&eacute;gias permitem a implementar os m&eacute;todos <code>hashCode</code> e <code>equals</code> de forma ainda mais fácil do que usando as classes {@link EqualsUtils} e {@link HashCodeUtils}.<BR>
 * <BR>
 * Abaixo está uma classe que utiliza {@link EqualsUtils} e {@link HashCodeUtils}.
 * 
 * <PRE>
 * import static br.com.staroski.equality.EqualsUtils.*;
 * import static br.com.staroski.equality.HashCodeUtils.*;
 * 
 * class MyClass {
 * 
 *     private int      field1;
 *     private Object   field2;
 *     private String[] field3;
 *  
 *     public int hashCode() {
 *         int hash = ADDICTIVE;
 *         hash = hash(hash, field1);
 *         hash = hash(hash, field2);
 *         hash = hash(hash, field3);
 *         return hash;
 *     }
 *  
 *     public boolean equals(Object object) {
 *         if (this == object) {
 *             return true;
 *         }
 *         if (object instanceof MyClass) {
 *             MyClass that = (MyClass) object;
 *             return equal(this.field1, that.field1)
 *                 && equal(this.field2, that.field2)
 *                 && equal(this.field3, that.field3);
 *         }
 *         return false;
 *     }
 * }
 * </PRE>
 * 
 * As implementa&ccedil;ões do <code>hashCode</code> e <code>equals</code> desta classe podem ser simplificadas, utilizando uma {@link EqualityStrategy estrat&eacute;gia} baseada nos
 * {@link EqualityStrategy#fieldBased(Object) atributos} da classe, conforme o exemplo abaixo:
 * 
 * <PRE>
 * import static br.com.staroski.equality.strategies.EqualityStrategy.*;
 * 
 * class MyClass {
 * 
 *     private int      field1;
 *     private Object   field2;
 *     private String[] field3;
 * 
 *     private final EqualityStrategy strategy = fieldBased(this);
 *     
 *     public int hashCode() {
 *         return strategy.hashCode();
 *     }
 * 
 *     public boolean equals(Object object) {
 *         return strategy.equals(object);
 *     }
 * }
 * </PRE>
 * 
 * <BR>
 * Esta classe disponibiliza tr&ecirc;s {@link EqualityStrategy estrat&eacute;gias} prontas baseadas na {@link #referenceBased(Object) refer&ecirc;ncia} do objeto, nos {@link #fieldBased(Object) atributos} do objeto e
 * em {@link #valueBased(ValueSupplier) valores} específicos.<BR>
 * <BR>
 * Instancias desta classe possuem dois m&eacute;todos especiais: um para criar uma {@link EqualityStrategy estrat&eacute;gia} que mant&eacute;m um {@link #cached() cache} do <code>hashCode</code> da
 * {@link EqualityStrategy estrat&eacute;gia} original e outro m&eacute;todo para restaurar a {@link EqualityStrategy estrat&eacute;gia} original, isto &eacute;, {@link #uncached() sem o cache}.
 * 
 * @author Ricardo Artur Staroski
 * @see HashCodeUtils
 * @see EqualsUtils
 */
public abstract class EqualityStrategy {

    /**
     * Obt&eacute;m uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que calcula o <code>hashCode</code> e <code>equals</code> com base nos atributos declarados pelo objeto passado por parametro.
     * 
     * @param object O objeto para o qual se deseja criar a {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia}.
     * @return Uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que calcula o <code>hashCode</code> e <code>equals</code> com base nos atributos do objeto.
     */
    public static EqualityStrategy fieldBased(Object object) {
        return new FieldBased(object);
    }

    /**
     * Obt&eacute;m uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que calcula o <code>hashCode</code> e <code>equals</code> baseado na refer&ecirc;ncia do objeto passado por parametro.
     * 
     * @param object O objeto para o qual se deseja criar a {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia}.
     * @return Uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que calcula o <code>hashCode</code> e <code>equals</code> com base na referencia do objeto.
     */
    public static EqualityStrategy referenceBased(Object object) {
        return new ReferenceBased(object);
    }

    /**
     * Obt&eacute;m uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que calcula o <code>hashCode</code> e <code>equals</code> baseado nos {@link ValueSupplier valores} informados.
     * 
     * @param supplier O {@link ValueSupplier fornecedor de valores} para o qual se deseja criar a {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia}.
     * @return Uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que calcula o <code>hashCode</code> e <code>equals</code> baseado nos {@link ValueSupplier valores} informados.
     */
    public static EqualityStrategy valueBased(ValueSupplier supplier) {
        return new ValueBased(supplier);
    }

    // objeto encapsulado pela estrat&eacute;gia de equivalencia
    protected final Object target;

    protected EqualityStrategy(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("null");
        }
        this.target = target;
    }

    /**
     * Obt&eacute;m uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que mant&eacute;m um cache do valor calculado para o <code>hashCode</code> da {@link EqualityStrategy estrat&eacute;gia} atual.
     * 
     * @return Uma {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} que mant&eacute;m um cache do <code>hashCode</code>.
     */
    public final EqualityStrategy cached() {
        final EqualityStrategy uncached = this;
        return uncached instanceof Cached ? (Cached) uncached : new Cached(uncached);
    }

    /**
     * Verifica se o objeto encapsulado por esta {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} &eacute; equivalente ao objeto passado por parametro.
     * 
     * @param object O objeto a ser comparado com o objeto encapsulado por esta {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia}.
     * @return <code>true</code> se forem equivalentes e <code>false</code> caso contrário.
     */
    public abstract boolean equals(Object object);

    /**
     * Calcula o <code>hashCode</code> do objeto encapsulado por esta {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia}.
     * 
     * @return O <code>hashCode</code> calculado.
     */
    public abstract int hashCode();

    /**
     * Se a {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} atual mant&eacute;m um cache do valor calculado para o <code>hashCode</code>, obt&eacute;m a {@link EqualityStrategy estrat&eacute;gia} original.
     * 
     * @return A {@link EqualityStrategy estrat&eacute;gia de equival&ecirc;ncia} original.
     */
    public final EqualityStrategy uncached() {
        final EqualityStrategy cached = this;
        return cached instanceof Cached ? (EqualityStrategy) ((Cached) cached).target : cached;
    }
}
