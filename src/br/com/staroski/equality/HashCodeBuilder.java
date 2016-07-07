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
 * import static br.com.staroski.equality.HashCodeBuilder.*;
 * 
 * class MyClass {
 * 
 * 	private int field1; // primitive
 * 	private Object field2; // object
 * 	private String[] field3; // array
 * 
 * 	public int hashCode() {
 * 		return hash(field1).and(field2).and(field3).hashCode();
 * 	}
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
 * 	private int field;
 * 
 * 	public int hashCode() {
 * 		return hash(field).hashCode();
 * 	}
 * }
 * </pre>
 * 
 * @author Ricardo Artur Staroski
 * @see EqualsUtils
 * @see EqualityStrategy
 * @see HashCodeUtils
 */
public abstract class HashCodeBuilder {

	public static abstract class Builder {

		protected final Number seed;

		protected Builder(Number seed) {
			this.seed = seed;
		}

		public Builder and(boolean value) {
			return new BooleanBuilder(appendSeed(), value);
		}

		public Builder and(boolean[] values) {
			return new BooleanArrayBuilder(appendSeed(), values);
		}

		public Builder and(byte value) {
			return new ByteBuilder(appendSeed(), value);
		}

		public Builder and(byte[] values) {
			return new ByteArrayBuilder(appendSeed(), values);
		}

		public Builder and(char value) {
			return new CharBuilder(appendSeed(), value);
		}

		public Builder and(char[] values) {
			return new CharArrayBuilder(appendSeed(), values);
		}

		public Builder and(double value) {
			return new DoubleBuilder(appendSeed(), value);
		}

		public Builder and(double[] values) {
			return new DoubleArrayBuilder(appendSeed(), values);
		}

		public Builder and(float value) {
			return new FloatBuilder(appendSeed(), value);
		}

		public Builder and(float[] values) {
			return new FloatArrayBuilder(appendSeed(), values);
		}

		public Builder and(int value) {
			return new IntBuilder(appendSeed(), value);
		}

		public Builder and(int[] values) {
			return new IntArrayBuilder(appendSeed(), values);
		}

		public Builder and(long value) {
			return new LongBuilder(appendSeed(), value);
		}

		public Builder and(long[] values) {
			return new LongArrayBuilder(appendSeed(), values);
		}

		public Builder and(Object value) {
			return new ObjectBuilder(appendSeed(), value);
		}

		public Builder and(Object[] values) {
			return new ObjectBuilder(appendSeed(), values);
		}

		public Builder and(short value) {
			return new ShortBuilder(appendSeed(), value);
		}

		public Builder and(short[] values) {
			return new ShortArrayBuilder(appendSeed(), values);
		}

		@Override
		public final int hashCode() {
			return hashCode(seed.intValue());
		}

		protected abstract int hashCode(int seed);

		private Number appendSeed() {
			if (seed == SINGLE_SEED) {
				return Integer.valueOf(hashCode(HashCodeUtils.MULTI_VALUE));
			}
			return Integer.valueOf(hashCode(seed.intValue()));
		}
	}

	private static final class BooleanArrayBuilder extends Builder {

		private final boolean[] value;

		BooleanArrayBuilder(Number seed, boolean[] value) {
			super(seed);
			this.value = value;
		}

		@Override
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
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
		protected int hashCode(int seed) {
			return HashCodeUtils.hash(seed, value);
		}
	}

	// usando "new Number" para não obter um cache do "Integer.valueOf"
	private static final Number SINGLE_SEED = new Integer(HashCodeUtils.SINGLE_VALUE);

	public static Builder hash(boolean value) {
		return new BooleanBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(boolean[] values) {
		return new BooleanArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(byte value) {
		return new ByteBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(byte[] values) {
		return new ByteArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(char value) {
		return new CharBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(char[] values) {
		return new CharArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(double value) {
		return new DoubleBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(double[] values) {
		return new DoubleArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(float value) {
		return new FloatBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(float[] values) {
		return new FloatArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(int value) {
		return new IntBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(int[] values) {
		return new IntArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(long value) {
		return new LongBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(long[] values) {
		return new LongArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(Object value) {
		return new ObjectBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(Object[] values) {
		return new ObjectArrayBuilder(SINGLE_SEED, values);
	}

	public static Builder hash(short value) {
		return new ShortBuilder(SINGLE_SEED, value);
	}

	public static Builder hash(short[] values) {
		return new ShortArrayBuilder(SINGLE_SEED, values);
	}

	// Construtor privado, classe utilit&aacute;ria n&atilde;o-instanciavel
	private HashCodeBuilder() {
		throw new UnsupportedOperationException(getClass().getName() + " can not be instantiated");
	}
}
