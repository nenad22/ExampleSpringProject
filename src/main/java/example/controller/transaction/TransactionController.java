package example.controller.transaction;

import example.controller.transaction.dto.TransactionDTO;
import example.controller.transaction.dto.TransactionRequest;
import example.domain.TransactionService;
import example.objectmappers.TransactionObjectsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/")
    public TransactionDTO makeTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {

        final Long currentUserId = 1L;

        return TransactionObjectsMapper.transactionDTOFromTransaction(
                transactionService.makeTransaction(
                        TransactionObjectsMapper.transactionFromTransactionRequest(transactionRequest),
                        currentUserId
                )
        );
    }
}
