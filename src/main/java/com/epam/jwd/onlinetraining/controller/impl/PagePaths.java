package com.epam.jwd.onlinetraining.controller.impl;

public enum PagePaths {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    LOGIN("/WEB-INF/jsp/login_signup.jsp"),
    PROFILE("/WEB-INF/jsp/profile.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp");

    private final String path;


    PagePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PagePaths of (String name){
        for (PagePaths page: values()) {
            if(page.name().equalsIgnoreCase(name)){
                return page;
            }
        }
        return MAIN;
    }
}
