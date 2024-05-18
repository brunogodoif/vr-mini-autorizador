package br.com.example.brunogodoif.vrminiautorizador.infrastructure.controllers.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardCreateRequest {

    @NotBlank(message = "O número do cartão é obrigatório")
    @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas dígitos")
    @Size(min = 16, max = 16, message = "O número do cartão deve ter exatamente 16 dígitos")
    private final String numeroCartao;

    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "\\d+", message = "A senha deve conter apenas dígitos")
    @Size(min = 4, max = 4, message = "A senha deve ter exatamente 4 dígitos")
    private final String senha;
}
