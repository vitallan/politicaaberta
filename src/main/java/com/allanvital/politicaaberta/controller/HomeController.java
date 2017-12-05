package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.model.ExpenseByMonthAndYear;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    private ExpenseByMonthRepository expenseByMonthRepository;

    public HomeController(ExpenseByMonthRepository expenseByMonthRepository) {
        this.expenseByMonthRepository = expenseByMonthRepository;
    }

    @GetMapping
    public ModelAndView home (Map<String, Object> model) {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        int month = lastMonth.getMonthValue();
        int year = lastMonth.getYear();

        List<ExpenseByMonthAndYear> expenses = expenseByMonthRepository.findTop10ByMonthAndYearOrderByValueDesc(month, year);

        model.put("expenses", expenses);
        model.put("month", month);
        model.put("year", year);

        return new ModelAndView("index", model);
    }

}
