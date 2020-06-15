package example.repository.account;

import example.repository.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User owner;
    private String currency;
    private Instant createdAt;
}
