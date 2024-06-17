package th.project.enterprise.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.project.enterprise.Entity.Steps;
import th.project.enterprise.Repository.StepsRepository;


@Service
public class StepsService {

    @Autowired
    private StepsRepository stepsRepository;
    public void addNewSteps(Steps steps) {
        stepsRepository.save(steps);
    }
}
