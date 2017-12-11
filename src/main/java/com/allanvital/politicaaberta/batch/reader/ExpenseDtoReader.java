package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import com.allanvital.politicaaberta.utils.DateShortcuts;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class ExpenseDtoReader extends AbstractDtoReader<ExpenseDto> {

    private final static Logger log = Logger.getLogger(ExpenseDtoReader.class.getName());
    private DeputiesChamberRepository repository;
    private String deputyOfficialId;

    public ExpenseDtoReader(DeputiesChamberRepository repository, @Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId) {
        super(log);
        this.repository = repository;
        this.deputyOfficialId = deputyOfficialId;
    }

    @Override
    protected List<ExpenseDto> findDtos(int page) {
        int year = DateShortcuts.yearFromLastMonth();
        int month = DateShortcuts.lastMonth();
        return repository.findExpensesFromDeputy(new Long(deputyOfficialId), year, month, page).getData();
    }

}
