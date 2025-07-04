package school.n1problem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "teacher")
@DiscriminatorValue("TEACHER")
public class Teacher extends User{
    private BigDecimal salary;
}
