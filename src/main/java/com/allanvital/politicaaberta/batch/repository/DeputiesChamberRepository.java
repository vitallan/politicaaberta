package com.allanvital.politicaaberta.batch.repository;

import com.allanvital.politicaaberta.batch.repository.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@FeignClient(name = "chamberApi", url = "https://dadosabertos.camara.leg.br/api/v2/")
public interface DeputiesChamberRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/deputados?ordenarPor=nome&itens=100&pagina={page}")
    ChamberEnvelop<DeputyDto> findDeputies(@RequestParam(value="page")int page);

    @RequestMapping(method = RequestMethod.GET, value = "/deputados/{id}/despesas?ano={year}&mes={month}&itens=100&pagina={page}")
    ChamberEnvelop<ExpenseDto> findExpensesFromDeputy(@RequestParam(value="id") Long officialDeputyId, @RequestParam(value="year") int year, @RequestParam(value="month") int month, @RequestParam(value="page") int page);

    @RequestMapping(method = RequestMethod.GET, value = "/deputados/{id}/orgaos?itens=100&pagina={page}")
    ChamberEnvelop<CommitteeDto> findCommitteesFromDeputy(@RequestParam(value="id") Long officialDeputyId, @RequestParam(value="page") int page);

    @RequestMapping(method = RequestMethod.GET, value = "/referencias/tiposProposicao")
    ChamberEnvelop<PropositionTypeDto> findPropositionTypes();

    @RequestMapping(method = RequestMethod.GET, value = "/proposicoes?idAutor={id}&itens=100&pagina={page}")
    ChamberEnvelop<PropositionDto> findPropositionsFromDeputy(@RequestParam(value="id") Long officialDeputyId, @RequestParam(value="page") int page);

}
