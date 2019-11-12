package de.algoristic.jpql.render.values;

public class DualValueWrapper<T extends SQLDisplayWrapper> implements SQLDisplayWrapper {

    T firstValue;
    T secondValue;

    public DualValueWrapper(T firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public Object getValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstValue.getValue());
        sb.append(" AND ");
        sb.append(secondValue.getValue());
        return sb.toString();
    }

}
