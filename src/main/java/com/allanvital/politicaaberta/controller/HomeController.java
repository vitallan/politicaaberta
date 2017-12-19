package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.model.ExpenseByMonthAndYear;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import com.allanvital.politicaaberta.utils.DateShortcuts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private ExpenseByMonthRepository expenseByMonthRepository;
    private ExpenseRepository expenseRepository;

    public HomeController(ExpenseByMonthRepository expenseByMonthRepository, ExpenseRepository expenseRepository) {
        this.expenseByMonthRepository = expenseByMonthRepository;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping
    public String home (Model model) {
        int month = DateShortcuts.lastMonth();
        int year = DateShortcuts.yearFromLastMonth();

        List<ExpenseByMonthAndYear> monthlyExpenses = expenseByMonthRepository.findTop10ByMonthAndYearOrderByValueDesc(month, year);
        List<Expense> biggestExpenses = expenseRepository.findTop10ByMonthAndYearOrderByValueDesc(month, year);

        model.addAttribute("monthlyExpenses", monthlyExpenses);
        model.addAttribute("biggestExpenses", biggestExpenses);
        model.addAttribute("month", month + "");
        model.addAttribute("year", year + "");

        return "index";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

}
