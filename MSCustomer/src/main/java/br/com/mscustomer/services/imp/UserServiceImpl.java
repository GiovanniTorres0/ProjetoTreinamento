package br.com.mscustomer.services.imp;

import br.com.mscustomer.models.User;
import br.com.mscustomer.repository.UserRepository;
import br.com.mscustomer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class UserServiceImpl<User, Long extends Serializable> implements UserService<User, Long> {


    @Override
    public User save(User entity) {
        try {
            return getDao().save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            getDao().deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(Long id) {
        try {
            Optional<User> obj = getDao().findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getAll() {
        try {
            List<User> returnList = new ArrayList<>();
            getDao().findAll().forEach(obj -> returnList.add(obj));
            return returnList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public abstract CrudRepository<User, Long> getDao();


}
