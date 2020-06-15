package example.objectmappers;

import example.controller.transaction.dto.TransactionDTO;
import example.controller.transaction.dto.TransactionRequest;
import example.repository.trasnsaction.Transaction;

public class TransactionObjectsMapper {
    public static Transaction transactionFromTransactionRequest(final TransactionRequest transactionRequest) {
        return Transaction.builder()
                .amount(transactionRequest.getAmount())
                .withdrawalAccountId(transactionRequest.getWithdrawalAccountId())
                .depositAccountId(transactionRequest.getDepositAccountId())
                .build();
    }

    public static TransactionDTO transactionDTOFromTransaction(final Transaction transaction) {
        return TransactionDTO.builder()
                .amount(transaction.getAmount())
                .executedAt(transaction.getExecutedAt().toString())
                .withdrawalAccountId(transaction.getWithdrawalAccountId())
                .depositAccountId(transaction.getDepositAccountId())
                .id(transaction.getId())
                .build();
    }
}
