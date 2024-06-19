package th.project.enterprise.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import th.project.enterprise.Entity.Team;
import th.project.enterprise.Entity.User;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {


        boolean existsByTeamName(String teamName);

        Team findByTeamName(String teamName);

//        @Query("SELECT u FROM User u , Team t where  U.team.id = t.id and t.teamName LIKE %:team%")
//        List<User> getAllUserInTeam(Team team);

}
