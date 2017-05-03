package com.allanvital.politicaaberta.api.endpoint;

import com.allanvital.politicaaberta.api.dto.ExpenseEntryDto;
import com.allanvital.politicaaberta.api.model.Expense;
import com.allanvital.politicaaberta.api.service.ExpenseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/expense")
public class ExpenseEndpoints {

	private static final Logger log = Logger.getLogger(ExpenseEndpoints.class);

	private String token;
	private ExpenseService service;

	@Autowired
	public ExpenseEndpoints(@Value("${api.token:TOKEN}") String token, ExpenseService service) {
		this.token = token;
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void postExpense(HttpServletResponse response, @RequestBody ExpenseEntryDto dto, @RequestHeader(required=false, value="token") String token) {
		if (!this.token.equals(token)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		log.info("Enviando evento de post de expense do deputy de siteId " + dto.getDeputySiteId() + " com valor " + dto.getValue());
		Expense expense = dto.buildExpense();
		service.idempotentSaveExpense(expense);
	}
	
}
