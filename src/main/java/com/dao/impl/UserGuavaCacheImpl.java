package com.dao.impl;

import com.dao.UserDao;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.model.User;
import org.jvnet.hk2.annotations.Service;

import java.util.UUID;

@Service
public class UserGuavaCacheImpl implements UserDao {

    LoadingCache<String, User> userCache = CacheBuilder.newBuilder().maximumSize(100)
            .build(new CacheLoader<String, User>() {
                @Override
                public User load(String userId) throws Exception {
                    return null;
                }
            });

    @Override
    public User fetch(String id) {
        return userCache.getIfPresent(id);
    }

    @Override
    public User add(User user) {
        user.setId(UUID.randomUUID().toString());
        userCache.put(user.getId(), user);
        return userCache.getIfPresent(user.getId());
    }

    @Override
    public void delete(String id) {
        userCache.invalidate(id);
    }

    @Override
    public User update(User user) {
        userCache.put(user.getId(), user);
        return userCache.getIfPresent(user.getId());
    }
}