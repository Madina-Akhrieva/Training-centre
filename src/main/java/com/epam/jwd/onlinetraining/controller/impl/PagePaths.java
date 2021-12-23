package com.epam.jwd.onlinetraining.controller.impl;

public enum PagePaths {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp"),
    PROFILE("/WEB-INF/jsp/profile.jsp"),
    ACCOUNTS("/WEB-INF/jsp/accounts.jsp"),
    ADD_COURSE("/WEB-INF/jsp/add_course.jsp"),
    WATCH_TASKS("/WEB-INF/jsp/watch_tasks.jsp"),

    EDIT_COURSE("/WEB-INF/jsp/edit_course.jsp"),
    ADD_TASK("/WEB-INF/jsp/add_task.jsp"),
    CHECK_TASK("/WEB-INF/jsp/check_task.jsp"),
    COMPLETE_TASK("/WEB-INF/jsp/complete_task.jsp"),

    ERROR("/WEB-INF/jsp/error.jsp"),
    MANAGE_COURSES("/WEB-INF/jsp/manage_courses.jsp"),
    MANAGE_TASKS("/WEB-INF/jsp/manage_tasks.jsp");

    private final String path;


    PagePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PagePaths of(String name) {
        for (PagePaths page : values()) {
            if (page.name().equalsIgnoreCase(name)) {
                return page;
            }
        }
        return MAIN;
    }
}
