package th.project.enterprise.Repository;

import org.springframework.data.jpa.repository.Modifying;
import th.project.enterprise.Entity.Adress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdressRepository extends CrudRepository<Adress, Long> {


    @Query("select count(a) from Adress a where a.street=:st and a.hausNumber=:hn and a.city=:city " +
            "and a.country=:country and a.zip=:zip")
    int chechIfAdressIsAlreadyExisted(String st, String hn, String city, String country, int zip);

    @Query("select a from Adress a where a.street=:st and a.hausNumber=:hn and a.city=:city " +
            "and a.country=:country and a.zip=:zip")
    Adress getIdAressThatAlreadyexisted(String st, String hn, String city, String country, int zip);

    @Modifying
    @Query("update Order o set o.adress=:adr where o.orderId=:oid")
    void updateOrderAdress(Adress adr, long oid);


}
