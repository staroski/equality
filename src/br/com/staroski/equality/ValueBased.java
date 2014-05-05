package br.com.staroski.equality;

import static br.com.staroski.equality.EqualsUtils.*;
import static br.com.staroski.equality.HashCodeUtils.*;

final class ValueBased extends EqualityStrategy {

    ValueBased(ValueSupplier target) {
        super(target);
    }

    public boolean equals(Object obj) {
        final ValueSupplier target = (ValueSupplier) this.target;
        if (target == obj) {
            return true;
        }
        if (obj instanceof ValueSupplier) {
            return equal(target.values(), ((ValueSupplier) obj).values());
        }
        return false;
    }

    public int hashCode() {
        return hash(NON_ADDICTIVE, ((ValueSupplier) target).values());
    }
}
