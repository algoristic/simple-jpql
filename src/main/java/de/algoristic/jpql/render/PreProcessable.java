package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.SharedQueryInformation;

public interface PreProcessable {

    void preProcess(SharedQueryInformation queryInfo);

}
