package br.com.staroski.equality.strategy;

final class ReferenceBased extends EqualityStrategy {

    ReferenceBased(Object target) {
        super(target);
    }

    public boolean equals(Object obj) {
        return target == obj;
    }

    public int hashCode() {
        return System.identityHashCode(target);
    }
}
