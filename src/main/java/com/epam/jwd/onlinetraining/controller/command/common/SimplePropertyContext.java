package com.epam.jwd.onlinetraining.controller.command.common;

import com.epam.jwd.onlinetraining.controller.impl.PagePaths;

public class SimplePropertyContext implements PropertyContext {

    @Override
    public String get(String name) {
        if (name.startsWith("page.")) {
            return PagePaths.of(name.substring(5)).getPath();
        }
        return null; //todo: exception
    }

    static SimplePropertyContext getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        public static final SimplePropertyContext INSTANCE = new SimplePropertyContext();
    }
}
