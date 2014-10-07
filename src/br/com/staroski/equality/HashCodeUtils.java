package br.com.staroski.equality;


/**
 * Esta classe utilit&aacute;ria disponibiliza m&eacute;todos que permitem a constru&ccedil;&atilde;o f&aacute;cil de m&eacute;todos <code>hashCode</code>.<BR>
 * <BR>
 * Regras para a implementa&ccedil;&atilde;o do <code>hashCode</code>:<BR>
 * - se uma classe sobrescreve o <code>equals</code>, ela deve sobrescrever o <code>hashCode</code> tamb&eacute;m;<BR>
 * - se ambos s&atilde;o sobrescritos, <code>equals</code> e <code>hashCode</code> devem usar o mesmo conjunto de campos;<BR>
 * - se o <code>equals</code> entre dois objetos &eacute; <code>true</code>, o <code>hashCode</code> desses objetos deve ser o mesmo;<BR>
 * - se o objeto for imut&aacute;vel, ent&atilde;o o <code>hashCode</code> &eacute; candidato para <I>cache</I> e <i>lazy initialization</I>;<BR>
 * <BR>
 * &Eacute; bastante comum ouvir desenvolvedores Java com pouco conhecimento afirmarem que o <code>hashCode</code> obt&eacute;m um identificador &uacute;nico (<I>unique id</I>) do objeto.<BR>
 * <B>&Eacute; importante salientar que isso n&atilde;o &eacute; verdade, o <code>hashCode</code> n&atilde;o &eacute; um identificador &uacute;nico!</B><BR>
 * <BR>
 * Exemplo de metodo <code>hashCode</code> que utiliza contribui&ccedil;&atilde;o de varios campos:
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
 *         int hash = MULTI_VALUE;
 *         hash = hash(hash, field1);
 *         hash = hash(hash, field2);
 *         hash = hash(hash, field3);
 *         return hash;
 *     }
 * }
 * </pre>
 * 
 * Exemplo de metodo <code>hashCode</code> que s&oacute; utiliza somente um campo:
 * 
 * <pre>
 * import static br.com.staroski.equality.HashCodeUtils.*;
 * 
 * class MyClass {
 * 
 *     private int field;
 *     
 *     public int hashCode(){
 *         return hash(SINGLE_VALUE, field);
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
     * @see #MULTI_VALUE
     */
    public static final int SINGLE_VALUE = 0;

    /**
     * Semente para calcular <code>hashCode</code>s formados pela contribui&ccedil;&atilde;o de dois ou mais campos.
     * 
     * @see #SINGLE_VALUE
     */
    public static final int MULTI_VALUE = 1;

    /**
     * N&uacute;mero primo &iacute;mpar utilizado nos calculos.
     */
    private static final int PRIME = 31;

    /**
     * Calcula o <I>hash</I> de um valor <tt>boolean</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final boolean value) {
        return PRIME * seed + (value ? 1231 : 1237);
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>boolean</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final boolean[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>byte</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final byte value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>byte</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final byte[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>char</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final char value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>char</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final char[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>double</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final double value) {
        return hash(seed, Double.doubleToLongBits(value));
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>double</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final double[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>float</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final float value) {
        return hash(seed, Float.floatToIntBits(value));
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>float</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final float[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>int</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final int value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>int</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final int[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>long</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final long value) {
        return PRIME * seed + (int) (value ^ value >>> 32);
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>long</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final long[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um <tt>Object</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
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
     * Vale lembrar que, independente do tipo de dado, <B>qualquer array com mais de uma dimens&atilde;o, &eacute; um array de <tt>Object</tt></B>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final Object[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    /**
     * Calcula o <I>hash</I> de um valor <tt>short</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param value O valor do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final short value) {
        return PRIME * seed + value;
    }

    /**
     * Calcula o <I>hash</I> de um array de <tt>short</tt>.
     * 
     * @param seed A semente ({@link #MULTI_VALUE aditiva} ou {@link #SINGLE_VALUE n&atilde;o aditiva}) para o c&aacute;lulo do <I>hash</I>
     * @param array O array do qual se deseja obter o <I>hash</I>.
     * @return O valor <I>hash</I> calculado.
     */
    public static int hash(final int seed, final short[] array) {
        if (array == null) {
            return hash(seed, 0);
        }
        final int length = array.length;
        int hash = MULTI_VALUE;
        for (int i = 0; i < length; ++i) {
            hash = hash(hash, array[i]);
        }
        return hash(seed, hash);
    }

    // Calcula o hash de um Object assumindo que ele &eacute; um array.
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

    // Construtor privado, classe utilit&aacute;ria n&atilde;o-instanciavel
    private HashCodeUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " can not be instantiated");
    }

}
