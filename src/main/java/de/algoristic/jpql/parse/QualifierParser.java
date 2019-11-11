package de.algoristic.jpql.parse;

public interface QualifierParser<T> {

    T parse(String s);

}
