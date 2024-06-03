package th.project.enterprise.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "eadress")
public class Adress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    private String street;
    private String hausNumber;
    private String city;
    private String country;
    private int zip;

    public Adress(String street, String hausNumber, String city, String country, int zip) {
        this.street = street;
        this.hausNumber = hausNumber;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public Adress() {
    }

}

