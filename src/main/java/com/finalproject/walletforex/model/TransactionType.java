package com.finalproject.walletforex.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_transaction_type")
public class TransactionType {
    @Id
    private String type;
    private String description;

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
