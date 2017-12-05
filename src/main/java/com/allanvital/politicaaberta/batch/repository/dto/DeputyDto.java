package com.allanvital.politicaaberta.batch.repository.dto;

import com.allanvital.politicaaberta.model.Deputy;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.WordUtils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class DeputyDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("siglaPartido")
    private String partyName;

    @JsonProperty("uriPartido")
    private String partyUri;

    @JsonProperty("siglaUf")
    private String uf;

    @JsonProperty("idLegislatura")
    private Integer legislatureId;

    @JsonProperty("urlFoto")
    private String pictureUri;

    public Long getId() { return id; }

    public Long getOfficialId() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyUri() {
        return partyUri;
    }

    public void setPartyUri(String partyUri) {
        this.partyUri = partyUri;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getLegislatureId() {
        return legislatureId;
    }

    public void setLegislatureId(Integer legislatureId) {
        this.legislatureId = legislatureId;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public Deputy buildDeputy() {
        Deputy deputy = new Deputy();
        deputy.setUf(this.getUf());
        deputy.setName(WordUtils.capitalize(this.getName().toLowerCase()));
        deputy.setOfficialId(this.getOfficialId());
        deputy.setNormalizedName(this.normalizeName(deputy.getName()));
        return deputy;
    }

    private String normalizeName(String name) {
        String normalized = Normalizer
                .normalize(name, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll(" ", "-")
                .toLowerCase();
        return normalized;
    }

    @Override
    public String toString() {
        return "DeputyDto{" +
                "id=" + id +
                ", uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", partyName='" + partyName + '\'' +
                ", partyUri='" + partyUri + '\'' +
                ", uf='" + uf + '\'' +
                ", legislatureId=" + legislatureId +
                ", pictureUri='" + pictureUri + '\'' +
                '}';
    }
}
