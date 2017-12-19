package com.allanvital.politicaaberta.utils;

import java.text.Normalizer;

public class NormalizerShortcuts {

    public static String normalize(String name) {
        String normalized = Normalizer
                .normalize(name, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll(" ", "-")
                .toLowerCase();
        return normalized;
    }

}
