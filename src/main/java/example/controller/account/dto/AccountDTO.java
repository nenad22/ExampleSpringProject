package example.controller.account.dto;

import example.repository.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private User owner;
    private String currency;
    private Instant createdAt;
}
