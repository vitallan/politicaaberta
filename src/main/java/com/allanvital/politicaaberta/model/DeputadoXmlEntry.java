package com.allanvital.politicaaberta.model;

import org.apache.commons.lang3.text.WordUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@XmlRootElement(name="Deputado")
public class DeputadoXmlEntry {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    private Long ideCadastro;
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
    public Long getIdeCadastro() {
        return ideCadastro;
    }
    public void setIdeCadastro(Long ideCadastro) {
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
        deputy.setIdeCadastro(this.id);
        deputy.setName(WordUtils.capitalize(this.getNomeParlamentar().toLowerCase()));
        deputy.setUf(this.getUFEleito());
        return deputy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeputadoXmlEntry xmlEntry = (DeputadoXmlEntry) o;

        if (id != null ? !id.equals(xmlEntry.id) : xmlEntry.id != null) return false;
        if (ideCadastro != null ? !ideCadastro.equals(xmlEntry.ideCadastro) : xmlEntry.ideCadastro != null)
            return false;
        if (numLegislatura != null ? !numLegislatura.equals(xmlEntry.numLegislatura) : xmlEntry.numLegislatura != null)
            return false;
        if (nomeParlamentar != null ? !nomeParlamentar.equals(xmlEntry.nomeParlamentar) : xmlEntry.nomeParlamentar != null)
            return false;
        if (SEXO != null ? !SEXO.equals(xmlEntry.SEXO) : xmlEntry.SEXO != null) return false;
        if (Profissao != null ? !Profissao.equals(xmlEntry.Profissao) : xmlEntry.Profissao != null) return false;
        if (LegendaPartidoEleito != null ? !LegendaPartidoEleito.equals(xmlEntry.LegendaPartidoEleito) : xmlEntry.LegendaPartidoEleito != null)
            return false;
        if (UFEleito != null ? !UFEleito.equals(xmlEntry.UFEleito) : xmlEntry.UFEleito != null) return false;
        if (Condicao != null ? !Condicao.equals(xmlEntry.Condicao) : xmlEntry.Condicao != null) return false;
        if (SiTuacaoMandato != null ? !SiTuacaoMandato.equals(xmlEntry.SiTuacaoMandato) : xmlEntry.SiTuacaoMandato != null)
            return false;
        if (Matricula != null ? !Matricula.equals(xmlEntry.Matricula) : xmlEntry.Matricula != null) return false;
        if (Gabinete != null ? !Gabinete.equals(xmlEntry.Gabinete) : xmlEntry.Gabinete != null) return false;
        if (Anexo != null ? !Anexo.equals(xmlEntry.Anexo) : xmlEntry.Anexo != null) return false;
        return Fone != null ? Fone.equals(xmlEntry.Fone) : xmlEntry.Fone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ideCadastro != null ? ideCadastro.hashCode() : 0);
        result = 31 * result + (numLegislatura != null ? numLegislatura.hashCode() : 0);
        result = 31 * result + (nomeParlamentar != null ? nomeParlamentar.hashCode() : 0);
        result = 31 * result + (SEXO != null ? SEXO.hashCode() : 0);
        result = 31 * result + (Profissao != null ? Profissao.hashCode() : 0);
        result = 31 * result + (LegendaPartidoEleito != null ? LegendaPartidoEleito.hashCode() : 0);
        result = 31 * result + (UFEleito != null ? UFEleito.hashCode() : 0);
        result = 31 * result + (Condicao != null ? Condicao.hashCode() : 0);
        result = 31 * result + (SiTuacaoMandato != null ? SiTuacaoMandato.hashCode() : 0);
        result = 31 * result + (Matricula != null ? Matricula.hashCode() : 0);
        result = 31 * result + (Gabinete != null ? Gabinete.hashCode() : 0);
        result = 31 * result + (Anexo != null ? Anexo.hashCode() : 0);
        result = 31 * result + (Fone != null ? Fone.hashCode() : 0);
        return result;
    }
}

