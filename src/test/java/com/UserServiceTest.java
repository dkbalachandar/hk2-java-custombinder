package com;

import com.model.User;
import com.service.UserService;
import org.junit.Test;
import org.jvnet.hk2.testing.junit.HK2Runner;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserServiceTest extends HK2Runner{

    @Inject
    private UserService userService;

    @Test
    public void testAdd(){

        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        User newEmp = userService.add(user);
        assertNotNull(newEmp);
        assertEquals("First Name", newEmp.getFirstName());
        assertEquals("Last Name", newEmp.getLastName());
        assertNotNull(newEmp.getId());

    }

    @Test
    public void testFetch(){
        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        User newEmp = userService.add(user);
        User fetchEmp = userService.fetch(newEmp.getId());
        assertEquals(newEmp, fetchEmp);
    }

    @Test
    public void testUpdate(){

        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        User newEmp = userService.add(user);
        newEmp.setFirstName("Bala");
        userService.update(newEmp);
        User fetchEmp = userService.fetch(newEmp.getId());
        assertEquals("Bala", fetchEmp.getFirstName());

    }

    @Test
    public void testDelete(){

        User user = new User();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        User newEmp = userService.add(user);
        userService.delete(newEmp.getId());
        User fetchEmp = userService.fetch(newEmp.getId());
        assertNull(fetchEmp);

    }


}