package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.service.DeputyService;
import com.allanvital.politicaaberta.service.pojo.DeputyDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deputado")
public class DeputyController {

    private DeputyService service;

    public DeputyController(DeputyService service) {
        this.service = service;
    }

    @GetMapping("/{deputyNormalizedName}")
    public String deputyDetail(@PathVariable String deputyNormalizedName, Model model) {
        DeputyDetail detail = service.getDeputyDetail(deputyNormalizedName);

        model.addAttribute("deputy", detail);

        return "deputy";
    }

}
