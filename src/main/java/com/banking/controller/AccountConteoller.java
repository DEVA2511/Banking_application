package com.banking.controller;

import com.banking.dto.AccountDto;
import com.banking.model.Account;
import com.banking.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountConteoller {
    private AccountService accountService;

    public AccountConteoller(AccountService accountService) {
        this.accountService = accountService;
    }
//    Add Acount Rest API
@PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return  new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

//    Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

//    Deposit amount Rest API;
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double >request){
Double amount=request.get("amount");
        AccountDto accountDto=accountService.deposit(id,amount);

        return ResponseEntity.ok(accountDto);
    }

//    withDraw Method
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withDraw(@PathVariable Long id, @RequestBody Map<String, Double >request){
        Double amount=request.get("amount");
        AccountDto accountDto=accountService.withDraw(id,amount);

        return ResponseEntity.ok(accountDto);
    }

//    Get all Account RESY API
    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accouts=accountService.getAllAccount();
        return ResponseEntity.ok(accouts);
    }

//    Delete REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully"+id);
    }
}
