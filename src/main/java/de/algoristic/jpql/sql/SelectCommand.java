package de.algoristic.jpql.sql;

import static de.algoristic.jpql.sql.StatementPreparator.prepareQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.algoristic.jpql.Condition;
import de.algoristic.jpql.Select;
import de.algoristic.jpql.render.SQLRenderer;

public class SelectCommand extends Select {
    
    private static Logger logger = LogManager.getLogger(SelectCommand.class);

    private WhereClause whereClause;
    private SharedQueryInformation queryInformation;

    public SelectCommand(SelectClause selectClause, FromClause fromClause) {
        super(selectClause);
        this.fromClause = fromClause;
        this.whereClause = WhereClause.noConditions;
    }

    public WhereClause getWhereClause() {
        return whereClause;
    }

    public SelectCommand where(Condition condition) {
        this.whereClause = new WhereClause(condition);
        return this;
    }

    public String query() {
        queryInformation = prepareQuery(this);
        SQLRenderer renderer = new SQLRenderer(this);
        String qlString = renderer.render();
        logger.debug(qlString);
        return qlString;
    }

    public Query query(EntityManager em) {
        String qlString = this.query();
        Query query = em.createQuery(qlString);
        for (ComplexParameter parameter : queryInformation.getComplexParameters()) {
            query.setParameter(parameter.getName(), parameter.getValue());
        }
        return query;
    }

}
