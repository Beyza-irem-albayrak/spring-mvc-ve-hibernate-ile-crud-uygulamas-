package com.example.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.User;
import com.example.dao.UserDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<User> list() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public boolean delete(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        return true;
    }

    @Override
    public User getUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        logger.info("User deleted successfully, User details=" + user);
    }

    @Override
    public boolean updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return true;
    }
}
