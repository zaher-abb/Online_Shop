package th.project.enterprise.Repository;

import th.project.enterprise.Entity.Adress;
import th.project.enterprise.Entity.Steps;
import th.project.enterprise.Entity.Team;
import th.project.enterprise.Entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepoistory extends CrudRepository<User, Long> {

    @Query("SELECT m from User m WHERE m.Email=:Email")
    User getUserByEmail(String Email);

    @Modifying
    @Query("update User u set u.adress=:adress where u.id=:uid")
    void updateUserAdreesID(Adress adress, long uid);

    @Modifying
    @Query("update User u set u.team=:team where u.id=:uid")
    void updateUserTeam(Team team, long uid);
}
