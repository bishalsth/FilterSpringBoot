package com.example.demo.service;

import com.example.demo.dtos.AddressDto;
import com.example.demo.dtos.AddressSpecificationDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressService {
    AddressDto create(AddressDto dto);

    AddressDto getById(Long id);

    List<AddressDto> getAll();

    void deleteById(Long id);

       Page<AddressDto> addressSpecification(AddressSpecificationDTO addresSpecificationDto);

}
