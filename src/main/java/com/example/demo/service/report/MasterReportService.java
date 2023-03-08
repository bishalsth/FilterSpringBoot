package com.example.demo.service.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface MasterReportService {

    public Workbook exportToExcel() throws JsonProcessingException, FileNotFoundException, UnsupportedEncodingException;
}
