package com.finalproject.walletforex.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction_type")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
