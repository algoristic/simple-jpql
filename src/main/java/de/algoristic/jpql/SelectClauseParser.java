package de.algoristic.jpql;

import java.util.ArrayList;
import java.util.List;

public class SelectClauseParser implements QualifierParser<SelectClause> {
    
    private QualifierParser<Property> propertyParser = new PropertyParser();

    @Override
    public SelectClause parse(String s) {
        s = s.trim();
        List<Property> properties = new ArrayList<>();
        String[] propertyTokens = s.split(",");
        for(String propertyToken: propertyTokens) {
            Property property = propertyParser.parse(propertyToken);
            properties.add(property);
        }
        return new SelectClause(properties);
    }

}
