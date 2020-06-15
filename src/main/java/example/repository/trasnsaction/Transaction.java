package example.repository.trasnsaction;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private Long withdrawalAccountId;
    private Long depositAccountId;
    private Long amount;
    private Instant executedAt;
}
