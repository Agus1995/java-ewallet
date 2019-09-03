package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.WalletAccountDao;
import com.finalproject.walletforex.dto.WalletAccountDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.model.Customer;
import com.finalproject.walletforex.model.WalletAccount;
import com.finalproject.walletforex.repository.WalletAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletAccountDaoImpl implements WalletAccountDao {

    @Autowired
    private WalletAccountRepository walletAccountRepository;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public WalletAccount register(WalletAccountDto dto) {
        WalletAccount walletAccount = setWalletAccount(dto);
        return walletAccountRepository.save(walletAccount);
    }

    @Override
    public List<WalletAccount> getRegistered(String cif) throws AccountNotFoundException, InterruptedException {
        List<WalletAccount> walletAccounts = new ArrayList<>();
        Customer customer = customerDao.findById(cif);
        walletAccounts = walletAccountRepository.findByAccountCustomer(customer);
        if (walletAccounts.isEmpty()){
            return walletAccounts;
        }
        return walletAccounts;
    }

    @Override
    public List<WalletAccount> findByWallet(String walletId) {
        return walletAccountRepository.findByWallet_WalletId(walletId);
    }

    @Override
    public void unreg(int id) {
        walletAccountRepository.deleteById(id);
    }

    private WalletAccount setWalletAccount(WalletAccountDto dto){
        WalletAccount walletAccount = new WalletAccount();
        walletAccount.setId(dto.getId());
        walletAccount.setAccount(dto.getAccount());
        walletAccount.setWallet(dto.getWallet());
        return walletAccount;
    }
}
