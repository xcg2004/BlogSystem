package com.xcg.blogsystem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class UserHolder {
    private static ThreadLocal<Long> userThreadLocal = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        userThreadLocal.set(userId);
    }
    public static Long getUserId() {
        return userThreadLocal.get();
    }
    public static void remove() {
        userThreadLocal.remove();
    }
}
