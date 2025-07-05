package school.n1problem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import school.n1problem.db.ClientRepository;
import school.n1problem.model.Client;

@Service
@RequiredArgsConstructor
public class ClientService2 {

    private final ClientRepository repository;
    private final ClientService3 clientService3;
    private final ClientService2 clientService2;

    @Transactional(noRollbackFor = RuntimeException.class)
    public void outerWithRollbackTest() {
        Client a = new Client();
        a.setName("Client A");
        repository.save(a);
//        innerNotSupported();
//        clientService2.innerNotSupported();
        clientService3.innerNotSupported();
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    public void innerNotSupported() {
//        Client b = new Client();
//        b.setName("Client B");
//        repository.save(b);
//        throw new RuntimeException("Error");
//    }
}