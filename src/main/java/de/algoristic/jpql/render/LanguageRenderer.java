package de.algoristic.jpql.render;

import de.algoristic.jpql.sql.FromClause;
import de.algoristic.jpql.sql.SelectClause;
import de.algoristic.jpql.sql.SelectCommand;
import de.algoristic.jpql.sql.WhereClause;

public class LanguageRenderer {

    private SelectCommand selectCommand;

    public LanguageRenderer(SelectCommand executableSelect) {
        this.selectCommand = executableSelect;
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        { //render select-clause
            SelectClause selectClause = selectCommand.getSelectClause();
            Renderer selectClauseRenderer = selectClause.getRenderer();
            sb.append(selectClauseRenderer.render());
        }
        { //render from-clause
            FromClause fromClause = selectCommand.getFromClause();
            Renderer fromClauseRenderer = fromClause.getRenderer();
            sb.append(fromClauseRenderer.render());
        }
        { //render where-clause
            WhereClause whereClause = selectCommand.getWhereClause();
            Renderer whereClauseRenderer = whereClause.getRenderer();
            sb.append(whereClauseRenderer.render());
        }
        return sb.toString();
    }

}
