package de.algoristic.jpql;

class TableParser implements QualifierParser<Table> {

    private RandomStringProvider rsp;

    public TableParser(RandomStringProvider rsp) {
        this.rsp = rsp;
    }

    @Override
    public Table parse(String s) {
        s = s.trim();
        String[] tokens = s.split(" ");
        String name = tokens[0]; //TODO: NPE? => in case of "" as tablename
        String alias;
        if (tokens.length > 1) {
            alias = tokens[1];
            rsp.reserve(alias);
        } else {
            alias = rsp.getRandom();
        }
        return new Table(name, alias);
    }

    static String determineTableName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

}
