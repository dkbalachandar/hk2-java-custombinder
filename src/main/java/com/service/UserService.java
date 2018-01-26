package com.service;

import com.model.User;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface UserService {
    public User fetch(String id);

    public User add(User user);

    public void delete(String id);

    public User update(User user);
}