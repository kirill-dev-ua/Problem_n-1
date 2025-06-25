package school.n1problem;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import school.n1problem.db.ClientRepository;
import school.n1problem.dto.ClientDto;
import school.n1problem.dto.PaymentDto;
import school.n1problem.model.Client;
import school.n1problem.model.Payment;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public ClientDto findById(Long id){
        Client client = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapClient(client);
    }

    private ClientDto mapClient(Client client){
        return new ClientDto(
                client.getId(),
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
}
