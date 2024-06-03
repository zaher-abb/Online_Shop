package th.project.enterprise.Service;

import th.project.enterprise.Entity.Adress;
import th.project.enterprise.Repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdressService {

    @Autowired
    AdressRepository adressRepository;

    public void addNewAdress(Adress adr) {

        adressRepository.save(adr);

    }

    public boolean chechIfAdressIsAlreadyExisted(String st, String hn, String city, String country, int zip) {
        if (adressRepository.chechIfAdressIsAlreadyExisted(st, hn, city, country, zip) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Adress getIdAressThatAlreadyexisted(String st, String hn, String city, String country, int zip) {

        return adressRepository.getIdAressThatAlreadyexisted(st, hn, city, country, zip);
    }


}
