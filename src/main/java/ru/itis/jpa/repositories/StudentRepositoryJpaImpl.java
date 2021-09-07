package ru.itis.jpa.repositories;

import ru.itis.jpa.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class StudentRepositoryJpaImpl implements StudentRepository {

    private EntityManager entityManager;

    public StudentRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
    }
}
