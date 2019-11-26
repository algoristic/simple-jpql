package de.algoristic.jpql.util;

public class AscendingNumberProvider implements NumberProvider {

    private static volatile AscendingNumberProvider instance = new AscendingNumberProvider();

    private int number = 0;

    private AscendingNumberProvider() {
    }

    @Override
    public synchronized int getNumber() {
        try {
            return number;
        } finally {
            number = number + 1;
        }
    }

    public static AscendingNumberProvider getInstance() {
        return instance;
    }

}
