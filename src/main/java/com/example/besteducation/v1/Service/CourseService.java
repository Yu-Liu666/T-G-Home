package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.domain.Course;

public interface CourseService {
    Course findById(Long id);
    Course create(Course course);
    Course findByIdentifier(String Identifier);
}
