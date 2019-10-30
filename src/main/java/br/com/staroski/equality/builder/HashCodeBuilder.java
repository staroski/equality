package br.com.staroski.equality.builder;

import br.com.staroski.equality.HashCodeUtils;

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
 * import static br.com.staroski.equality.HashCodeBuilder.*;
 * 
 * class MyClass {
 * 
 *     private int field1; // primitive
 *     private Object field2; // object
 *     private String[] field3; // array
 * 
 *     public int hashCode() {
 *         return hash(field1). //
 *                 and(field2). //
 *                 and(field3). //
 *                 code();
 *     }
 * }
 * </pre>
 * 
 * Exemplo de metodo <code>hashCode</code> que s&oacute; utiliza somente um campo:
 * 
 * <pre>
 * import static br.com.staroski.equality.HashCodeBuilder.*;
 * 
 * class MyClass {
 * 
 *     private int field;
 * 
 *     public int hashCode() {
 *         return hash(field).code();
 *     }
 * }
 * </pre>
 * 
 * @author Ricardo Artur Staroski
 * @see br.com.staroski.equality.EqualsUtils
 * @see br.com.staroski.equality.strategy.EqualityStrategy
 * @see HashCodeUtils
 */
public abstract class HashCodeBuilder {

    /**
     * Permite adicionar contribuições ao cálculo do <code>hashCode</code> através dos métodos <code>and</code> e obter o valor do <code>hashCode</code> calculado através do método
     * {@link #code()}<BR>
     * Instâncias de {@link Builder} são obtidas através dos métodos <code>hash</code> da classe {@link HashCodeBuilder}
     */
    public static abstract class Builder {

        final Number hashSeed;

        /**
         * Contrutor
         * 
         * @param seed
         *            Semente para o cálculo do <code>hashCode</code>
         */
        Builder(Number seed) {
            this.hashSeed = seed;
        }

        /**
         * Adiciona uma contribuição do tipo <t>boolean</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(boolean value) {
            return new BooleanBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>boolean[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(boolean[] value) {
            return new BooleanArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>byte</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(byte value) {
            return new ByteBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>byte[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(byte[] value) {
            return new ByteArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>char</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(char value) {
            return new CharBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>char[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(char[] value) {
            return new CharArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>double</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(double value) {
            return new DoubleBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>double[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(double[] value) {
            return new DoubleArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>float</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(float value) {
            return new FloatBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>float[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(float[] value) {
            return new FloatArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>int</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(int value) {
            return new IntBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>int[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(int[] value) {
            return new IntArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>long</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(long value) {
            return new LongBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>long[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(long[] value) {
            return new LongArrayBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>Object</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(Object value) {
            return new ObjectBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>Object[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(Object[] value) {
            return new ObjectBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>short</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(short value) {
            return new ShortBuilder(appendSeed(), value);
        }

        /**
         * Adiciona uma contribuição do tipo <t>short[]</t> ao cálculo do <code>hashCode</code>
         * 
         * @param value
         *            A contribuição a ser adicionada
         * @return Uma intância de {@link Builder}
         */
        public Builder and(short[] value) {
            return new ShortArrayBuilder(appendSeed(), value);
        }

        /**
         * Retorna o <code>hashCode</code> calculado por este {@link Builder}.
         * 
         * @return O valor do <code>hashCode</code> calculado.
         */
        public final int code() {
            return compute(hashSeed.intValue());
        }

        @Override
        public final int hashCode() {
            return code();
        }

        // obtém a semente apropriada para o cálculo do hashCode
        private Number appendSeed() {
            if (hashSeed == SINGLE_SEED) {
                return Integer.valueOf(compute(HashCodeUtils.MULTI_VALUE));
            }
            return Integer.valueOf(compute(hashSeed.intValue()));
        }

        // template para calcular o hashCode
        abstract int compute(int seed);
    }

    private static final class BooleanArrayBuilder extends Builder {

        private final boolean[] value;

        BooleanArrayBuilder(Number seed, boolean[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class BooleanBuilder extends Builder {

        private final boolean value;

        BooleanBuilder(Number seed, boolean value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class ByteArrayBuilder extends Builder {

        private final byte[] value;

        ByteArrayBuilder(Number seed, byte[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class ByteBuilder extends Builder {

        private final byte value;

        ByteBuilder(Number seed, byte value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class CharArrayBuilder extends Builder {

        private final char[] value;

        CharArrayBuilder(Number seed, char[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class CharBuilder extends Builder {

        private final char value;

        CharBuilder(Number seed, char value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class DoubleArrayBuilder extends Builder {

        private final double[] value;

        DoubleArrayBuilder(Number seed, double[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class DoubleBuilder extends Builder {

        private final double value;

        DoubleBuilder(Number seed, double value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class FloatArrayBuilder extends Builder {

        private final float[] value;

        FloatArrayBuilder(Number seed, float[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class FloatBuilder extends Builder {

        private final float value;

        FloatBuilder(Number seed, float value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class IntArrayBuilder extends Builder {

        private final int[] value;

        IntArrayBuilder(Number seed, int[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class IntBuilder extends Builder {

        private final int value;

        IntBuilder(Number seed, int value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class LongArrayBuilder extends Builder {

        private final long[] value;

        LongArrayBuilder(Number seed, long[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class LongBuilder extends Builder {

        private final long value;

        LongBuilder(Number seed, long value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class ObjectArrayBuilder extends Builder {

        private final Object[] value;

        ObjectArrayBuilder(Number seed, Object[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class ObjectBuilder extends Builder {

        private final Object value;

        ObjectBuilder(Number seed, Object value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class ShortArrayBuilder extends Builder {

        private final short[] value;

        ShortArrayBuilder(Number seed, short[] value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    private static final class ShortBuilder extends Builder {

        private final short value;

        ShortBuilder(Number seed, short value) {
            super(seed);
            this.value = value;
        }

        @Override
        int compute(int seed) {
            return HashCodeUtils.hash(seed, value);
        }
    }

    // usando "new Number" para não obter um cache do "Integer.valueOf"
    private static final Number SINGLE_SEED = new Integer(HashCodeUtils.SINGLE_VALUE);

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>boolean</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(boolean value) {
        return new BooleanBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>boolean[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(boolean[] value) {
        return new BooleanArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>byte</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(byte value) {
        return new ByteBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>byte[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(byte[] value) {
        return new ByteArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>char</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(char value) {
        return new CharBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>char[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(char[] value) {
        return new CharArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>double</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(double value) {
        return new DoubleBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>double[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(double[] value) {
        return new DoubleArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>float</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(float value) {
        return new FloatBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>float[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(float[] value) {
        return new FloatArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>int</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(int value) {
        return new IntBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>int[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(int[] value) {
        return new IntArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>long</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(long value) {
        return new LongBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>long[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(long[] value) {
        return new LongArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>Object</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(Object value) {
        return new ObjectBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>Object[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(Object[] value) {
        return new ObjectArrayBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>short</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(short value) {
        return new ShortBuilder(SINGLE_SEED, value);
    }

    /**
     * Obtém um {@link Builder}para a contribuição do tipo <t>short[]</t> informada
     * 
     * @param value
     *            A contribuição para o cálculo do <code>hashCode</code>
     * @return O {@link Builder} criado a partir da contribuição informada
     */
    public static Builder hash(short[] value) {
        return new ShortArrayBuilder(SINGLE_SEED, value);
    }

    // Construtor privado, classe utilit&aacute;ria n&atilde;o-instanciavel
    private HashCodeBuilder() {
        throw new UnsupportedOperationException(getClass().getName() + " can not be instantiated");
    }
}
