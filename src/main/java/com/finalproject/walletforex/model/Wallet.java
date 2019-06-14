package com.finalproject.walletforex.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name = "tb_wallet")
public class Wallet {
    @Id
    @Column(name = "wallet_id")
    private String walletId;
    @Column(name = "wallet_name")
    private String walletName;
    @ManyToOne
    @JoinColumn(name = "cif")
    private Customer customer;
    @CreationTimestamp
    @Column(name = "created_at", insertable=false,updatable=false)
    private Timestamp createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
