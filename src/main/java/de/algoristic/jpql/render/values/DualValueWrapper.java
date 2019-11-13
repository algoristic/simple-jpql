package de.algoristic.jpql.render.values;

public class DualValueWrapper<T extends SQLDisplayWrapper> extends LiteralValueWrapper {

    T firstValue;
    T secondValue;

    public DualValueWrapper(T firstValue, T secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    protected Object getLiteralValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstValue.getValue());
        sb.append(" AND ");
        sb.append(secondValue.getValue());
        return sb.toString();
    }

}
