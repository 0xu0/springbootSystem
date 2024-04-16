package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class form_layoutsController {
    @RequestMapping("form_layouts.html")
    public String form(){

        return "/form/form_layouts";
    }
    @PostMapping("/upload")
    public String upload(String email, String username,
                         MultipartFile headerImg,
                         MultipartFile[] photos){
        //System.out.println(email);
        if(!headerImg.isEmpty()) {//如果图片不为空
            String name = headerImg.getOriginalFilename();
            try {
                headerImg.transferTo(new File("E:\\java\\springboot\\springbootSystem\\src\\main\\resources\\img\\" + name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        if(photos!=null){
            for(MultipartFile photo:photos){
                String originalFilename = photo.getOriginalFilename();
                try {
                    photo.transferTo(new File("E:\\java\\springboot\\springbootSystem\\src\\main\\resources\\img\\" + originalFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "/form/form_layouts";
    }

}
