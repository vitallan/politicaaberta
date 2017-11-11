package com.allanvital.politicaaberta.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DeputadoXmlEntry {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private Integer ideCadastro;
    private Integer numLegislatura;
    private String nomeParlamentar;
    private String SEXO;
    private String Profissao;
    private String LegendaPartidoEleito;
    private String UFEleito;
    private String Condicao;
    private String SiTuacaoMandato;
    private Integer Matricula;
    private Integer Gabinete;
    private Integer Anexo;
    private String Fone;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getIdeCadastro() {
        return ideCadastro;
    }
    public void setIdeCadastro(Integer ideCadastro) {
        this.ideCadastro = ideCadastro;
    }
    public Integer getNumLegislatura() {
        return numLegislatura;
    }
    public void setNumLegislatura(Integer numLegislatura) {
        this.numLegislatura = numLegislatura;
    }
    public String getNomeParlamentar() {
        return nomeParlamentar;
    }
    public void setNomeParlamentar(String nomeParlamentar) {
        this.nomeParlamentar = nomeParlamentar;
    }
    public String getSEXO() {
        return SEXO;
    }
    public void setSEXO(String sEXO) {
        SEXO = sEXO;
    }
    public String getProfissao() {
        return Profissao;
    }
    public void setProfissao(String profissao) {
        Profissao = profissao;
    }
    public String getLegendaPartidoEleito() {
        return LegendaPartidoEleito;
    }
    public void setLegendaPartidoEleito(String legendaPartidoEleito) {
        LegendaPartidoEleito = legendaPartidoEleito;
    }
    public String getUFEleito() {
        return UFEleito;
    }
    public void setUFEleito(String uFEleito) {
        UFEleito = uFEleito;
    }
    public String getCondicao() {
        return Condicao;
    }
    public void setCondicao(String condicao) {
        Condicao = condicao;
    }
    public String getSiTuacaoMandato() {
        return SiTuacaoMandato;
    }
    public void setSiTuacaoMandato(String siTuacaoMandato) {
        SiTuacaoMandato = siTuacaoMandato;
    }
    public Integer getMatricula() {
        return Matricula;
    }
    public void setMatricula(Integer matricula) {
        Matricula = matricula;
    }
    public Integer getGabinete() {
        return Gabinete;
    }
    public void setGabinete(Integer gabinete) {
        Gabinete = gabinete;
    }
    public Integer getAnexo() {
        return Anexo;
    }
    public void setAnexo(Integer anexo) {
        Anexo = anexo;
    }
    public String getFone() {
        return Fone;
    }
    public void setFone(String fone) {
        Fone = fone;
    }
    @Override
    public String toString() {
        return "DeputadoXmlEntry [id=" + id + ", ideCadastro=" + ideCadastro + ", numLegislatura=" + numLegislatura + ", nomeParlamentar=" + nomeParlamentar + ", SEXO=" + SEXO + ", Profissao=" + Profissao + ", LegendaPartidoEleito=" + LegendaPartidoEleito
            + ", UFEleito=" + UFEleito + ", Condicao=" + Condicao + ", SiTuacaoMandato=" + SiTuacaoMandato + ", Matricula=" + Matricula + ", Gabinete=" + Gabinete + ", Anexo=" + Anexo + ", Fone=" + Fone + "]";
    }
    
}
