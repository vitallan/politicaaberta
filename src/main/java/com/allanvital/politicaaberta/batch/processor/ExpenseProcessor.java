package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.model.Expense;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ExpenseProcessor implements ItemProcessor<DespesaXmlEntry, Expense>{

    @Override
    public Expense process(DespesaXmlEntry item) throws Exception {
        return null;
    }

}
