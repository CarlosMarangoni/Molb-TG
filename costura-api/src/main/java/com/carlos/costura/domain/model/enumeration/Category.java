package com.carlos.costura.domain.model.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum Category {

    MASCARA("mascara"),
    LINGERIE("lingerie"),
    CAMISETA("camiseta"),
    CALCA("calca"),
    SHORTS("shorts"),
    VESTIDO("vestido"),
    DIVERSOS("diversos");

    private static class Holder {
        static Map<String, Category> CATEGORY_MAP = new HashMap<>();
    }

    private final String category;

    Category(String category) {
        this.category = category;
        Holder.CATEGORY_MAP.put(category, this);
    }

    public String getCategory() {
        return this.category;
    }

    public static Category convertFromString(String category) {
        return Holder.CATEGORY_MAP.get(category);
    }


    }
