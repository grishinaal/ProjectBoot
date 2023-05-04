package ru.karpushova.ProjectBoot.dao;


import ru.karpushova.ProjectBoot.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    void updateUser(User updateUser);

    void removeUserById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

}
