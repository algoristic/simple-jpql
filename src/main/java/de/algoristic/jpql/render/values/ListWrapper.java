package de.algoristic.jpql.render.values;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import de.algoristic.jpql.Property;

public class ListWrapper extends LiteralValueWrapper {

    private List<SQLDisplayWrapper> values;

    private ListWrapper() {
        this(new ArrayList<>());
    }

    public ListWrapper(List<SQLDisplayWrapper> values) {
        this.values = values;
    }

    public static ListWrapper getListWrapper(List<Object> values) {
        if ((values == null) || (values.size() == 0)) {
            return new ListWrapper();
        } else {
            List<SQLDisplayWrapper> wrappers;
            Object elem = values.get(0);
            //this is not nice - but since type erasure makes it impossible to have functions like
            //void foo(Bar<T> bar) {...}
            //void foo(Bar<R> bar) {...}
            //in the same class impossible, this is the only way to provide a user-firendly interface.
            //(see ConditionSelector.in)
            //
            //also there are only 3 (non-complex) datatypes to be assigned to a list:
            //namely another property, string, number and date...
            if (elem instanceof String) {
                wrappers = getWrappedStrings(values);
            } else if (elem instanceof Number) {
                wrappers = getWrappedNumbers(values);
            } else if (elem instanceof Property) {
                wrappers = getWrappedProperties(values);
            } else {
                wrappers = getWrappedObjects(values);
            }
            return new ListWrapper(wrappers);
        }
    }
    
    @Override
    protected Object getLiteralValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        String renderedList = values.stream()
            .map(SQLDisplayWrapper::getValue)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        sb.append(renderedList);
        sb.append(")");
        return sb.toString();
    }

    private static List<SQLDisplayWrapper> getWrappedObjects(List<Object> objectValues) {
        return getWrapped(objectValues, obj -> obj, ComplexDataWrapper::new);
    }
    
    private static List<SQLDisplayWrapper> getWrappedProperties(List<Object> propertyValues) {
        return getWrapped(propertyValues, val -> (Property) val, PropertyWrapper::new);
    }
    
    private static List<SQLDisplayWrapper> getWrappedStrings(List<Object> stringValues) {
        return getWrapped(stringValues, val -> (String) val, StringWrapper::new);
    }
    
    private static List<SQLDisplayWrapper> getWrappedNumbers(List<Object> numberValues) {
        return getWrapped(numberValues, val -> (Number) val, NumberWrapper::getNumberWrapper);
    }

    private static <T> List<SQLDisplayWrapper> getWrapped(List<Object> values,
                                                   Function<Object, T> conversionFunction,
                                                   Function<T, SQLDisplayWrapper> wrapperFunction) {
        return values.stream()
                .map(conversionFunction)
                .map(wrapperFunction)
                .collect(Collectors.toList());
    }

}
