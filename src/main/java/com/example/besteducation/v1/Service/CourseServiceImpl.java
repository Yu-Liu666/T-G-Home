package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.dao.CourseRepository;
import com.example.besteducation.v1.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course create(Course course) {
        Course c=courseRepository.save(course);
        return c;
    }

    @Override
    public Course findByIdentifier(String identifier) {
        return courseRepository.findByIdentifier(identifier);
    }

}
