package school.n1problem.dto;

import java.util.List;

public record ClientDto(
        Long id,
        String name,
        List<PaymentDto> payments
//        Set<OrderDto> orders
) {
}
