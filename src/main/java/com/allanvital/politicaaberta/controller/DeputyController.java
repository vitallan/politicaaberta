package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.service.DeputyService;
import com.allanvital.politicaaberta.service.pojo.DeputyDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.Path;

@Controller
@RequestMapping("/deputados")
public class DeputyController {

    private DeputyService service;

    public DeputyController(DeputyService service) {
        this.service = service;
    }

    @GetMapping
    public String deputySearch(@RequestParam(value = "query", required = false) String query, Model model) {
        if (!StringUtils.isEmpty(query)) {
            model.addAttribute("deputies", service.searchDeputies(query));
            model.addAttribute("query", query);
        }
        return "deputy_search";
    }

    @GetMapping("/{deputyNormalizedName}")
    public String deputyDetail(@PathVariable String deputyNormalizedName, Model model) {
        DeputyDetail detail = service.getDeputyDetail(deputyNormalizedName);

        model.addAttribute("deputy", detail);

        return "deputy";
    }

}
