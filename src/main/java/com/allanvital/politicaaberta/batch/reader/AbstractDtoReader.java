package com.allanvital.politicaaberta.batch.reader;

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
    public T read() throws Exception {
        this.populateDtoList();
        T object = this.getDto();
        return object;
    }

    private T getDto() {
        if (dtos == null || dtos.isEmpty()) {
            log.debug("Nao ha novas paginas e a lista foi consumida. Terminando.");
            return null;
        }
        T dto = dtos.remove(0);
        log.debug("Efetuando leitura de " + dto);
        return dto;
    }

    private void populateDtoList() {
        if (this.dtos.isEmpty()) {
            currentPage++;
            log.debug("Listagem vazia, buscando pagina " + currentPage + " de dtos");
            this.dtos = findDtos(currentPage);
        }
    }

}
