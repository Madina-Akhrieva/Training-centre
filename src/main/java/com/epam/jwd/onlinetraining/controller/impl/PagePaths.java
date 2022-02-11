package com.epam.jwd.onlinetraining.controller.impl;

public enum PagePaths {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    LOGIN("/login.jsp"),
    PROFILE("/WEB-INF/jsp/profile.jsp"),
    ACCOUNTS("/WEB-INF/jsp/accounts.jsp"),
    ADD_COURSE("/add_course.jsp"),
    WATCH_TASKS("/WEB-INF/jsp/watch_tasks.jsp"),
    ADD_ANSWER("/add_answer.jsp"),

    EDIT_COURSE("/edit_course.jsp"),
    ADD_TASK("/add_task.jsp"),
    CHECK_TASK("/WEB-INF/jsp/check_task.jsp"),
    COMPLETE_TASK("/WEB-INF/jsp/complete_task.jsp"),
    STUDENTS("/WEB-INF/jsp/students.jsp"),
    TASKS_WITH_FEEDBACK("/WEB-INF/jsp/tasks_with_feedback.jsp"),
    GIVE_FEEDBACK("/WEB-INF/jsp/give_feedback.jsp"),

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
