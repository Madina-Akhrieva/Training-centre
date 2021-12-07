package com.epam.jwd.onlinetraining.controller.command;

public class SimplePropertyContext implements PropertyContext{
    @Override
    public String get(String name) {
        return PagePaths.of(name).getPath();
    }

    static SimplePropertyContext getInstance(){
        return Holder.INSTANCE;
    }

    private static class Holder{
        public static final SimplePropertyContext INSTANCE = new SimplePropertyContext();
    }
}
