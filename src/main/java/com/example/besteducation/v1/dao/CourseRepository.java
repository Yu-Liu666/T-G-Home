package com.example.besteducation.v1.dao;

import com.example.besteducation.v1.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findById(Long id);
    Course findByIdentifier(String identifier);
}
