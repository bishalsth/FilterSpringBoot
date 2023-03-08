package com.example.demo.controller;


import com.example.demo.config.CustomMessageSource;
import com.example.demo.dtos.AddressDto;
import com.example.demo.dtos.AddressSpecificationDTO;
import com.example.demo.service.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filter")

public class AddressController extends BaseController{
    private final AddressService addressService;
    private final CustomMessageSource customMessageSource;

    public AddressController(AddressService addressService, CustomMessageSource customMessageSource) {
        this.addressService = addressService;
        this.customMessageSource = customMessageSource;
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

}

