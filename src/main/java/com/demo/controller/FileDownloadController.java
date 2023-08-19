package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.serivce.FeedBackRecordAndUserInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.prefs.BackingStoreException;

/**
 * @ClassName: FileDownloadController
 * @author: order
 * @date: 2023-08-10 23:11
 * @version: 1.0
 * @Description:
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin
public class FileDownloadController {

    @Autowired
    FeedBackRecordAndUserInfoService feedBackRecordAndUserInfoService;

    @GetMapping("/download/excel")
    public void export(HttpServletResponse response) throws Exception {
        List<Map<String, Object>> excelModels = feedBackRecordAndUserInfoService.selectFeedBackRecordAndUserInfoExcel();
        List<String> list = new ArrayList<>();
        list.add("feedback_record_id");
        list.add("chat_record_id");
        list.add("first_name");
        list.add("last_name");
        list.add("email");
        list.add("agent_name");
        list.add("created_at");
        this.excelPort(String.valueOf(UUID.randomUUID()), list, excelModels, response);
    }

    @GetMapping("/download/excel/question/{id}")
    public void exportQuestion(@PathVariable("id") Integer id, HttpServletResponse response) {
        List<Map<String, Object>> maps = feedBackRecordAndUserInfoService.selectFeedBackRecordByRecordId(String.valueOf(id));
        this.downloadQuestion(response, maps);
    }

    @GetMapping("/download/excel/transcript/{id}")
    public void exportTranscript(@PathVariable("id") Integer id, HttpServletResponse response) {
        Map<String, Object> maps = feedBackRecordAndUserInfoService.selectChatRecordByRecordId(String.valueOf(id));
        this.downloadTranscript(response, maps);
    }

    public void downloadTranscript(HttpServletResponse response, Map<String, Object> map) {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=" + UUID.randomUUID() + ".txt");

        try {

            OutputStream outputStream = response.getOutputStream();
            List list = JSONObject.parseObject((String) map.get("transcript"), List.class);
            for (Object value : list) {
                JSONObject o = (JSONObject) value;
                String next = o.keySet().iterator().next();
                String line2 = "sender： " + next + "|     " + "content： " + o.get(next) + System.getProperty("line.separator");
                byte[] buffer = line2.getBytes();
                outputStream.write(buffer);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadQuestion(HttpServletResponse response, List<Map<String, Object>> list) {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=" + UUID.randomUUID() + ".txt");

        try {

            OutputStream outputStream = response.getOutputStream();

            for (Map<String, Object> map : list) {
//                String next = map.keySet().iterator().next();
//                String line2 = "sender： " + next + "|     " + "content： " +map.get(next)+System.getProperty("line.separator");

                String line2 = "question： " + map.get("question") + System.getProperty("line.separator") + "answer： " + map.get("answer") + System.getProperty("line.separator");
//                String line = data + System.getProperty("line.separator");  // 添加换行符
                byte[] buffer = line2.getBytes();
                outputStream.write(buffer);
            }

            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载TXT
     *
     * @param path
     * @param response
     * @return
     */
    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    /**
     * 下载excel
     *
     * @param sheetName 工作表名，文件名，头部信息
     * @param listName  列名
     * @param list      需要写入的数据
     * @param response  返回
     */
    public void excelPort(String sheetName, List<String> listName, List<Map<String, Object>> list, HttpServletResponse response) {
        OutputStream os = null;
        try {
            if (list.size() == 0) {
                throw new BackingStoreException("数据为空");
            }
            // 声明一个工作簿
            XSSFWorkbook wb = new XSSFWorkbook();
            // 创建sheet页
            XSSFSheet sheet = wb.createSheet(sheetName);
            sheet.setDefaultColumnWidth(19);

            // 表头
//            XSSFRow rowReportTitle = sheet.createRow(0);
//            Cell cell1 = rowReportTitle.createCell(0); // 0列
            // 设置值
//            cell1.setCellValue(sheetName);

            // 合并表头
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, listName.size() - 1));
//            rowReportTitle.setHeight((short) 600); // 行高

            //设置表头字体
//            Font headFont = wb.createFont();
//            headFont.setFontName("宋体");
//            headFont.setFontHeightInPoints((short) 18);// 字体大小
//
//            CellStyle headStyle = wb.createCellStyle();
//            headStyle.setFont(headFont);
//            headStyle.setAlignment(HorizontalAlignment.CENTER);// 左右居中
//            headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中
            // 头部样式添加
//            cell1.setCellStyle(headStyle);

            // 全局加线样式
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
            cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
            cellStyle.setBorderTop(BorderStyle.THIN);//上边框
            cellStyle.setBorderRight(BorderStyle.THIN);//右边框
            cellStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 上下居中

            // 记录标题信息
            TreeMap<String, Integer> headMap = new TreeMap<>();

            // 标题写入
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < listName.size(); i++) {
                row.setHeight((short) 450);
                XSSFCell cell = row.createCell(i);
                String headName = listName.get(i);
                cell.setCellValue(headName); // 写入列名
                headMap.put(headName, i);
                cell.setCellStyle(cellStyle);
            }

            // 写入内容数据
            int ind = 1;
            for (Map<String, Object> map : list) {
                XSSFRow r = sheet.createRow(ind++);
                for (Map.Entry<String, Integer> m : headMap.entrySet()) {
                    String name = m.getKey(); // 列名
                    XSSFCell cell2 = r.createCell(m.getValue());

                    try {
                        String value = (String) map.get(name); // value 不一定存在
                        if (value != null) {
                            cell2.setCellValue(value);
                        }
                    } catch (ClassCastException c) {
                        Integer value = (Integer) map.get(name); // value 不一定存在
                        cell2.setCellValue(value);
                    }
                    cell2.setCellStyle(cellStyle);
                }
            }


            // 输出Excel文件
            os = response.getOutputStream();
            response.setContentType("application/x-download");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(sheetName.getBytes(), "ISO8859-1") + ".xls");
            wb.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
