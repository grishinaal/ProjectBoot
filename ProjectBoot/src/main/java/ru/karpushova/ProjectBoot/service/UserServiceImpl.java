package ru.karpushova.ProjectBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karpushova.ProjectBoot.dao.UserDao;
import ru.karpushova.ProjectBoot.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void saveUser(User user) throws Exception {
        if (validator(user)) {
            userDao.saveUser(user);
        } else throw new Exception("Только буквы!");
    }

    public boolean validator(User user) {
        String name = user.getName();
        String lastName = user.getLastName();
        if (lastName.matches("^[a-zA-Z]+$") && name.matches("^[a-zA-Z]+$")) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateUser(User updateUser) throws Exception {
        if (validator(updateUser)) {
            userDao.updateUser(updateUser);
        } else throw new Exception("Только буквы!");
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {

        return userDao.getUserById(id);
    }

}
