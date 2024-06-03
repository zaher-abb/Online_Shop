package th.project.enterprise.Entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@Entity
public class Employee extends User {
    protected String personnelNo;
    protected float salary;
    protected int vacationDays;
    protected float workingHours;

    public Employee() {
        super();

    }

    public Employee(String personnelNo, String lastName, String firstName) {
        super(lastName,firstName);
        this.personnelNo = personnelNo;

    }
}

