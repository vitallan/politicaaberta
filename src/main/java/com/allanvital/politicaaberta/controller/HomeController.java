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
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        int month = DateShortcuts.getLastMonth();
        int year = DateShortcuts.getLastMonthYear();

        List<ExpenseByMonthAndYear> monthlyExpenses = expenseByMonthRepository.findTop10ByMonthAndYearOrderByValueDesc(month, year);
        List<Expense> biggestExpenses = expenseRepository.findTop10ByMonthAndYearOrderByValueDesc(month, year);


        model.addAttribute("monthlyExpenses", monthlyExpenses);
        model.addAttribute("biggestExpenses", biggestExpenses);
        model.addAttribute("month", month + "");
        model.addAttribute("year", year + "");

        return "index";
    }

}
