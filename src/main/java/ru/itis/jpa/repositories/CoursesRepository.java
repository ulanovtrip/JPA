package ru.itis.jpa.repositories;

import ru.itis.jpa.models.Course;

import java.util.List;

public interface CoursesRepository {

    void save(Course course);

    List<Course> findAllByStudentsCount(int count);
}
