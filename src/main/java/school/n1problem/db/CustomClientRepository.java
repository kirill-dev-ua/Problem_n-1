package school.n1problem.db;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import school.n1problem.model.Client;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomClientRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<Client> findAllClientWithGraph() {
        EntityGraph<Client> graph = entityManager.createEntityGraph(Client.class);
        graph.addAttributeNodes("payments");
//        graph.addAttributeNodes("orders");

        return entityManager.createQuery("select c from Client c", Client.class)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getResultList();
    }
}
