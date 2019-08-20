package com.example.ccq.springelasticsearch.controller;

import com.example.ccq.springelasticsearch.config.Excel.ExcelUtils;
import com.example.ccq.springelasticsearch.pojo.Excelpojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/excel")
public class EasyExcelContrllor {

    private static final Logger log = LoggerFactory.getLogger(EasyExcelContrllor.class);


    @GetMapping("/to")
    public String toinput(){
        return "input";
    }

    /**
     * 导入/单个文件上传
     */
    @ResponseBody
    @RequestMapping(value = "/upload")
    public Object upload(@RequestParam("file") MultipartFile file){
        Map<String, Object> map = new HashMap<>();
        if(file.isEmpty()){
        map.put("code",101) ;
        map.put("msg","文件为空");
        return map;
        }
        try {
            //获取文件名
            String filename = file.getOriginalFilename();
            log.info("上传的文件名为：" + filename);
            // 获取文件的后缀名
            String suffixName  = filename.substring(filename.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            //创建一个临时文件，用于暂时存放
            File tmpFile  = File.createTempFile("tmp", null);
            //将MultipartFile 转换为 File 临时文件
            file.transferTo(tmpFile);
            //将临时文件转为输入流
            FileInputStream inputStream = new FileInputStream(tmpFile);
            //开始Excel导入
            ExcelUtils.readexcel(inputStream, Excelpojo.class);
            map.put("code",200) ;
            map.put("msg","上传成功");
            //上传完成 删除临时文件
            tmpFile.delete();
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("code",500) ;
        map.put("msg","上传失败");
        return map;
    }


    /**
     * 下载模板，用于填写导入数据
     * @param request
     * @param response
     */
    @RequestMapping("/downloadExcel")
    public void cooperation(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            String fileName = "导入模板";
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8")+".xlsx");
            ExcelUtils.writeExcel(out,Excelpojo.class,null);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出数据文件
     * @param request
     * @param response
     */
    @RequestMapping("/downloadExcelData")
    public void cooperationData(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            String fileName = "导出明细";
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8")+".xlsx");

            //List<ExcelModel> data = 此处为 数据接口 返回一个list ;
            List<Excelpojo> data = new ArrayList<>();
            for (int i = 0; i < 5; i++){
                Excelpojo excelEntity = new Excelpojo();
                excelEntity.setName("我是名字"+i);

                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String s = format.format(new Date());
                excelEntity.setBirth(s);
                data.add(excelEntity);
            }
            //把数据明细放在list data中
            System.out.println("把数据明细放在list data中:请完善查询数据接口调用，并把查询结果写入list data中");
            Boolean flag = ExcelUtils.writeExcel(out, Excelpojo.class, data);
            System.out.println("导出是否成功："+flag);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }












}
