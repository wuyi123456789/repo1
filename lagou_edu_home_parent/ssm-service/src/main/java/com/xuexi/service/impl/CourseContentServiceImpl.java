package com.xuexi.service.impl;

import com.xuexi.dao.CourseContentMapper;
import com.xuexi.domain.Course;
import com.xuexi.domain.CourseSection;
import com.xuexi.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer couresId) {
        return courseContentMapper.findSectionAndLessonByCourseId(couresId);
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        //1.补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //2.调用courseContentMapper方法
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //1.补全信息
        courseSection.setUpdateTime(new Date());

        //2.调用courseContentMapper方法
        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        //补全信息
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());

        //调用mapper
        courseContentMapper.updateSectionStatus(courseSection);
    }

}
