package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Игорь", "Волов", (byte) 28);
        userService.saveUser("Иван", "Петров", (byte) 35);
        userService.saveUser("Андрей", "Сидоров", (byte) 19);
        userService.saveUser("Виктор", "Третьяков", (byte) 23);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.removeUserById(2);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
