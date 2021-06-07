package com.xuexi.service;

import com.xuexi.domain.Course;
import com.xuexi.domain.CourseVo;
import com.xuexi.domain.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
    多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVo courseVo);

    /*
     * 添加课程及讲师信息
     * */
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /*
     根据id查询课程信息
     */
    public CourseVo findCourseById(Integer id);

    /*
     更新课程或讲师信息
     */
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    /*
     课程状态变更
     */
    public  void updateCourseStatus(int courseId, int status );
}
