package com.allanvital.politicaaberta.batch.repository.dto;

import com.allanvital.politicaaberta.model.Expense;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.WordUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class ExpenseDto {

    @JsonProperty("ano")
    private int year;

    @JsonProperty("mes")
    private int month;

    @JsonProperty("tipoDespesa")
    private String description;

    @JsonProperty("idDocumento")
    private Integer documentId;

    @JsonProperty("tipoDocumento")
    private String documentType;

    @JsonProperty("idTipoDocumento")
    private Integer documentTypeId;

    @JsonProperty("dataDocumento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date documentDate;

    @JsonProperty("numDocumento")
    private String documentNumber;

    @JsonProperty("valorDocumento")
    private BigDecimal documentGrossValue;

    @JsonProperty("urlDocumento")
    private String documentUrl;

    @JsonProperty("nomeFornecedor")
    private String providerName;

    @JsonProperty("cnpjCpfFornecedor")
    private String cnpjCpf;

    @JsonProperty("valorLiquido")
    private BigDecimal documentNetValue;

    @JsonProperty("valorGlosa")
    private BigDecimal documentGlossValue;

    @JsonProperty("numRessarcimento")
    private Integer refund;

    @JsonProperty("idLote")
    private Integer lotId;

    @JsonProperty("parcela")
    private Integer installment;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public BigDecimal getDocumentGrossValue() {
        return documentGrossValue;
    }

    public void setDocumentGrossValue(BigDecimal documentGrossValue) {
        this.documentGrossValue = documentGrossValue;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public BigDecimal getDocumentNetValue() {
        return documentNetValue;
    }

    public void setDocumentNetValue(BigDecimal documentNetValue) {
        this.documentNetValue = documentNetValue;
    }

    public BigDecimal getDocumentGlossValue() {
        return documentGlossValue;
    }

    public void setDocumentGlossValue(BigDecimal documentGlossValue) {
        this.documentGlossValue = documentGlossValue;
    }

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public Integer getLotId() {
        return lotId;
    }

    public void setLotId(Integer lotId) {
        this.lotId = lotId;
    }

    public Integer getInstallment() {
        return installment;
    }

    public void setInstallment(Integer installment) {
        this.installment = installment;
    }

    public Expense buildExpense() {
        Expense expense = new Expense();
        expense.setValue(this.getDocumentGrossValue());
        expense.setDescription(WordUtils.capitalize(this.getDescription().toLowerCase()));
        expense.setReceiver(WordUtils.capitalize(this.getProviderName().toLowerCase()));
        expense.setCpfCpnj(this.getCnpjCpf());

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, this.getMonth());
        calendar.set(Calendar.YEAR, this.getYear());
        expense.setExpenseDate(calendar.getTime());

        return expense;
    }

    @Override
    public String toString() {
        return "ExpenseDto{" +
                "year=" + year +
                ", month=" + month +
                ", description='" + description + '\'' +
                ", documentId=" + documentId +
                ", documentType='" + documentType + '\'' +
                ", documentTypeId=" + documentTypeId +
                ", documentDate=" + documentDate +
                ", documentNumber='" + documentNumber + '\'' +
                ", documentGrossValue=" + documentGrossValue +
                ", documentUrl='" + documentUrl + '\'' +
                ", providerName='" + providerName + '\'' +
                ", cnpjCpf='" + cnpjCpf + '\'' +
                ", documentNetValue=" + documentNetValue +
                ", documentGlossValue=" + documentGlossValue +
                ", refund=" + refund +
                ", lotId=" + lotId +
                ", installment=" + installment +
                '}';
    }
}
