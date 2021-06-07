package com.xuexi.service.impl;

import com.xuexi.dao.CourseMapper;
import com.xuexi.domain.Course;
import com.xuexi.domain.CourseVo;
import com.xuexi.domain.Teacher;
import com.xuexi.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        return courseMapper.findCourseByCondition(courseVo);
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保存课程
        courseMapper.saveCourse(course);

        //获取新插入数据的id值
        int id = course.getId();

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        //封装课时信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);

        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);

        //更新课程信息
        courseMapper.updateCourse(course);

        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全讲师信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        //更新讲师信息
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int courseId, int status) {
        //1.封装数据
        Course course  = new Course();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //2.调用mapper
        courseMapper.updateCourseStatus(course);
    }
}
