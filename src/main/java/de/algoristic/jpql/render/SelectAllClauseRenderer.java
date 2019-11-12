package de.algoristic.jpql.render;

import java.util.List;
import java.util.stream.Collectors;

import de.algoristic.jpql.Property;
import de.algoristic.jpql.Select;
import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.FullTableProperty;
import de.algoristic.jpql.sql.SelectClause;

public class SelectAllClauseRenderer extends SelectClauseRenderer {

    private SelectClause selectClause;

    public SelectAllClauseRenderer(SelectClause selectClause) {
        super(selectClause);
        this.selectClause = selectClause;
    }

    @Override
    public String render() {
        Select parentOperation = selectClause.getParentOperation();
        FromClause fromClause = parentOperation.getFromClause();
        List<Property> tableProperties = fromClause.stream().map(FullTableProperty::new).collect(Collectors.toList());
        return super.render(tableProperties.stream().map(property -> (Renderable) property));
    }

}
