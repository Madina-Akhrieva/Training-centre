package com.epam.jwd.onlinetraining.controller.command.common;

import com.epam.jwd.onlinetraining.controller.core.PagePaths;

public class SimplePropertyContext implements PropertyContext {

    @Override
    public String get(String name) {
        return PagePaths.of(name.substring(5)).getPath();
    }

    static SimplePropertyContext getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final SimplePropertyContext INSTANCE = new SimplePropertyContext();
    }
}
