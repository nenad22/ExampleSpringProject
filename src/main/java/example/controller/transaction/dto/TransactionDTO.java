package example.controller.transaction.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long withdrawalAccountId;
    private Long depositAccountId;
    private Long amount;
    private String executedAt;
}
