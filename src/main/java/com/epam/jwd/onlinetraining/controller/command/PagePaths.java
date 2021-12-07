package com.epam.jwd.onlinetraining.controller.command;

public enum PagePaths {
    MAIN("WEB_INF/jsp/main.jsp"),
    LOGIN("WEB_INF/jsp/login_signup.jsp"),
    PROFILE("WEB_INF/jsp/profile.jsp");

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
