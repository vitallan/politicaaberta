package com.allanvital.politicaaberta.batch.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChamberEnvelop<T> {

    @JsonProperty("dados")
    public List<T> data;

    @JsonProperty("links")
    private List<Link> links;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "ChamberEnvelop{" +
                "data=" + data +
                ", links=" + links +
                '}';
    }
}
