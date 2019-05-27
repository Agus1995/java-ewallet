package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.WalletDao;
import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.model.Wallet;
import com.finalproject.walletforex.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class WalletDaoImpl implements WalletDao {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet addWallet(WalletDto dto) {
        Wallet wallet = setWallet(dto);
        wallet.setWalletId("1234");
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByCif(String cif) {
        Wallet wallet = walletRepository.findByCustomer_Cif(cif);
        if (wallet==null){
            return null;
        }
        return wallet;
    }

    private Wallet setWallet(WalletDto dto){
        Wallet wallet = new Wallet();
        wallet.setWalletId(dto.getWalletId());
        wallet.setWalletName(dto.getWalletName());
        wallet.setCustomer(dto.getCustomer());

        return wallet;
    }
}
