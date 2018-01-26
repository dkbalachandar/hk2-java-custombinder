package com;

import com.config.DependencyBinder;
import com.dao.UserDao;
import com.dao.impl.UserGuavaCacheImpl;
import com.dao.impl.UserLocalCacheDaoImpl;
import com.model.User;
import com.service.UserService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

public class Application {

    public static void main(String[] args) {

        System.setProperty("CACHE", "GUAVA");
        //Create the Service locator
        ServiceLocator serviceLocator = ServiceLocatorFactory.getInstance().create("serviceLocator");
        //Bind the configurations to the created service locator
        ServiceLocatorUtilities.bind(serviceLocator, new DependencyBinder());

        //Get the UserService from the Service locator. We can still pass Named if we have more then one class that implement an interface.
        UserService userService = serviceLocator.getService(UserService.class, "empService");
        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");

        System.out.println("Add user details");
        User userAddResponse = userService.add(user);
        System.out.println("The user details after it has been added User: " + userAddResponse);

        System.out.println("Now fetch the user details with ID");
        User userFetchResponse = userService.fetch(user.getId());
        System.out.println("User :" + userFetchResponse);

        System.out.println("Now update the user details");
        user.setFirstName("Bala");
        user.setLastName("Samy");
        User userUpdateResponse = userService.update(user);
        System.out.println("User After Updation:" + userUpdateResponse);

        System.out.println("Now delete the user details");
        userService.delete(user.getId());
        System.out.println("User After Deletion:" + userService.fetch(user.getId()));

        System.out.println("\n");
        System.out.println("Testing HK2 injection");
        System.out.println("Fetching the various DAO implementation");
        //To get UserDao with named annotation.
        UserDao userDao = serviceLocator.getService(UserDao.class, "empGuavaCacheDao");
        if(userDao instanceof UserGuavaCacheImpl){
            System.out.println("UserDao userDao = serviceLocator.getService(UserDao.class, \"empGuavaCacheDao\")");
            System.out.println("userDao instanceof UserGuavaCacheImpl - > Its an instance of UserGuavaCacheImpl ");
        }
        userDao = serviceLocator.getService(UserDao.class, "empLocalCacheDao");
        if(userDao instanceof UserLocalCacheDaoImpl){
            System.out.println("userDao = serviceLocator.getService(UserDao.class, \"empLocalCacheDao\")");
            System.out.println("userDao instanceof UserLocalCacheDaoImpl - > Its an instance of UserLocalCacheDaoImpl ");
        }

    }
}