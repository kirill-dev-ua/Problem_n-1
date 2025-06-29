package school.n1problem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import school.n1problem.db.ClientRepository;
import school.n1problem.model.Client;
import school.n1problem.model.Payment;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService2 {

    private final ClientRepository repository;

    @Transactional
    public void method1() {
        Client client = repository.findById(1L).get();
        method2(client);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void method2(Client client) {
        List<Payment> payments = client.getPayments();
        System.out.println(payments);
    }
}