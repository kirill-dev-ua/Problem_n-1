package school.n1problem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import school.n1problem.db.ClientRepository;
import school.n1problem.model.Client;

@Service
@RequiredArgsConstructor
public class ClientService3 {

    private final ClientRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void innerNotSupported() {
        Client b = new Client();
        b.setName("Client B");
        repository.save(b);
        throw new RuntimeException("Error");
    }
}