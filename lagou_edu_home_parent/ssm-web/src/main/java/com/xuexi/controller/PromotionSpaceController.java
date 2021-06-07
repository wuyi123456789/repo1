package com.xuexi.controller;

import com.xuexi.domain.PromotionSpace;
import com.xuexi.domain.ResponseResult;
import com.xuexi.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*
     查询广告位列表
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult  findAllPromotionSpace(){
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        ResponseResult result = new ResponseResult(true, 200, "查询所有广告位成功", list);
        return result;
    }

    /*
     添加广告位
    */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult  saveOrUpdatePromotionSpace(@RequestBody  PromotionSpace promotionSpace){

        if(promotionSpace.getId() == null) {
            //新增
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "新增广告位成功", null);
        }else{
            //修改
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "更新广告位名称成功", null);

        }
    }

    /*
     根据id查询广告位
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        return new ResponseResult(true,200,"查询具体广告位成功",promotionSpace);
    }

    /*
     * 课程图片上传
     * */
    @RequestMapping("/PromotionAdUpload>")
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

}
