package th.project.enterprise.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Adding a no-args constructor for subclasses
@MappedSuperclass // Define User as a mapped superclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adress_id")
    private Adress adress;

    private String roles;
    private Gender gender;
    private LocalDate dateOfBirth;

    @NotNull
    @Size(min = 4)
    private String password;

    @NotNull
    @Column(nullable = false, unique = true)
    private String Email;

    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFullname() {
        return this.getFirstName() + " " + this.getLastName();
    }


}
