package com.config;

import com.dao.UserDao;
import com.dao.impl.UserGuavaCacheImpl;
import com.dao.impl.UserLocalCacheDaoImpl;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class DependencyBinder extends AbstractBinder
{
    @Override
    protected void configure() {
        bind(UserServiceImpl.class).to(UserService.class).named("empService").in(Singleton.class);
        bind(UserGuavaCacheImpl.class).to(UserDao.class).named("empGuavaCacheDao").in(Singleton.class);
        bind(UserLocalCacheDaoImpl.class).to(UserDao.class).named("empLocalCacheDao").in(Singleton.class);
    }
}