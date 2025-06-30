package school.n1problem.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.n1problem.db.ClientRepository;
import school.n1problem.db.CustomClientRepository;
import school.n1problem.dto.ClientDto;
import school.n1problem.dto.PaymentDto;
import school.n1problem.mapper.ClientMapper;
import school.n1problem.model.Client;
import school.n1problem.model.Payment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository repository;
    private final CustomClientRepository customClientRepository;
    private final ClientMapper clientMapper;

    @Transactional(rollbackForClassName = "java.io.IOException", readOnly = true)
    public ClientDto findById(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return clientMapper.mapClientToDto(client);
    }

    @Transactional
    public void save(ClientDto dto) {
        Client c = new Client();
        c.setName(dto.name());
        dto.payments().forEach(p ->
                c.getPayments().add(new Payment(null, p.amount(), null)));
        repository.save(c);
    }

    @Transactional(readOnly = true)
    public List<ClientDto> findAllClients() {
//        List<Client> clients = customClientRepository.findAllClientWithGraph();
//        List<Client> clients = repository.findAllWithPayments();
//        List<Client> clients = repository.findAllWithPaymentsWithGraph();

        var clients = repository.findAll();
        var dtoList = clients.stream()
                .map(clientMapper::mapClientToDto)
                .toList();
        return dtoList;
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        client.setName(dto.name());
        List<PaymentDto> paymentDtos = dto.payments();
        List<Payment> payments = client.getPayments();

        for (int i = 0; i < Math.min(payments.size(), paymentDtos.size()); i++) {
            payments.get(i).setAmount(paymentDtos.get(i).amount());
        }

        Client saved = repository.save(client);
        return clientMapper.mapClientToDto(saved);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void testOrphanRemoval(Long clientId) {
        Client client = repository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + clientId));
        List<Payment> payments = client.getPayments();
        if (!payments.isEmpty()) {
            Payment toRemove = payments.get(0);
            client.getPayments().remove(toRemove);
            toRemove.setClientId(null);
        }
    }

    @Transactional
    public void testCascadeRemove(Long clientId) {
        Client client = repository.findById(clientId).orElseThrow();
        repository.delete(client);
    }
}