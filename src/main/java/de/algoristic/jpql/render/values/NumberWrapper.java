package de.algoristic.jpql.render.values;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class NumberWrapper extends LiteralValueWrapper {

    private static enum SupportedTypes {

        INTEGER(IntegerWrapper::new, Long.class, long.class, Integer.class, int.class, Short.class, short.class, Byte.class, byte.class),
        FLOAT(FloatWrapper::new, Float.class, float.class),
        DOUBLE(DoubleWrapper::new, Double.class, double.class);

        private Function<Number, NumberWrapper> constructor;
        private List<Class<?>> supportedClasses;

        private SupportedTypes(Function<Number, NumberWrapper> constructor, Class<?>... supportedClasses) {
            this.constructor = constructor;
            this.supportedClasses = Stream.of(supportedClasses).collect(Collectors.toList());
        }

        Function<Number, NumberWrapper> getConstructor() {
            return constructor;
        }

        boolean supports(Class<?> clazz) {
            return supportedClasses.contains(clazz);
        }

    }

    private Number number;

    protected NumberWrapper(Number number) {
        this.number = number;
    }

    @Override
    protected Object getLiteralValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(number);
        sb.append(getSuffix());
        return sb.toString();
    }

    protected abstract String getSuffix();

    public static SQLDisplayWrapper getNumberWrapper(Number number) {
        Class<? extends Number> numberClass = number.getClass();
        for (SupportedTypes type : SupportedTypes.values()) {
            if (type.supports(numberClass)) {
                return type.getConstructor().apply(number);
            }
        }
        return new GenericNumberWrapper(number);
    }

    private static class GenericNumberWrapper extends NumberWrapper {

        protected GenericNumberWrapper(Number number) {
            super(number);
        }

        @Override
        protected String getSuffix() {
            return "";
        }

    }

    private static class IntegerWrapper extends GenericNumberWrapper {

        protected IntegerWrapper(Number number) {
            super(number);
        }

    }

    private static class FloatWrapper extends NumberWrapper {

        protected FloatWrapper(Number number) {
            super(number);
        }

        @Override
        protected String getSuffix() {
            return "F";
        }

    }

    private static class DoubleWrapper extends NumberWrapper {

        protected DoubleWrapper(Number number) {
            super(number);
        }

        @Override
        protected String getSuffix() {
            return "D";
        }

    }

}
