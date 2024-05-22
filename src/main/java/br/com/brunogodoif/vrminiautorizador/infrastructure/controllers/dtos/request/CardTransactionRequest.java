package br.com.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CardTransactionRequest(
        @Schema(description = "Número do cartão", example = "1234567890123456")
        @NotBlank(message = "O número do cartão é obrigatório")
        @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas dígitos")
        @Size(min = 16, max = 16, message = "O número do cartão deve ter exatamente 16 dígitos")
        String numeroCartao,

        @Schema(description = "Senha do cartão", example = "1234") @NotBlank(message = "A senha é obrigatória")
        @Pattern(regexp = "\\d+", message = "A senha deve conter apenas dígitos") @Size(min = 4, max = 4, message = "A senha deve ter exatamente 4 dígitos")
        String senhaCartao,

        @Schema(description = "Valor da transação", example = "100.00")
        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior a 0.00")
        double valor) {

}