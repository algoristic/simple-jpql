package de.algoristic.jpql.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.Table;

public class RandomStringProvider {

    private static final int DEF_LENGTH = 8;

    private List<String> assignedStrings = new ArrayList<>();

    public String getRandom() {
        return getRandom(DEF_LENGTH);
    }

    public String getRandom(int length) {
        String rnd;
        int tries = 0;
        int maxTries = calculateTries(length);
        do {
            tries++;
            if (tries > maxTries) {
                //TODO: throw error??? => this should not happen!
            }
            rnd = RandomStringUtils.randomAlphabetic(length);
        } while (assignedStrings.contains(rnd));
        assignedStrings.add(rnd);
        return rnd;
    }
    
    public void init(FromClause fromClause) {
        for(Table table: fromClause) {
            String alias = table.getAlias();
            if(alias != null) {
                reserve(alias);
            }
        }
    }
    
    public void reserve(String s) {
        assignedStrings.add(s);
    }

    private static int calculateTries(int stringLength) {
        return 100; //TODO: return useful value
    }

}
