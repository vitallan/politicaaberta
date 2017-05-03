package com.allanvital.politicaaberta.api.endpoint;

import com.allanvital.politicaaberta.api.model.TableData;
import com.allanvital.politicaaberta.api.repository.SummarizedDataFromLastMonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TableDataEndpoints {

    private SummarizedDataFromLastMonthRepository repository;

    @Autowired
    public TableDataEndpoints(SummarizedDataFromLastMonthRepository repository) {
        this.repository = repository;
    }
    
    @RequestMapping("/api/summarized/expensive-deputies")
    public List<TableData> getMostExpensiveDeputies() {
        return repository.getMostExpensiveDeputies();
    }

    @RequestMapping("/api/summarized/expensive-parties")
    public List<TableData> getMostExpensiveParties() {
        return repository.getMostExpensiveParties();
    }

    @RequestMapping("/api/summarized/expensive-average-deputy")
    public List<TableData> getMostExpensiveAverageDeputyFromParty() {
        return repository.getMostExpensiveAverageDeputyFromParty();
    }

}
