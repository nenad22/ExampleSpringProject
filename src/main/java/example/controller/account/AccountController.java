package example.controller.account;

import example.controller.account.dto.AccountDTO;
import example.domain.AccountService;
import example.objectmappers.AccountObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public AccountDTO getAccount(@PathVariable final Long id) {
        return AccountObjectMapper.accountDTOFromAccount(accountService.getById(id));
    }

    @GetMapping("/{id}/balance")
    public Long getAccountBalance(@PathVariable final Long id) {
        return accountService.getAccountBalance(id);
    }
}
