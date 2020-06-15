package example.objectmappers;

import example.controller.account.dto.AccountDTO;
import example.repository.account.Account;

public class AccountObjectMapper {

    public static AccountDTO accountDTOFromAccount(final Account account) {
        return new AccountDTO(
                account.getId(),
                account.getOwner(),
                account.getCurrency(),
                account.getCreatedAt()
        );
    }
}
