package de.algoristic.jpql.render.wrapper;

import de.algoristic.jpql.sql.ComplexParameter;
import de.algoristic.jpql.sql.SharedQueryInformation;
import de.algoristic.jpql.util.NumberProvider;

public class ComplexDataWrapper implements SQLDisplayWrapper {

    private String surrogate;
    private Object obj;

    public ComplexDataWrapper(Object obj) {
        this.obj = obj;
        this.surrogate = createSurrogate(obj);
    }

    @Override
    public Object getValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(":").append(surrogate);
        return sb.toString();
    }

    @Override
    public void preProcess(SharedQueryInformation queryInfo) {
        ComplexParameter parameter = new ComplexParameter(surrogate, obj);
        queryInfo.add(parameter);
    }

    private static final String createSurrogate(Object obj) {
        Class<?> objClass = obj.getClass();
        String className = objClass.getSimpleName();
        String classAlias = className.toLowerCase();
        NumberProvider numberProvider = NumberProvider.getInstance();
        int randomNumber = numberProvider.getNumber();
        StringBuilder sb = new StringBuilder();
        sb.append(classAlias);
        sb.append("_");
        sb.append(randomNumber);
        return sb.toString();
    }

}
