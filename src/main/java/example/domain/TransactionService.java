package example.domain;

import example.repository.trasnsaction.TransactionRepository;
import example.repository.account.Account;
import example.repository.trasnsaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction makeTransaction(final Transaction transaction, final Long requestingUserId) {
        final Account fromAccount = accountService.getById(transaction.getWithdrawalAccountId());

        assertUserIsOwnerOfAccount(fromAccount, requestingUserId);

        final Account toAccount = accountService.getById(transaction.getDepositAccountId());

        assertAccountCurrenciesMatch(fromAccount, toAccount);

        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getWithdrawalsForAccount(final Long accountId) {
        return transactionRepository.findByWithdrawalAccountId(accountId);
    }

    public Collection<Transaction> getDepositsForAccount(final Long accountId) {
        return transactionRepository.findByDepositAccountId(accountId);
    }

    private void assertAccountCurrenciesMatch(Account fromAccount, Account toAccount) {
        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            throw new RuntimeException("User not owner of account");
        }
    }

    private void assertUserIsOwnerOfAccount(final Account account, final Long userId) {
        if (!account.getOwner().getId().equals(userId)) {
            throw new RuntimeException("User not owner of account");
        }
    }

}
