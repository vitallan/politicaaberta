package com.allanvital.politicaaberta.api.repository;

import com.allanvital.politicaaberta.api.model.TableData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// i got stucked with hql trying to do these selects, but failed
// there is, probably, a better way
@Repository
public class SummarizedDataFromLastMonthRepository {

    private static final Logger log = Logger.getLogger(SummarizedDataFromLastMonthRepository.class);

    private JdbcTemplate template;

    @Autowired
    public SummarizedDataFromLastMonthRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<TableData> getMostExpensiveDeputies() {
        final String sql = "" +
                "SELECT d.id id, d.name description, sum(e.value) value " +
                "FROM deputy d INNER JOIN expense e ON d.id = e.deputy_id " +
                "WHERE YEAR(e.month_and_year) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) " +
                "AND MONTH(e.month_and_year) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) " +
                "GROUP BY d.id, d.name " +
                "ORDER BY value DESC " +
                "LIMIT 15 ";
        List<TableData> tableData = template.query(sql, new SummarizedDataRowMapper());
        return tableData;
    }

    public List<TableData> getMostExpensiveParties() {
        final String sql = "" +
                "SELECT p.id id, p.description description, sum(e.value) value " +
                "FROM deputy d INNER JOIN expense e ON d.id = e.deputy_id " +
                "INNER JOIN party p ON p.id = d.party_id " +
                "WHERE YEAR(e.month_and_year) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) " +
                "AND MONTH(e.month_and_year) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) " +
                "GROUP BY p.id, p.description " +
                "ORDER BY value DESC " +
                "LIMIT 15 ";
        List<TableData> tableData = template.query(sql, new SummarizedDataRowMapper());
        return tableData;
    }

    public List<TableData> getMostExpensiveAverageDeputyFromParty() {
        final String sql = "" +
                "SELECT p.id id, p.description description, AVG(s.value) value " +
                "FROM ( " +
                    "SELECT d.id, d.party_id party_id, SUM(e.value) value " +
                    "FROM deputy d INNER JOIN expense e ON d.id = e.deputy_id " +
                    "WHERE YEAR(e.month_and_year) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)  " +
                    "AND MONTH(e.month_and_year) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) " +
                    "GROUP BY d.id, d.party_id " +
                    ") s INNER JOIN party p ON s.party_id = p.id " +
                "GROUP BY p.id, p.description " +
                "ORDER BY value DESC " +
                "LIMIT 15";
        List<TableData> tableData = template.query(sql, new SummarizedDataRowMapper());
        return tableData;
    }

    private class SummarizedDataRowMapper implements RowMapper<TableData> {

        @Override
        public TableData mapRow(ResultSet resultSet, int i) throws SQLException {
            String description = resultSet.getString("description");
            BigDecimal value = resultSet.getBigDecimal("value");
            Long id = resultSet.getLong("id");

            TableData data = new TableData(id, description, value.setScale(2, RoundingMode.CEILING));
            log.info("Dados de " + description + " sumarizados em " + value);
            return data;
        }

    }

}
