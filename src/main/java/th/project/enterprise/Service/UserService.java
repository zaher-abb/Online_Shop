package th.project.enterprise.Service;

import th.project.enterprise.Entity.*;
import th.project.enterprise.Repository.UserRepoistory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepoistory userRepository;


    public void creatUser(Customer user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void creatUser(Employee employee) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode(employee.getPassword()));
    
        userRepository.save(employee);
    }

    public Customer findByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
    public Employee findEmpByEmail(String email) {
        return userRepository.getEmpByEmail(email);
    }
    public User findUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public boolean isCustomerPresent(String email) {
        Customer user = userRepository.getUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
    public boolean isUserPresent(String email) {
        User user = userRepository.getUserByEmail(email);
        User emp = userRepository.getEmpByEmail(email);
        if (user != null || emp != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Customer user = userRepository.getUserByEmail(s);
        Employee emp = userRepository.getEmpByEmail(s);
        UserDetail userDetails;
        if (user == null && emp == null) {
            throw new UsernameNotFoundException("user not exits with this name");
        }
        
        if(emp != null){
            return new UserDetail(emp);
            
        }else  {
            return new UserDetail(user);
    
    
        }
        
        
    }


    public void updateUserAdreesID(Adress adress, long uid) {
        userRepository.updateUserAdreesID(adress, uid);
    }

    public List<Customer> getAllCustomer() {
        return userRepository.getAllCustomer("ADMIN");
    }
   
    
    public List<Employee> getAllEmployees() {

      return  userRepository.getAllEmployees();
    }
    
    public void removeEmployee(long id) {
        
        userRepository.deleteEmployeeById(id);
    }
}

