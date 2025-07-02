package school.n1problem.mapper;

import org.mapstruct.Mapper;
import school.n1problem.dto.ClientDto;
import school.n1problem.dto.ClientDto2;
import school.n1problem.dto.OrderDto;
import school.n1problem.dto.PaymentDto;
import school.n1problem.model.Client;
import school.n1problem.model.Order;
import school.n1problem.model.Payment;

@Mapper(componentModel = "spring")
public class ClientMapper {

    public static ClientDto mapClientToDto(Client client) {
        return new ClientDto(
                client.getName(),
                client.getPayments()
                        .stream()
                        .map(ClientMapper::mapPaymentToDto)
                        .toList()
//                client.getOrders()
//                        .stream()
//                        .map(this::mapOrderToDto)
//                        .collect(Collectors.toSet())
        );
    }

    public static ClientDto2 mapClientToDto2(Client client) {
        return new ClientDto2(
                client.getName()
        );
    }

    public static PaymentDto mapPaymentToDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getAmount(),
                payment.getClientId().getId()
        );
    }

    public OrderDto mapOrderToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getNumber(),
                order.getClientId()
        );
    }

    public Payment toPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.amount());
        return payment;
    }
}
