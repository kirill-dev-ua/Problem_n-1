package school.n1problem.dto;

import java.util.List;

public record ClientDto(
        String name,
        List<PaymentDto> payments
//        Set<OrderDto> orders
) {
}
