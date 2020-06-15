package example.controller.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequest {
    private @NotNull Long withdrawalAccountId;
    private @NotNull Long depositAccountId;
    private @NotNull @Min(1) Long amount;
}
