package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.QueryInformation;

public interface PreProcessable {

    void preProcess(QueryInformation queryInfo);

}
