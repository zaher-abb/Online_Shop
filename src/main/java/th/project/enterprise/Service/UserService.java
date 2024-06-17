package th.project.enterprise.Service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import th.project.enterprise.Entity.Adress;
import th.project.enterprise.Entity.Steps;
import th.project.enterprise.Entity.User;
import th.project.enterprise.Entity.UserDetail;
import th.project.enterprise.Repository.UserRepoistory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepoistory userRepoistory;


    public void creatUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepoistory.save(user);
    }

    public User findByEmail(String email) {
        return userRepoistory.getUserByEmail(email);
    }

    public boolean isUserPresent(String email) {
        User user = userRepoistory.getUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepoistory.getUserByEmail(s);
        UserDetail userDetails;
        if (user == null) {
            throw new UsernameNotFoundException("user not exits with this name");
        }
        return new UserDetail(user);
    }


    public void updateUserAdreesID(Adress adress, long uid) {
        userRepoistory.updateUserAdreesID(adress, uid);
    }


}

