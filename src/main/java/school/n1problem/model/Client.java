package school.n1problem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
//@DynamicUpdate
//@NamedEntityGraph(
//        name = "clients_with_payments",
//        attributeNodes = {
//                @NamedAttributeNode("payments")
//        }
//)
//для двух и более связей
//@NamedEntityGraph(
//        name = "clients_full",
//        attributeNodes = {
//                @NamedAttributeNode("payments"),
//                @NamedAttributeNode("orders")
//        }
//)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE,
            orphanRemoval = true)
    @JoinColumn(name = "client_id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Payment> payments = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "client_id")
//    @Fetch(FetchMode.SUBSELECT)
//    private Set<Order> orders = new HashSet<>();
}
