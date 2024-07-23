package com.banking.services.impl;

import com.banking.dto.AccountDto;
import com.banking.mapper.AccountMapper;
import com.banking.model.Account;
import com.banking.repository.AccountRepository;
import com.banking.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccounctServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccounctServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
      Account savedAccount=  accountRepository.save(account);
        return AccountMapper.mapToAccountDto((savedAccount));
    }

    @Override
    public AccountDto getAccountById(Long id) {
      Account account=  accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=  accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
double total=account.getBalance()+amount;
account.setBalance(total);
Account savedAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto withDraw(Long id, double amount) {
        Account account=  accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
      if(account.getBalance()<amount){
          throw  new RuntimeException("Insuffisent amount");
      }

        double withDraw=account.getBalance()-amount;
        account.setBalance(withDraw);
        Account savedAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(account);
    }


    @Override
    public List<AccountDto> getAllAccount() {
//        Account account=  accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));
List<Account>accounts=accountRepository.findAll();
return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account=  accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't exists"));

//        if(account.getId()!=id){
//            throw  new RuntimeException("Insuffisent amount");
//        }
//
        accountRepository.deleteById(id);
    }
}
