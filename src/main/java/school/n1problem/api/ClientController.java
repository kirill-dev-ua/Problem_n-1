package school.n1problem.api;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import school.n1problem.ClientService;
import school.n1problem.db.*;
import school.n1problem.dto.ClientDto;
import school.n1problem.dto.OrderDto;
import school.n1problem.dto.PaymentDto;
import school.n1problem.model.Client;
import school.n1problem.model.Order;
import school.n1problem.model.Payment;

import java.util.List;

@AllArgsConstructor
@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    private final ClientRepository clientRepository;
    private final CustomClientRepository customClientRepository;
    private final ClientService clientService;

    @PostMapping("/clients")
    public void create(@RequestBody ClientDto dto) {
        clientService.save(dto);
    }

    @GetMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping("/clients")
    @Transactional
    public List<ClientDto> getAllClients() {
        log.info("Called method getAllClients()");

//        List<Client> clients = customClientRepository.findAllClientWithGraph();
//        List<Client> clients = clientRepository.findAllWithPayments();
//        List<Client> clients = clientRepository.findAllWithPaymentsWithGraph();
        List<Client> clients = clientRepository.findAll();

        var dtoList = clients.stream()
                .map(this::mapClientToDto)
                .toList();
        log.info("Method getAllClients() returning result");
        return dtoList;
    }

    private ClientDto mapClientToDto(Client client) {
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

    private PaymentDto mapPaymentToDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getAmount(),
                payment.getClientId()
        );
    }

    private OrderDto mapOrderToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getNumber(),
                order.getClientId()
        );
    }

    @PutMapping("/{id}")
    @Transactional
    public Client update(@PathVariable Long id, @RequestBody ClientDto dto) {
        Client existing = clientRepository.findById(id).orElseThrow();
        existing.setName(dto.name());
        existing.getPayments().get(0).setAmount(dto.payments().get(0).amount());
        existing.getPayments().get(0).setAmount(dto.payments().get(1).amount());
        return clientRepository.save(existing);
    }
}

