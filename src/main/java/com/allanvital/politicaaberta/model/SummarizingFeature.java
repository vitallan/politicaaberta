package com.allanvital.politicaaberta.model;

public enum SummarizingFeature {

    EXPENDITURE_BY_DEPUTY_BY_MONTH(1);

    private int id;

    SummarizingFeature(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SummarizingFeature parse(int id) {
        for(SummarizingFeature feature : SummarizingFeature.values()) {
            if (feature.getId() == id) {
                return feature;
            }
        }
        return null;
    }

}
