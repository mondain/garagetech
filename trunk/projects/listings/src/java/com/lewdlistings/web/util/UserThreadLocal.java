package com.lewdlistings.web.util;

import com.lewdlistings.entity.User;

public class UserThreadLocal {

    private static ThreadLocal<User> currentUser = new ThreadLocal<User>();

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }
}