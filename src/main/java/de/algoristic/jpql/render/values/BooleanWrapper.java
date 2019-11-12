package de.algoristic.jpql.render.values;

public class BooleanWrapper implements SQLDisplayWrapper {

    private Boolean value;

    public BooleanWrapper(Boolean value) {
        super();
        this.value = value;
    }

    @Override
    public Object getValue() {
        if (value == null) {
            return "null";
        } else {
            String value = this.value.toString();
            return value.toUpperCase();
        }
    }

}
