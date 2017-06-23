package infosec.security;

import infosec.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.zip.DataFormatException;

/**
 * Created by Administrator on 2017/6/23.
 */
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,DataAccessException{
        Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        infosec.model.User userinfo = userMapper.getByUserName(username);
        GrantedAuthority auth1 = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userinfo.getRolecode();
            }
        };
        auths.add(auth1);
        User user = new User(username, userinfo.getPassword(), auths);
        return user;
    }
}
