package br.com.staroski.equality;

import static br.com.staroski.equality.HashCodeUtils.*;
import static br.com.staroski.equality.EqualsUtils.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

final class FieldBased extends EqualityStrategy {

    private final Field[] fields;

    FieldBased(Object target) {
        super(target);
        final List<Field> usedFields = new ArrayList<Field>();
        final Class<?> thisClass = getClass();
        final Field[] declaredFields = target.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.getType().isAssignableFrom(thisClass)) {
                field.setAccessible(true);
                usedFields.add(field);
            }
        }
        final int length = usedFields.size();
        fields = usedFields.toArray(new Field[length]);
    }

    public boolean equals(Object obj) {
        if (target == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return equal(fieldValues(target), fieldValues(obj));
    }

    public int hashCode() {
        return hash(NON_ADDICTIVE, fieldValues(target));
    }

    private Object[] fieldValues(Object target) {
        try {
            final int length = fields.length;
            Object[] values = new Object[length];
            for (int i = 0; i < length; i++) {
                values[i] = fields[i].get(target);
            }
            return values;
        } catch (IllegalAccessException e) {
            throw new SecurityException(e);
        }
    }
}
