package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.model.ExpenseByMonthAndYear;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExpenseController {

    private ExpenseByMonthRepository repository;

    public ExpenseController(ExpenseByMonthRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping(value = "/mais-gastadores", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ExpenseByMonthAndYear getMostExpensiveDeputies() {
        return repository.findOne(1L);
    }

}
