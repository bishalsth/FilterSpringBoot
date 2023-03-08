package com.example.demo.service.report;

import com.example.demo.entity.Address;
import com.example.demo.global.helper.ExportToExcelService;
import com.example.demo.repo.AddressRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MasterReportServiceImpl  implements MasterReportService{


private final ExportToExcelService exportToExcelService;

    private final AddressRepo addressRepo;

    public MasterReportServiceImpl(ExportToExcelService exportToExcelService, AddressRepo addressRepo) {
        this.exportToExcelService = exportToExcelService;
        this.addressRepo = addressRepo;
    }

    @Override
    public Workbook exportToExcel() throws JsonProcessingException, FileNotFoundException, UnsupportedEncodingException {
        List<Address> all = addressRepo.findAll();

        ObjectMapper objectMapper =new ObjectMapper();
        String json = objectMapper.writeValueAsString(all);

        List<String> heading = new ArrayList<>();
        heading.add("id");
        heading.add("name");
        heading.add("address");
        heading.add("district");
        heading.add("phone_number");
        Workbook sheets = exportToExcelService.jsonDataToCSV(heading, json);
        return sheets;

    }
}
