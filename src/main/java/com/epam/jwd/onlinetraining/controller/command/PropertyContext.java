package com.epam.jwd.onlinetraining.controller.command;

public interface PropertyContext {
    String get(String name);

    static PropertyContext instance(){
        return SimplePropertyContext.getInstance();
    }
}
