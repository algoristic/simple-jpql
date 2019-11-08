package de.algoristic.jpql;

public abstract class Property {

    private static QualifierParser<Property> propertyParser = new PropertyParser();

    protected String name;

    protected Property(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTableAlias())
            .append(".")
            .append(name);
        return sb.toString();
    }

    abstract public String getTableAlias();

    abstract protected void completeReferences(FromClause fromClause);

    public static Property of(String qualifier) {
        return propertyParser.parse(qualifier);
    }

    public static Property of(String tableAlias, String name) {
        return new UnboundedProperty(name, tableAlias);
    }

    public static Property of(Class<?> clazz, String name) {
        return new BoundedProperty(name, clazz);
    }

    public static Property of(Table table, String name) {
        return new BoundedProperty(name, table);
    }

}
