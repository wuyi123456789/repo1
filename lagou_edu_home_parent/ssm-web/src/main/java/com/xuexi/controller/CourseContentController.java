package com.xuexi.controller;

import com.xuexi.domain.Course;
import com.xuexi.domain.CourseSection;
import com.xuexi.domain.ResponseResult;
import com.xuexi.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "章节及课时内容查询成功", list);
        return result;
    }

    /*
    回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course= courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "查询课程信息成功", course);
        return result;
    }

    /*
     新增&更新章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody  CourseSection courseSection){

        //判断是否携带了章节ID
        if(courseSection.getId() ==null) {
            //新增
            courseContentService.saveSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增章节成功", null);
            return responseResult;
        }else{
            //更新
            courseContentService.updateSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "更新章节成功", null);
            return responseResult;
        }
    }

    /*
     修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult  updateSectionStatus(int id,int status){

        courseContentService.updateSectionStatus(id,status);

        //数据响应
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改章节状态成功", map);
        return result;
    }

}
