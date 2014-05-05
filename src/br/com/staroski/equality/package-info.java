/**
 * Neste pacote encontram-se classes que auxiliam na implementação simplificada dos métodos <code>hashCode</code> e <code>equals</code>.<BR>
 * <BR>
 * Para entender melhor, veja os 3 exemplos abaixo.<BR>
 * <BR>
 * 1 - Classe com exemplo padrão de implementação do <code>hashCode</code> e <code>equals</code> que leva em consideração cada atributo.
 * 
 * <PRE>
 * import java.util.Arrays;
 * 
 * class MyClass {
 * 
 *     private int      field1;
 *     private Object   field2;
 *     private String[] field3;
 * 
 *     public int hashCode() {
 *         final int prime = 31;
 *         int result = 1;
 *         result = prime * result + field1;
 *         result = prime * result + ((field2 == null) ? 0 : field2.hashCode());
 *         result = prime * result + Arrays.hashCode(field3);
 *         return result;
 *     }
 * 
 *     public boolean equals(Object obj) {
 *         if (this == obj) {
 *             return true;
 *         }
 *         if (obj == null) {
 *             return false;
 *         }
 *         if (!(obj instanceof MyClass)) {
 *             return false;
 *         }
 *         MyClass other = (MyClass) obj;
 *         if (field1 != other.field1) {
 *             return false;
 *         }
 *         if (field2 == null) {
 *             if (other.field2 != null) {
 *                 return false;
 *             }
 *         } else if (!field2.equals(other.field2)) {
 *             return false;
 *         }
 *         if (!Arrays.equals(field3, other.field3)) {
 *             return false;
 *         }
 *         return true;
 *     }
 * }
 * </PRE>
 * 
 * <BR>
 * 2 - Podemos simplificar o código da classe acima, utilizando as classes {@link br.com.staroski.equality.HashCodeUtils} e {@link br.com.staroski.equality.EqualsUtils}.<BR>
 * Veja o código refatorado:
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
 * <BR>
 * 3 - Podemos simplificar o código acima mais ainda, utilizando a classe {@link br.com.staroski.equality.EqualityStrategy}.<BR>
 * Veja como fica o código refatorado:
 * 
 * <PRE>
 * import static br.com.staroski.equality.EqualityStrategy.*;
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
 */
package br.com.staroski.equality;

