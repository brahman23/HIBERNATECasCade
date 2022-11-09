package peaksoft.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;

import java.util.Properties;

public class DBUtil {
    public static SessionFactory creatSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/HIBERNATE");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "12345");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put(Environment.SHOW_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Task.class);
        configuration.addAnnotatedClass(Lesson.class);
        return configuration.buildSessionFactory();
    }
}
