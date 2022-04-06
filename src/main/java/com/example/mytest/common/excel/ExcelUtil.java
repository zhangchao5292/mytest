package com.example.mytest.common.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangchao
 * @Date 2022/2/14
 */
@Slf4j
public class ExcelUtil {
    public static void main(String[] args) {
        try {
            // 实现excel读的操作
            File file = new File("C:\\Users\\zhang\\Desktop\\水军头像昵称\\user.xlsx");
            SensitiveWordListener listener = new SensitiveWordListener();
            EasyExcel.read(new FileInputStream(file), User.class, listener).sheet().doRead();

            List<User> userList = listener.userList;
            //实现excel写的操作
            //1 设置写入文件夹地址和excel文件名称
            String filename = "C:\\Users\\zhang\\Desktop\\水军头像昵称\\test.xlsx";
            // 2 调用easyexcel里面的方法实现写操作
            // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
            EasyExcel.write(filename, User.class).sheet("列表").doWrite(userList);
        } catch (Exception e) {
            log.error("failToUploadSensitive. error:{}", e.getMessage(), e);
        }
    }


    static class SensitiveWordListener extends AnalysisEventListener<User> {
        List<User> userList = new ArrayList<>();

        //读取表头内容
        @Override
        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
            System.out.println("表头：" + headMap);
        }

        //一行一行读取excel内容
        @Override
        public void invoke(User user, AnalysisContext analysisContext) {
            userList.add(user);
        }

        //读取完成之后
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            log.info("=============read end=============: " + userList.size());
        }
    }

    @Data
    public static class User {

        @ExcelProperty(index = 0, value = "mid")
        private String mid;

        @ExcelProperty(index = 1, value = "nickname")
        private String nickname;

        @ExcelProperty(index = 2, value = "headimgurl")
        private String headimgurl;
    }
}

