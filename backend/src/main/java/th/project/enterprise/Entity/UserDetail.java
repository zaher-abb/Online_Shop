package th.project.enterprise.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {


    private Customer user;
    private Employee emp;


    public UserDetail(Customer user) {
        super();
        this.user = user;

    }
    public UserDetail(Employee emp) {
        super();
        this.emp = emp;
        
    }

    public Customer getUser() {
        
        return user;
    }
    
    public Employee getEmp() {
        
        return emp;
    }

    public void setUser(Customer user) {
        this.user = user;
    }
    public void setEmployee(Employee emp) {
        this.emp = emp;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + getroles());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        
        if(user == null) {
            
            return  emp.getPassword();
        }else{
            return user.getPassword();
    
    
        }
    }

    @Override
    public String getUsername() {
    
        if(user == null) {
        
            return  emp.getEmail();
        }else{
            return user.getEmail();
        
        
        }
      
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFirstname() {
        
        if(user == null) {
        
            return  emp.getFirstName();
        }else{
            return user.getFirstName();
        
        
        }
        
    }

    public String getroles() {
    
        if(user == null) {
    
            return emp.getRoles();
        }else{
            return user.getRoles();
        
        
        }
    }


}
