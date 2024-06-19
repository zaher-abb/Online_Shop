package th.project.enterprise.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import th.project.enterprise.Entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {


        boolean existsByTeamName(String teamName);
        Team findByTeamName(String teamName);
}
