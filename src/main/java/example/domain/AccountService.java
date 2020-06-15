package example.domain;

import example.repository.account.AccountRepository;
import example.repository.account.Account;
import example.repository.trasnsaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account getById(final Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Long getAccountBalance(final Long accountId) {
        final Account account = accountRepository.findById(accountId).orElseThrow();

        final Collection<Transaction> withdrawalTransactions = transactionService.getWithdrawalsForAccount(accountId);
        final Collection<Transaction> depositTransactions = transactionService.getDepositsForAccount(accountId);

        long balance = 0L;

        balance -= withdrawalTransactions.stream().mapToLong(Transaction::getAmount).sum();
        balance += depositTransactions.stream().mapToLong(Transaction::getAmount).sum();

        return balance;
    }
}
