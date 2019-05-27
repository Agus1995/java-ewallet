package com.finalproject.walletforex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tb_wallet")
public class Wallet {
    @Id
    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "wallet_name")
    private String walletName;

    @Column(name = "cif")
    private String cif;
}
