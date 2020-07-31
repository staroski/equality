package br.com.staroski.equality.strategy;

import static br.com.staroski.equality.EqualsUtils.equal;
import static br.com.staroski.equality.HashCodeUtils.SINGLE_VALUE;
import static br.com.staroski.equality.HashCodeUtils.hash;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

final class FieldBased extends EqualityStrategy {

    private final Field[] fields;

    FieldBased(Object target) {
        super(target);
        final List<Field> usedFields = new ArrayList<Field>();
        final Field[] declaredFields = target.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (!isEqualityStrategy(field) && !isStatic(field)) {
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
        return hash(SINGLE_VALUE, fieldValues(target));
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

    private boolean isEqualityStrategy(Field field) {
        return EqualityStrategy.class.isAssignableFrom(field.getType());
    }

    private boolean isStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }
}
