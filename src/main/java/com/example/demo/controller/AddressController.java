package com.example.demo.controller;


import com.example.demo.config.CustomMessageSource;
import com.example.demo.dtos.AddressDto;
import com.example.demo.dtos.AddressSpecificationDTO;
import com.example.demo.service.AddressService;
import com.example.demo.service.report.MasterReportService;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/filter")

public class AddressController extends BaseController{
    private final AddressService addressService;
    private final CustomMessageSource customMessageSource;

    private final MasterReportService masterReportService;

    public AddressController(AddressService addressService, CustomMessageSource customMessageSource, MasterReportService masterReportService) {
        this.addressService = addressService;
        this.customMessageSource = customMessageSource;
        this.masterReportService = masterReportService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AddressDto dto){
        AddressDto dto1 = addressService.create(dto);
        return ResponseEntity.ok(successResponse(customMessageSource.get("crud.create",customMessageSource.get("address")),dto1));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<AddressDto> all = addressService.getAll();
        return ResponseEntity.ok(all);

    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        AddressDto byId = addressService.getById(id);
        return ResponseEntity.ok(byId);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        addressService.deleteById(id);
        return ResponseEntity.ok("DEleted");

    }

@PostMapping("/filter")
public ResponseEntity<?> specification(@RequestBody AddressSpecificationDTO addressSpecificationDTO){
    Page<AddressDto> addressDtoPage = addressService.addressSpecification(addressSpecificationDTO);
return ResponseEntity.ok(addressDtoPage);

}

    @GetMapping("/export-to-excel")
    public ResponseEntity<?> exportToExcel(HttpServletResponse response) {
        try {
            Workbook workbook = masterReportService.exportToExcel();
            String excelFileName = UUID.randomUUID().toString() + "Address.xlsx";
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            workbook.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            byte[] toBase64 = outArray;
            byte[] endocedBase64 = Base64.encodeBase64(toBase64);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setContentLength(endocedBase64.length);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "excel" + " ; excelFileName=" + excelFileName + ";");
            OutputStream outStream = response.getOutputStream();
            outStream.write(endocedBase64);
            outStream.flush();
            workbook.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok("success");
    }



}

