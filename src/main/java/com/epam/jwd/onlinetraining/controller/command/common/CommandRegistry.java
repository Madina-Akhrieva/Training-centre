package com.epam.jwd.onlinetraining.controller.command.common;

import com.epam.jwd.onlinetraining.controller.command.completetaskcommand.AddTaskAnswerCommand;
import com.epam.jwd.onlinetraining.controller.command.completetaskcommand.SendAnswerCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseToUserCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.DeleteCourseCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.EditCourseCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.SubmitEditCourseCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.LoginCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.LogoutCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.SignupCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAccountsPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowAddCoursePageCommand;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowErrorPageCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.ShowLoginPageCommand;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowMainPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowManageCoursesPageCommand;
import com.epam.jwd.onlinetraining.controller.command.coursecomamnd.ShowProfilePageCommand;
import com.epam.jwd.onlinetraining.controller.command.logincommand.ShowSignupPageCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.ShowAddTaskPageCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.ShowManageTasksPageCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.ShowTasksCommand;
import com.epam.jwd.onlinetraining.controller.command.taskscommand.AddTaskCommand;
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
    MANAGE_COURSES(ShowManageCoursesPageCommand.INSTANCE, "manage_courses", ADMIN, MENTOR),
    MANAGE_TASKS(ShowManageTasksPageCommand.INSTANCE, "manage_tasks", ADMIN, MENTOR),
    SHOW_ADD_COURSE_PAGE(ShowAddCoursePageCommand.INSTANCE, "show_add_course", MENTOR, ADMIN),
    SHOW_ADD_TASK_PAGE(ShowAddTaskPageCommand.INSTANCE, "show_add_task", MENTOR, ADMIN),
    ADD_COURSE(AddCourseCommand.INSTANCE, "add_course", MENTOR, ADMIN),
    WATCH_TASKS(ShowTasksCommand.INSTANCE, "watch_tasks", STUDENT, MENTOR, ADMIN, UNAUTHORIZED),
    EDIT_COURSE(EditCourseCommand.INSTANCE, "edit_course", MENTOR, ADMIN),
    DELETE_COURSE(DeleteCourseCommand.INSTANCE, "delete_course", MENTOR, ADMIN),
    ADD_TASK(AddTaskCommand.INSTANCE, "add_task", MENTOR, ADMIN),
    SUBMIT_EDIT_COURSE(SubmitEditCourseCommand.INSTANCE, "submit_edit_course", MENTOR, ADMIN),
    ADD_COURSE_TO_USER(AddCourseToUserCommand.INSTANCE, "add_course_to_user", STUDENT),
    SEND_ANSWER(SendAnswerCommand.INSTANCE, "send_answer", STUDENT),
    SHOW_ADD_ANSWER_PAGE(AddTaskAnswerCommand.INSTANCE, "show_add_answer_page", STUDENT),
    DEFAULT(ShowMainPageCommand.INSTANCE, ""),


    COMPLETE_TASK(CompleteTaskCommand.INSTANCE, "complete_task", STUDENT),
    CHECK_TASK(CheckTaskCommand.INSTANCE, "check_task", MENTOR);

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
