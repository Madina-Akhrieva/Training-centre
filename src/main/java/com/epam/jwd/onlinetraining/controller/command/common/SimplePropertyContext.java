package com.epam.jwd.onlinetraining.controller.command.common;

import com.epam.jwd.onlinetraining.controller.core.PagePaths;

/**
 * com.epam.jwd.onlinetraining.controller.command.common public class SimplePropertyContext
 * extends Object
 * implements PropertyContext
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class SimplePropertyContext implements PropertyContext {

    private static class Holder {
        public static final SimplePropertyContext INSTANCE = new SimplePropertyContext();
    }

    static SimplePropertyContext getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public String get(String name) {
        return PagePaths.of(name.substring(5)).getPath();
    }


}
