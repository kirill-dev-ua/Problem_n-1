package school.n1problem.db;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.n1problem.model.Client;
import school.n1problem.model.Payment;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c join fetch c.payments")
    List<Client> findAllWithPayments();

    @Query("select c from Client c")
    @EntityGraph(attributePaths = {"payments"})
    List<Client> findAllWithPaymentsWithGraph();
}
