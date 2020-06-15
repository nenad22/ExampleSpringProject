package example.domain;

import example.repository.account.Account;
import example.repository.account.AccountRepository;
import example.repository.trasnsaction.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.AdditionalMatchers.or;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;
    @MockBean
    private AccountRepository accountRepository;

    private Long equalDepositWithdrawalAmountAccountId;
    private Long noDepositAmountAccountId;
    private Long noWithdrawalAmountAccountId;


    @Before
    public void setUp() {

        equalDepositWithdrawalAmountAccountId = 0L;
        noDepositAmountAccountId = 1L;
        noWithdrawalAmountAccountId = 2L;

        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(new Account()));

        mockWithdrawalAccount();
        mockDepositAccount();
    }

    @Test
    public void calculatesEqualAccountBalance() {

        final Long balance = accountService.getAccountBalance(equalDepositWithdrawalAmountAccountId);

        assertEquals(balance, Long.valueOf(0));
    }

    @Test
    public void calculatesAccountBalanceWithNoWithdrawal() {

        final Long balance = accountService.getAccountBalance(noWithdrawalAmountAccountId);

        assertEquals(balance, Long.valueOf(2));
    }

    @Test
    public void calculatesAccountBalanceWithNoDeposit() {

        final Long balance = accountService.getAccountBalance(noDepositAmountAccountId);

        assertEquals(balance, Long.valueOf(-2));
    }

    private void mockWithdrawalAccount() {
        when(transactionService.getWithdrawalsForAccount(
                or(
                        eq(equalDepositWithdrawalAmountAccountId),
                        eq(noDepositAmountAccountId)
                )
        )).thenReturn(
                Arrays.asList(
                        Transaction.builder().amount(1L).build(),
                        Transaction.builder().amount(1L).build()
                )
        );

        when(transactionService.getWithdrawalsForAccount(noWithdrawalAmountAccountId))
                .thenReturn(Collections.EMPTY_LIST);
    }

    private void mockDepositAccount() {
        when(transactionService.getDepositsForAccount(
                or(
                        eq(equalDepositWithdrawalAmountAccountId),
                        eq(noWithdrawalAmountAccountId)
                )
        )).thenReturn(
                Arrays.asList(
                        Transaction.builder().amount(1L).build(),
                        Transaction.builder().amount(1L).build()
                )
        );
        when(transactionService.getDepositsForAccount(noDepositAmountAccountId)).thenReturn(Collections.EMPTY_LIST);
    }

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public AccountService accountService() {
            return new AccountService();
        }
    }
}