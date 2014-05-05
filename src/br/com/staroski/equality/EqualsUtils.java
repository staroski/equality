package br.com.staroski.equality;


/**
 * Esta classe utilit�ria disponibiliza m�todos que permitem a constru��o f�cil de m�todos <code>equals</code>.<BR>
 * <BR>
 * Todos os objetos possuem uma <B>identidade</B> (sua refer�ncia na mem�ria) e um <B>estado</B> (os seus dados).<BR>
 * O operador <code>==</code> sempre compara a identidade, assim como a implementa��o <I>default</I> do m�todo <code>equals</code>.<BR>
 * <BR>
 * As vezes a implementa��o <I>default</I> do <code>equals</code> possui o comportamento desejado (como por exemplo numa <I>type-safe enumeration</I>), mas o <code>equals</code> deveria geralmente
 * comparar o estado, n�o a identidade.<BR>
 * Isso � particularmente verdade para classes <I>data-centric</I> que mapeiam registros de bancos de dados.<BR>
 * <BR>
 * <code>hashCode</code> e <code>equals</code> possuem uma rela��o pr�xima:<BR>
 * - se voce sobrescreve <code>equals</code>, voce deve sobrescrever tamb�m o <code>hashCode</code>.<BR>
 * - <code>hashCode</code> deve ser igual para objetos <code>equals</code> entre si.<BR>
 * - <code>equals</code> e <code>hashCode</code> deve depender do mesmo conjunto de campos. N�o � necess�rio utilizar todos os campos, somente os campos significativos. Por exemplo, um campo que �
 * calculado a partir de outros campos, poderia ser omitido do <code>equals</code> e <code>hashCode</code>.<BR>
 * <BR>
 * Objetos inseridos em <I>lists</I>, <I>sets</I>, ou <I>maps</I> (tanto como chave quanto como valor) deveriam ter uma defini��o apropriada do <code>equals</code>.<BR>
 * Se voc� extende uma classe concreta e adiciona um novo campo que contribui para o <code>equals</code>, ent�o n�o � poss�vel escrever um <code>equals</code> perfeitamente correto para esta classe
 * nova. Ao inv�s disso, voc� deveria usar composi��o ao inv�s de heran�a.<BR>
 * <BR>
 * Ao implementar o <code>equals</code>, os campos s�o comparados de forma diferente dependendo do seu tipo:<BR>
 * - campos do tipo object, incluindo collections: usam <code>equals</code><BR>
 * - <I>type-safe enumerations</I>: usam <code>==</code> e <code>equals</code> pois instancias diferentes podem conter os mesmos objetos<BR>
 * - campos object possivelmente <code>null</code>: usam <code>==</code> e <code>equals</code><BR>
 * - campos de tipos primitivos diferentes de <code>float</code> ou <code>double</code>: usam <code>==</code><BR>
 * - campos <code>float</code>: s�o convetidos para <code>int</code> utilizando <code>Float.floatToIntBits</code> e em seguida comparados com <code>==</code><BR>
 * - campos <code>double</code>: s�o convertidos para <code>long</code> utilizando <code>Double.doubleToLongBits</code> e em seguida comparados com <code>==</code><BR>
 * - campos do tipo array: comparam cada elemento do array aplicando as regras acima<BR>
 * <BR>
 * � importante notar que se um campo � do tipo <I>wrapper class</I>, ent�o a implementa��o do <code>equals</code> � simples, pois ela s� faz uma coisa: invoca o <code>equals</code> recursivamente.<BR>
 * <BR>
 * Em um m�todo <code>equals</code> �, geralmente, melhor ordenar as compara��es dos campos de acordo com a signific�ncia do campo, isto �, campos mais significativos, deveriam ser avaliados primeiro.
 * Isso permite que o operador l�gico <code>&&</code> minimize o tempo de execu��o.<BR>
 * <BR>
 * Exemplo pr�tico:
 * 
 * <pre>
 * import static br.com.staroski.equality.EqualsUtils.*;
 * 
 * class MyClass {
 * 
 *     private int      field1;
 *     private Object   field2;
 *     private String[] field3;
 *     
 *     public boolean equals(Object object){
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
 * </pre>
 * 
 * @author Ricardo Artur Staroski
 * @see HashCodeUtils
 * @see EqualityStrategy
 */
public final class EqualsUtils {

    /**
     * Compara dois valores <tt>boolean</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final boolean value1, final boolean value2) {
        return value1 == value2;
    }

    /**
     * Compara dois arrays de <tt>boolean</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final boolean[] array1, final boolean[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>byte</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final byte value1, final byte value2) {
        return value1 == value2;
    }

    /**
     * Compara dois arrays de <tt>byte</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final byte[] array1, final byte[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>char</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final char value1, final char value2) {
        return value1 == value2;
    }

    /**
     * Compara dois arrays de <tt>char</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final char[] array1, final char[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>double</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final double value1, final double value2) {
        return Double.doubleToLongBits(value1) == Double.doubleToLongBits(value2);
    }

    /**
     * Compara dois arrays de <tt>double</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final double[] array1, final double[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>float</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final float value1, final float value2) {
        return Float.floatToIntBits(value1) == Float.floatToIntBits(value2);
    }

    /**
     * Compara dois arrays de <tt>float</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final float[] array1, final float[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>int</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final int value1, final int value2) {
        return value1 == value2;
    }

    /**
     * Compara dois arrays de <tt>int</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final int[] array1, final int[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>long</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final long value1, final long value2) {
        return value1 == value2;
    }

    /**
     * Compara dois arrays de <tt>long</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final long[] array1, final long[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois <tt>Object</tt>s.
     * 
     * @param object1 O primeiro objeto.
     * @param object2 O segundo objeto.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final Object object1, final Object object2) {
        if (object1 == object2) {
            return true;
        }
        if (object1 == null || object2 == null) {
            return false;
        }
        if (object1.getClass().isArray()) {
            return equalArray(object1, object2);
        }
        return object1.equals(object2);
    }

    /**
     * Compara dois arrays de <tt>Object</tt>.<BR>
     * Vale lembrar que, independente do tipo de dado, <B>qualquer array com mais de uma dimens�o, � um array de <tt>Object</tt></B>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final Object[] array1, final Object[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara dois valores <tt>short</tt>.
     * 
     * @param value1 O primeiro valor.
     * @param value2 O segundo valor.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final short value1, final short value2) {
        return value1 == value2;
    }

    /**
     * Compara dois arrays de <tt>short</tt>.
     * 
     * @param array1 O primeiro array.
     * @param array2 O segundo array.
     * @return <code>true</code> se forem iguais e <code>false</code> caso contr�rio.
     */
    public static boolean equal(final short[] array1, final short[] array2) {
        if (array1 == array2) {
            return true;
        }
        if (array1 == null || array2 == null) {
            return false;
        }
        final int length = array1.length;
        if (array2.length != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (!equal(array1[i], array2[i])) {
                return false;
            }
        }
        return true;
    }

    // Compara dois Objects assumindo que eles s�o arrays.
    private static boolean equalArray(final Object object1, final Object object2) {
        if (object1 instanceof byte[] && object2 instanceof byte[]) return equal((byte[]) object1, (byte[]) object2);
        if (object1 instanceof boolean[] && object2 instanceof boolean[]) return equal((boolean[]) object1, (boolean[]) object2);
        if (object1 instanceof short[] && object2 instanceof short[]) return equal((short[]) object1, (short[]) object2);
        if (object1 instanceof char[] && object2 instanceof char[]) return equal((char[]) object1, (char[]) object2);
        if (object1 instanceof int[] && object2 instanceof int[]) return equal((int[]) object1, (int[]) object2);
        if (object1 instanceof float[] && object2 instanceof float[]) return equal((float[]) object1, (float[]) object2);
        if (object1 instanceof long[] && object2 instanceof long[]) return equal((long[]) object1, (long[]) object2);
        if (object1 instanceof double[] && object2 instanceof double[]) return equal((double[]) object1, (double[]) object2);
        if (object1 instanceof Object[] && object2 instanceof Object[]) return equal((Object[]) object1, (Object[]) object2);
        throw new AssertionError("received non-array object(s) {\n  object1: " + object1 + "\n  object2: " + object2 + "\n}");
    }

    // Construtor privado, classe utilit�ria n�o-instanciavel
    private EqualsUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " can not be instantiated");
    }
}
