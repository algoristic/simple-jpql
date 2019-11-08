package de.algoristic.jpql;

public class PropertyParser implements QualifierParser<Property> {

    @Override
    public Property parse(String s) {
        s = s.trim();
        String name = null;
        String tableAlias = null;
        String[] tokens = s.split("\\.");
        if (tokens.length == 1) {
            name = tokens[0];
        } else if (tokens.length != 0) { // do this ONLY, when tokens.length >= 2
            tableAlias = tokens[0];
            name = tokens[1];
        }
        return new UnboundedProperty(name, tableAlias);
    }

}
