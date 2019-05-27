package com.finalproject.walletforex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "tb_wallet_taccount")
public class WalletAccount {
    @Id
    @Column(name = "wallet_accid")
    private String walletAccid;

    @Column(name = "accNumber")
    private String accNumber;

    @Column(name = "walletid")
    private String walletId;
}