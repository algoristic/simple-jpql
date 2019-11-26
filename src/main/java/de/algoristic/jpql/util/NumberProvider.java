package de.algoristic.jpql.util;

public interface NumberProvider {

    public static NumberProvider getInstance() {
        return AscendingNumberProvider.getInstance();
    }

    int getNumber();

}
