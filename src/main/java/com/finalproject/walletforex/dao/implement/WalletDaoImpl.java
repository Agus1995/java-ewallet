package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.WalletDao;
import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.model.Wallet;
import com.finalproject.walletforex.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<Wallet> findByCif(String cif) {
        List<Wallet> wallet = walletRepository.findByCustomer_Cif(cif);
        if (wallet.isEmpty()){
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
