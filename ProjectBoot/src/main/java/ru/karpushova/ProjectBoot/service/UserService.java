package ru.karpushova.ProjectBoot.service;


import ru.karpushova.ProjectBoot.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user) throws Exception;

    void updateUser(User updateUser) throws Exception;

    void removeUserById(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);


}
