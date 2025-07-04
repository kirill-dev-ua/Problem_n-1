package school.n1problem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_table")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public abstract class User extends BaseEntity {
    private String password;
    private String email;
    private String login;
    private String role;
}