package school.n1problem.dto;

public record PaymentDto(
        Long id,
        Double amount,
        Long clientId
) {
}
