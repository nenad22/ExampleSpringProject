package example.repository.trasnsaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Collection<Transaction> findByWithdrawalAccountId(final Long accountId);

    Collection<Transaction> findByDepositAccountId(final Long accountId);
}