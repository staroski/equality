package br.com.staroski.equality;

final class Cached extends EqualityStrategy {

    private boolean compute = true;
    private int hash = -1;

    Cached(EqualityStrategy target) {
        super(target);
    }

    public boolean equals(Object obj) {
        return obj != null && (hashCode() == obj.hashCode()) && target.equals(obj);
    }

    public int hashCode() {
        if (compute) {
            hash = target.hashCode();
            compute = false;
        }
        return hash;
    }
}
