package org.com.database;

import org.com.events.CreateUser;

public interface DBservice {
    void saveUser(CreateUser createUser);
}
