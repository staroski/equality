package br.com.staroski.equality;


/**
 * Esta classe utilitária disponibiliza métodos que permitem a construção fácil de métodos <code>hashCode</code>.<BR>
 * <BR>
 * Regras para a implementação do <code>hashCode</code>:<BR>
 * - se uma classe sobrescreve o <code>equals</code>, ela deve sobrescrever o <code>hashCode</code> também;<BR>
 * - se ambos são sobrescritos, <code>equals</code> e <code>hashCode</code> devem usar o mesmo conjunto de campos;<BR>
 * - se o <code>equals</code> entre dois objetos é <code>true</code>, o <code>hashCode</code> desses objetos deve ser o mesmo;<BR>
 * - se o objeto for imutável, então o <code>hashCode</code> é candidato para <I>cache</I> e <i>lazy initialization</I>;<BR>
 * <BR>
 * É bastante comum ouvir desenvolvedores Java com pouco conhecimento afirmarem que o <code>hashCode</code> obtém um identificador único (<I>unique id</I>) do objeto.<BR>
 * <B>É importante salientar que isso não é verdade, o <code>hashCode</code> não é um identificador único!</B><BR>
 * <BR>
 * Exemplo de metodo <code>hashCode</code> que utiliza contribuição de varios campos:
 * 
 * <pre>
 * import static br.com.staroski.equality.HashCodeUtils.*;
 * 
 * class MyClass {
 * 
 *     private int      field1; // primitive
 *     private Object   field2; // object
 *     private String[] field3; // array
 *     
 *     public int hashCode(){
 *         int hash = ADDICTIVE;
 *         hash = hash(hash, field1);
 *         hash = hash(hash, field2);
 *         hash = hash(hash, field3);
 *         return hash;
 *     }
 * }
 * </pre>
 * 
 * Exemplo de metodo <code>hashCode</code> que só utiliza somente um campo:
 * 
 * <pre>
 * import static br.com.staroski.equality.HashCodeUtils.*;
 * 
 * class MyClass {
 * 
 *     private int field;
 *     
 *     public int hashCode(){
 *         return hash(NON_ADDICTIVE, field);
 *     }
 * }
 * </pre>
 * 
 * @author Ricardo Artur Staroski
 * @see EqualsUtils
 * @see EqualityStrategy
 */
public final class HashCodeUtils {

    /**
     * Semente para calcular <code>hashCode</code>s de um unico campo.
     * 
     * @see #ADDICTIVE
     */
    public static final int NON_ADDICTIVE = 0;

    /**
     * Semente para calcular <code>hashCode</code>s formados pela contribuição de dois ou mais campos.
     * 
     * @see #NON_ADDICTIVE
     */
    public static final int ADDICTIVE = 1;

    /**
     * Número primo ímpar utilizado nos calculos.
     */
    public static final int PRIME = 31;

    /**
     * Calcula o <I>hash</I> de um valor <tt>boolean</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final boolean value) {
        return PRIME * seed + (value ? 1231 : 1237);
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>boolean</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final boolean[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>byte</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final byte value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>byte</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final byte[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>char</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final char value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>char</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final char[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>double</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final double value) {
        return hash(seed, Double.doubleToLongBits(value));
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>double</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final double[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>float</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final float value) {
        return hash(seed, Float.floatToIntBits(value));
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>float</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final float[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>int</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final int value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>int</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final int[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>long</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final long value) {
        return PRIME * seed + (int) (value ^ value >>> 32);
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>long</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final long[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um <tt>Object</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param object O objeto do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final Object object) {
        if (object == null) {
            return hash(seed, 0);
        }
        if (object.getClass().isArray()) {
            return hashArray(seed, object);
        }
        return hash(seed, object.hashCode());
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>Object</tt>.<BR>
     * Vale lembrar que, independente do tipo de dado, <B>qualquer array com mais de uma dimensão, é um array de <tt>Object</tt></B>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final Object[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>short</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final short value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>short</tt>.
     * 
     * @param seed A semente ({@link #ADDICTIVE aditiva} ou {@link #NON_ADDICTIVE não aditiva}) para o cálulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final short[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = ADDICTIVE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    // Calcula o hash de um Object assumindo que ele é um array.
    private static int hashArray(final int seed, final Object object) {
        if (object instanceof byte[]) return hash(seed, (byte[]) object);
        if (object instanceof boolean[]) return hash(seed, (boolean[]) object);
        if (object instanceof short[]) return hash(seed, (short[]) object);
        if (object instanceof char[]) return hash(seed, (char[]) object);
        if (object instanceof int[]) return hash(seed, (int[]) object);
        if (object instanceof float[]) return hash(seed, (float[]) object);
        if (object instanceof long[]) return hash(seed, (long[]) object);
        if (object instanceof double[]) return hash(seed, (double[]) object);
        if (object instanceof Object[]) return hash(seed, (Object[]) object);
        throw new AssertionError("received non-array object {\n  object: " + object + "\n}");
    }

    // Construtor privado, classe utilitária não-instanciavel
    private HashCodeUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " can not be instantiated");
    }

}
