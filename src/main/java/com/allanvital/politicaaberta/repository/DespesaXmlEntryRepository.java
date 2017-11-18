package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface DespesaXmlEntryRepository extends CrudRepository<DespesaXmlEntry, Long> {

    DespesaXmlEntry findByNumMesAndNumAnoAndVlrDocumentoAndTxtCNPJCPFAndIdeCadastro(
            int numMes, int NumAno, BigDecimal vlrDocumento, String txtCNPJCPF, int IdeCadastro
    );

}
