package com.chokealot.konyiguitar.guitar;

import lombok.Data;

@Data
public class Guitar {

    private Long id;
    private String name;
    private String type;
    private int productionYear;
    private String information;
    private double price;

}
