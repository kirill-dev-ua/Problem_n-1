package school.n1problem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
@DiscriminatorValue("STUDENT")
public class Student extends User{
    private double averageGrade;
}
