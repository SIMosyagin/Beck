package demo.dao.implementation;


import demo.dao.interfaces.UserInterfaceDao;
import demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserInterfaceDao")
public class UserDao extends AbstractDao implements UserInterfaceDao {

    @Autowired
    BCryptPasswordEncoder encoder;

    public void save(Users user){
        persist(user);
    }

    public void saveOrUpdateIfExist(Users user){
        saveOrUpdate(user);
    }

    public List<Users> selectAllUser (){
        List<Users> users = getSession().createQuery("From Users").list();
        return  users;
    }

    public Users getUserByNickName(String nickName){
        Users user = getSession().createQuery("From Users  Where nickname = :nick", Users.class)
                .setParameter("nick", nickName)
                .getSingleResult();
        return user;
    }

    public void delete (Users user){
        getSession().delete(user);
    }


}
