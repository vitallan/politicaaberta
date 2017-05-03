package com.allanvital.politicaaberta.api.endpoint;

import com.allanvital.politicaaberta.api.dto.DeputyEntryDto;
import com.allanvital.politicaaberta.api.model.Deputy;
import com.allanvital.politicaaberta.api.service.DeputyService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/deputy")
public class DeputyEndpoints {

	private static final Logger log = Logger.getLogger(DeputyEndpoints.class);
	
	private String token;
	private DeputyService service;

	@Autowired
	public DeputyEndpoints(@Value("${api.token:TOKEN}") String token, DeputyService service){
		this.token = token;
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void postDeputy(HttpServletResponse response, @RequestBody DeputyEntryDto deputyDto, @RequestHeader(required=false, value="token") String token) {
		if (!this.token.equals(token)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		log.info("Enviando evento de post de deputy de siteId: " + deputyDto.getSiteId());
		Deputy deputy = deputyDto.buildDeputy();
		service.saveDeputy(deputy);
	}

}
