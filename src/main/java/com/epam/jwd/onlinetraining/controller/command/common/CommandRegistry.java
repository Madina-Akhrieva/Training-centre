package com.epam.jwd.onlinetraining.controller.command.common;

import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.LoginCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.LogoutCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.SignupCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAccountsPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAddCoursePageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAfterAddCoursePageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAfterDeletePageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAfterEditPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowEditCourseCommand;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowErrorPageCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.ShowLoginPageCommand;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowMainPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowManageCoursesPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowProfilePageCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.ShowSignupPageCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.ShowTasksCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.AddTaskCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.AfterAddTaskCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.CheckTaskCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.CompleteTaskCommand;
import com.epam.jwd.onlinetraining.dao.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.onlinetraining.dao.model.Role.ADMIN;
import static com.epam.jwd.onlinetraining.dao.model.Role.MENTOR;
import static com.epam.jwd.onlinetraining.dao.model.Role.STUDENT;
import static com.epam.jwd.onlinetraining.dao.model.Role.UNAUTHORIZED;

public enum CommandRegistry {

    MAIN_PAGE(ShowMainPageCommand.INSTANCE, "main_page"),
    ERROR(ShowErrorPageCommand.INSTANCE, "show_error"),
    LOGIN_PAGE(ShowLoginPageCommand.INSTANCE, "show_login", UNAUTHORIZED),
    SIGN_PAGE(ShowSignupPageCommand.INSTANCE, "show_signup"),
    LOGIN(LoginCommand.INSTANCE, "login", UNAUTHORIZED),
    SIGNUP(SignupCommand.INSTANCE, "signup", UNAUTHORIZED),
    LOGOUT(LogoutCommand.INSTANCE, "logout", STUDENT, MENTOR, ADMIN),
    PROFILE_PAGE(ShowProfilePageCommand.INSTANCE, "show_profile"),
    SHOW_ACCOUNTS(ShowAccountsPageCommand.INSTANCE, "show_accounts", ADMIN),
    MANAGE_COURSES(ShowManageCoursesPageCommand.INSTANCE, "manage_courses", ADMIN),
    SHOW_ADD_COURSE_PAGE(ShowAddCoursePageCommand.INSTANCE, "show_add_course",  STUDENT, ADMIN),
    ADD_COURSES(AddCourseCommand.INSTANCE, "add_course", ADMIN),
    WATCH_TASKS(ShowTasksCommand.INSTANCE, "watch_tasks",  STUDENT, MENTOR, ADMIN),
    DEFAULT(ShowMainPageCommand.INSTANCE, ""),


    AFTER_ADD_COURSE(ShowAfterAddCoursePageCommand.INSTANCE, "after_add_course"),
    COMPLETE_TASK(CompleteTaskCommand.INSTANCE, "complete_task"),
    AFTER_ADD_TASK(AfterAddTaskCommand.INSTANCE, "after_add_task"),
    CHECK_TASK(CheckTaskCommand.INSTANCE, "check_task"),
    ADD_TASK(AddTaskCommand.INSTANCE, "add_task"),
    AFTER_EDIT_COURSE(ShowAfterEditPageCommand.INSTANCE, "after_edit_course", ADMIN),
    AFTER_DELETE_COURSE(ShowAfterDeletePageCommand.INSTANCE, "after_delete_course", ADMIN),
    EDIT_COURSE(ShowEditCourseCommand.INSTANCE, "edit_course", ADMIN);

    private final Command command;
    private final String path;
    private final List<Role> allowedRoles;

    CommandRegistry(Command command, String path, Role... roles) {
        this.command = command;
        this.path = path;
        this.allowedRoles = roles != null && roles.length > 0 ? Arrays.asList(roles) : Role.valuesAsList();
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getAllowedRoles() {
        return allowedRoles;
    }

    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
