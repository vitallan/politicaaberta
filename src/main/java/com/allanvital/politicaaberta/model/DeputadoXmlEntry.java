package com.allanvital.politicaaberta.model;

import org.springframework.util.StringUtils;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="Deputado")
public class DeputadoXmlEntry {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private Integer ideCadastro;
    private Integer numLegislatura;
    private String nomeParlamentar;
    private String SEXO;

    @XmlElement(name="Profissao")
    private String Profissao;

    @XmlElement(name="LegendaPartidoEleito")
    private String LegendaPartidoEleito;
    private String UFEleito;

    @XmlElement(name="Condicao")
    private String Condicao;

    @XmlElement(name="SiTuacaoMandato")
    private String SiTuacaoMandato;

    @XmlElement(name="Matricula")
    private Integer Matricula;

    @XmlElement(name="Gabinete")
    private Integer Gabinete;

    @XmlElement(name="Anexo")
    private Integer Anexo;

    @XmlElement(name="Fone")
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

    public Deputy buildDeputy() {
        Deputy deputy = new Deputy();
        deputy.setDeputyXmlEntryId(this.id);
        deputy.setName(StringUtils.capitalize(this.getNomeParlamentar()));
        deputy.setUf(this.getUFEleito());
        return deputy;
    }
}

