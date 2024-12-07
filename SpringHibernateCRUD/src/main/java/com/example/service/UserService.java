package com.example.service;

import java.util.List;
import com.example.entity.User;

public interface UserService {
    public List<User> list(); // Tüm kullanıcıları listelemek için

    public boolean delete(User user); // Kullanıcıyı silmek için

    public boolean saveUser(User user); // Yeni kullanıcı eklemek için

    public boolean updateUser(User user); // Kullanıcı güncellemek için

    public User getUser(int id); // ID ile kullanıcı bilgisi almak için

    public void removeUser(int id); // Kullanıcıyı ID ile silmek için
}
