package ru.itis.jpa.repositories;

import ru.itis.jpa.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CoursesRepositoryJpaImpl implements CoursesRepository {

    // стандарт JPA, через него можно работать с сущностями, это предоставит взаимодействие с БД
    private EntityManager entityManager;

    public CoursesRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Course course) {
        // сохраняем курс
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(course);
        entityTransaction.commit();
    }

    // найти все курсы по их количеству студентов
    @Override
    public List<Course> findAllByStudentsCount(int count) {
        return entityManager.createQuery("select course from Course course where course.students.size = ?", Course.class)
                .setParameter(0, count)
                .getResultList();
    }
}
