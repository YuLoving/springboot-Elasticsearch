package com.example.ccq.springelasticsearch.config.Excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.ccq.springelasticsearch.pojo.Excelpojo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * 编写阿里的easyexcel的导入导出工具类
 */
@Slf4j
public class ExcelUtils {
    /**
     * @param is   导入文件输入流
     * @param clazz Excel实体映射类
     * @return
     */

    public  static  boolean readexcel(InputStream is, Class clazz){
        BufferedInputStream bis=null;
        try {
            bis=new BufferedInputStream(is);
            //解析每行结果在listener中处理
             AnalysisEventListener listener = new ExcelListener();
            ExcelReader reader = EasyExcelFactory.getReader(bis, listener);
            reader.read(new Sheet(1,1,clazz));
            log.info("+++++++++导入成功+++++++");
        }catch (Exception e){
            log.error("+++++++++导入失败+++++++");
            e.printStackTrace();
            return false;
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


    /**
     * @param os 文件输出流
     * @param clazz Excel实体映射类
     * @param data 导出数据
     * @return
     */
    public static boolean writeExcel(OutputStream os, Class clazz, List< ? extends BaseRowModel> data){
        BufferedOutputStream  bos=null;
        try {
            bos= new BufferedOutputStream(os);
            ExcelWriter writer = new ExcelWriter(bos, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet = new Sheet(1, 0, clazz);
            writer.write(data,sheet);
            writer.finish();
            log.info("========导出Excel成功========");
        }catch (Exception e){
            log.error("+++++++++导出excel失败+++++++");
            e.printStackTrace();
            return false;
        }finally {
                if(bos!=null){
                    try {
                        bos.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
        }
        return true;
    }


    public static void main(String[] args) {
        //1.读Excel
        FileInputStream fis=null;
        try {
            fis=new FileInputStream("D:\\导入数据.xlsx");
            boolean flag = ExcelUtils.readexcel(fis, Excelpojo.class);
            System.out.println("是否导入成功："+flag);
        }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }

      //2.导出
      /*  FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:\\export.xlsx");
            //FileOutputStream fos, Class clazz, List<? extends BaseRowModel> data
            List<Excelpojo> list = new ArrayList<>();
            for (int i = 0; i < 5; i++){
                Excelpojo excelEntity = new Excelpojo();
                excelEntity.setName("我是名字"+i);

                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String s = format.format(new Date());
                excelEntity.setBirth(s);
                list.add(excelEntity);
            }
            Boolean flag = ExcelUtils.writeExcel(fos,Excelpojo.class,list);
            System.out.println("导出是否成功："+flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



*/



    }

}
