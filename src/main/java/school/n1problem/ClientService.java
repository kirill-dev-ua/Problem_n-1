package school.n1problem;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.n1problem.db.ClientRepository;
import school.n1problem.dto.ClientDto;
import school.n1problem.dto.PaymentDto;
import school.n1problem.model.Client;
import school.n1problem.model.Payment;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional(rollbackForClassName = "java.io.IOException")
    public ClientDto findById(Long id){
        Client client = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapClient(client);
    }

    private ClientDto mapClient(Client client){
        return new ClientDto(
                client.getName(),
                client.getPayments()
                        .stream()
                        .map(this::mapPaymentToDto)
                        .toList());
    }

    private PaymentDto mapPaymentToDto(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getAmount(),
                payment.getClientId()
        );
    }

    public void save(ClientDto dto) {
        Client c = new Client();
        c.setName(dto.name());
        dto.payments().forEach(p ->
                c.getPayments().add(new Payment(null, p.amount(), null)));
        repository.save(c);
    }
}