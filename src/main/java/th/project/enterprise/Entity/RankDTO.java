package th.project.enterprise.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RankDTO {

    private String teamName;
    private long stepsSum;
    private int teamMemberCount;
    private String user;

    public RankDTO(long stepsSum,String teamName){
        this.teamName=teamName;
        this.stepsSum = stepsSum;
    }
    public RankDTO(String user, long stepsSum){
        this.user=user;
        this.stepsSum = stepsSum;
    }
//    public RankDTO(int stepsSum,Team teamName){
//        this.teamName=teamName;
//        this.stepsSum = stepsSum;
//    }
}
