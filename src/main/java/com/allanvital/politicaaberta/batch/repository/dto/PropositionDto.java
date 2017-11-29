package com.allanvital.politicaaberta.batch.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropositionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("siglaTipo")
    private String typeInitials;

    @JsonProperty("idTipo")
    private Long typeId;

    @JsonProperty("numero")
    private Integer number;

    @JsonProperty("ano")
    private Integer year;

    @JsonProperty("ementa")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTypeInitials() {
        return typeInitials;
    }

    public void setTypeInitials(String typeInitials) {
        this.typeInitials = typeInitials;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PropositionDto{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", typeInitials='" + typeInitials + '\'' +
                ", typeId=" + typeId +
                ", number=" + number +
                ", year=" + year +
                ", description='" + description + '\'' +
                '}';
    }
}
