package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.batch.repository.dto.DeputyDto;
import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDtoReader<T> implements ItemReader<T> {

    private Logger log;

    private int currentPage = 0;

    private List<T> dtos = new LinkedList<>();

    protected abstract List<T> findDtos(int page);

    public AbstractDtoReader(Logger log) {
        this.log = log;
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        this.populateDtoList();
        T deputy = this.getDto();
        return deputy;
    }

    private T getDto() {
        if (dtos.isEmpty()) {
            log.info("Nao ha novas paginas e a lista foi consumida. Terminando.");
            return null;
        }
        T dto = dtos.remove(0);
        log.info("Efetuando leitura de " + dto);
        return dto;
    }

    private void populateDtoList() {
        if (this.dtos.isEmpty()) {
            currentPage++;
            log.info("Listagem vazia, buscando pagina " + currentPage + " de dtos");
            this.dtos = findDtos(currentPage);
        }
    }

}
