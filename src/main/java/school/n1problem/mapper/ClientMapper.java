package school.n1problem.mapper;

import school.n1problem.dto.ClientDto;
import school.n1problem.dto.OrderDto;
import school.n1problem.dto.PaymentDto;
import school.n1problem.model.Client;
import school.n1problem.model.Order;
import school.n1problem.model.Payment;

public class ClientMapper {

    public ClientDto mapClientToDto(Client client) {
        return new ClientDto(
                client.getName(),
                client.getPayments()
                        .stream()
                        .map(this::mapPaymentToDto)
                        .toList()
//                client.getOrders()
//                        .stream()
//                        .map(this::mapOrderToDto)
//                        .collect(Collectors.toSet())
        );
    }

    public PaymentDto mapPaymentToDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getAmount(),
                payment.getClientId()
        );
    }

    public OrderDto mapOrderToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getNumber(),
                order.getClientId()
        );
    }
}
