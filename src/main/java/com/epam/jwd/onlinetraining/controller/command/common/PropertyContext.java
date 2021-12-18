package com.epam.jwd.onlinetraining.controller.command.common;

public interface PropertyContext {
    String get(String name);

    static PropertyContext instance(){
        return SimplePropertyContext.getInstance();
    }
}
