package com.back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private long id;
    private String name;
    private String description;
    private Date itemDate;
    private double price;
    private Category category;
}
