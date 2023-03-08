package com.example.demo.global.helper;

import com.example.demo.global.generics.BodyData;
import com.example.demo.global.generics.GenericExcelPojo;
import com.github.opendevl.JFlat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExportToExcelServiceImpl implements ExportToExcelService{
    @Override
    public Workbook jsonDataToCSV(List<String> headings, String jsonData) throws FileNotFoundException, UnsupportedEncodingException {
        /** Initilize Workbook */
        Workbook workbook = new XSSFWorkbook();
        GenericExcelPojo genericExcelPojo = new GenericExcelPojo();
        jsonData = jsonData.replace("\\n", "");

        /** Adding Sheet to the workbook */
        Sheet sheet = workbook.createSheet("OAG Entity List");
        sheet.setDefaultColumnWidth(20);

        //Convert JSON data to 2d object
        JFlat flatMe = new JFlat(jsonData);
        List<Object[]> json2csv = flatMe.json2Sheet().getJsonAsSheet();

        //Using 2d data to separate head and body data
        int flag = 0;
        List<String> heading = new ArrayList<>();
        List<BodyData> bodyData1List = new ArrayList<>();
        for (Object[] obj : json2csv) {
            if (flag < 1) {
                for (int i = 0; i < obj.length; i++) {
                    heading.add(obj[i].toString());
                    flag++;
                }
                genericExcelPojo.setHeading(heading);
            } else {
                BodyData bodyData1 = new BodyData();
                List<String> bodyData = new ArrayList<>();
                for (int i = 0; i < obj.length; i++) {
                    String data = obj[i] == null ? "-" : obj[i].toString();
                    data = data.replace("\"", "");
                    bodyData.add(data);
                }
                bodyData1.setData(bodyData);
                bodyData1List.add(bodyData1);
            }
        }
        if (flag != headings.size()) {
            System.out.println("head is not equal to data column");
            throw new RuntimeException("head is not equal to data column");
        }
        genericExcelPojo.setBodyData(bodyData1List);

        loadDataToGenericSheet(sheet, headings, genericExcelPojo, workbook);

        return workbook;
    }

    public Sheet loadDataToGenericSheet(Sheet sheet, List<String> headings, GenericExcelPojo data,
                                        Workbook workbook) {
        int rowNumber = 0;
        Row headerRow = sheet.createRow(rowNumber++);
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Courier New");
        font.setBold(true);
        font.setUnderline(Font.U_SINGLE);
        font.setColor(HSSFColor.HSSFColorPredefined.DARK_RED.getIndex());
        headerCellStyle.setFont(font);
        //set background color
        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);

        CellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setWrapText(true);


        /**
         * Adding headings
         */
        Cell cell = headerRow.createCell(0);
        cell.setCellValue("S.N.");
        cell.setCellStyle(headerCellStyle);
        for (int i = 0; i < headings.size(); i++) {
            cell = headerRow.createCell(i + 1);
            cell.setCellValue(headings.get(i));
            cell.setCellStyle(headerCellStyle);
        }
        /**
         * Add data under headings
         */if (data.getBodyData() != null) {
            for (BodyData bodyData : data.getBodyData()) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(rowNumber - 1);
                row.setRowStyle(bodyCellStyle);
                for (int i = 0; i < bodyData.getData().size(); i++) {
                    row.createCell(i + 1).setCellValue(bodyData.getData().get(i));
                    row.setRowStyle(bodyCellStyle);
                }
            }
        }
        return sheet;
    }

}
