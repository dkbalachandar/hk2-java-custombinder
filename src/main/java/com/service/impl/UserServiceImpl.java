package com.service.impl;

import com.CacheDetails;
import com.dao.UserDao;
import com.model.User;
import com.service.UserService;
import org.glassfish.hk2.api.IterableProvider;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Inject
    public UserServiceImpl(IterableProvider<UserDao> iterableProviders) {
        String cache = System.getProperty("CACHE");
        if (CacheDetails.GUAVA.name().equals(cache)) {
            this.userDao = iterableProviders.named("empGuavaCacheDao").get();
        }
        else if(CacheDetails.LOCAL.name().equals(cache)) {
            this.userDao = iterableProviders.named("empLocalCacheDao").get();
        }
    }

    @Override
    public User fetch(String id) {
        return userDao.fetch(id);
    }

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }
}