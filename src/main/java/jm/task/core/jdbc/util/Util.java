package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/kata";
        return DriverManager.getConnection(url, "root", "1972");
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/kata");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1972");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
