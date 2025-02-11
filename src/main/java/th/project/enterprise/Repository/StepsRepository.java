package th.project.enterprise.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import th.project.enterprise.Entity.RankDTO;
import th.project.enterprise.Entity.Steps;

import java.util.List;

public interface StepsRepository extends CrudRepository<Steps, Long> {

//    @Query("SELECT SUM(s.steps_number) as su, t.teamName " +
//            "FROM Steps s " +
//            "JOIN s.user u " +
//            "JOIN u.team t " +
//            "GROUP BY t.teamName order by su desc ")
//    List<RankDTO> getStepsSumByTeam();

    @Query("SELECT new th.project.enterprise.Entity.RankDTO(SUM(s.steps_number), t.teamName) " +
            "FROM Steps s " +
            "LEFT JOIN s.user u " +
            "LEFT JOIN u.team t " +
            "GROUP BY t.teamName " +
            "ORDER BY SUM(s.steps_number) DESC")
    List<RankDTO> getStepsSumByTeam();

    @Query("SELECT new th.project.enterprise.Entity.RankDTO(u.firstName, u.lastName, SUM(s.steps_number), u.Email )" +
            "FROM Steps s " +
            "JOIN s.user u " +
            "WHERE u.teamName =:teamName" +
            " GROUP BY u.firstName, u.lastName , u.Email " +
            " order by SUM(s.steps_number) DESC")
   List<RankDTO> getStepsSumByUserInTeam(String teamName);
}













