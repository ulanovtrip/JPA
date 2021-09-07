package ru.itis.jpa.app;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.jpa.models.Course;
import ru.itis.jpa.models.Lesson;
import ru.itis.jpa.models.Student;
import ru.itis.jpa.repositories.CoursesRepository;
import ru.itis.jpa.repositories.CoursesRepositoryJpaImpl;
import ru.itis.jpa.repositories.StudentRepository;
import ru.itis.jpa.repositories.StudentRepositoryJpaImpl;

import javax.persistence.EntityManager;
import java.util.Arrays;


public class MainJpa {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate/hibernate.cfg.xml");

        // на основе hiberate.cfg.xml соберёт фабрику сессий, т.е. соединений
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();

        CoursesRepository coursesRepository = new CoursesRepositoryJpaImpl(entityManager);

        StudentRepository studentRepository = new StudentRepositoryJpaImpl(entityManager);

        // создали курс
        Course java = Course.builder()
                .title("Java")
                .build();

        Course sql = Course.builder()
                .title("Sql")
                .build();

        // сохранили курсы
        coursesRepository.save(java);
        coursesRepository.save(sql);

        // создали студентов
        Student ivan = Student.builder()
                .firstName("Ivan")
                .lastName("Ulanov")
                .courses(Arrays.asList(java, sql))
                .build();

        Student nikola = Student.builder()
                .firstName("Nikola")
                .lastName("Ivanov")
                .courses(Arrays.asList(java, sql))
                .build();

        Student olga = Student.builder()
                .firstName("Olga")
                .lastName("Kozlov")
                .courses(Arrays.asList(java, sql))
                .build();

        // сохранили студентов
        studentRepository.save(ivan);
        studentRepository.save(nikola);
        studentRepository.save(olga);

        System.out.println(coursesRepository.findAllByStudentsCount(3));

    }
}
