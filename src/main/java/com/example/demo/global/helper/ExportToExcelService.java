package com.example.demo.global.helper;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ExportToExcelService {

    Workbook jsonDataToCSV(List<String> headings, String jsonData) throws FileNotFoundException, UnsupportedEncodingException;
}
