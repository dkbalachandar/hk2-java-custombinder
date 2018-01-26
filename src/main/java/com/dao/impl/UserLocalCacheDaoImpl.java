package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import org.jvnet.hk2.annotations.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserLocalCacheDaoImpl implements UserDao {

    private static Map<String, User> userLocalCache = new HashMap<>();

    public User fetch(String id) {
        return userLocalCache.get(id);
    }

    public User add(User user) {
        user.setId(UUID.randomUUID().toString());
        userLocalCache.put(user.getId(), user);
        return userLocalCache.get(user.getId());
    }

    public void delete(String id) {
        userLocalCache.remove(id);
    }

    public User update(User user) {
        userLocalCache.put(user.getId(), user);
        return userLocalCache.get(user.getId());
    }
}