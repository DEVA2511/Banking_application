package com.banking.services;

import com.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {
AccountDto createAccount(AccountDto accountDto);
AccountDto getAccountById(Long id);

AccountDto deposit(Long id,double amount);

AccountDto withDraw(Long id, double amount);

List<AccountDto> getAllAccount();

void deleteAccount(Long id);

}
