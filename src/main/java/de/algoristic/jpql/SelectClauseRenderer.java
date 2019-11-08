package de.algoristic.jpql;


public class SelectClauseRenderer {
    
    private SelectClause selectClause;

    public SelectClauseRenderer(SelectClause selectClause) {
        this.selectClause = selectClause;
    }

    public String render() {
        StringBuilder sb = new StringBuilder("SELECT").append(" ");
        if(selectClause.isSelectAll()) {
            Select parentOperation = selectClause.getParentOperation();
            FromClause fromClause = parentOperation.getFromClause();
            for(Table table: fromClause) {
                String alias = table.getAlias();
                sb.append(alias)
                    .append(", ");
            }
        } else {
            for(Property property: selectClause) {
                String tableAlias = property.getTableAlias();
                String name = property.getName();
                sb.append(tableAlias)
                    .append(".")
                    .append(name)
                    .append(", ");
            }
        }
        sb = new StringBuilder(sb.substring(0, (sb.length() - 2))); //remove single trailing comma
        sb.append(" ");
        return sb.toString();
    }

}
