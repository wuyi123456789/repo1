package com.xuexi.controller;

import com.xuexi.domain.Course;
import com.xuexi.domain.CourseVo;
import com.xuexi.domain.ResponseResult;
import com.xuexi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVo);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",list);
        return responseResult;
    }

    /*
    * 课程图片上传
    * */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断接收到的上传文件是否为空
        if(file.isEmpty()){
            throw  new RuntimeException();
        }
        //2.获取项目部署路径
        //D:\ap
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        String newFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        String uploadPath = substring+"upload\\";
        File file1 = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if(!file1.getParentFile().exists()){
             file1.getParentFile().mkdirs();
            System.out.println("创建目录："+file);
        }

        //图片就真正上传
        file.transferTo(file1);

        //6.将文件名和文件路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://loaclhost:8080/upload/"+newFileName);

        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);
        return result;
    }

    /*
     新增课程信息及讲师信息
     新增课程信息和修改课程信息要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        if(courseVo.getId() == null){
            courseService.saveCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        }else {
            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }

    }

    /*
     根据id查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public  ResponseResult findCourseById(Integer id){
        CourseVo courseVo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "根据id查询课程信息成功", courseVo);
        return result;
    }

    /*
     课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        //调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id,status);

        //响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);
        return responseResult;
    }
}
