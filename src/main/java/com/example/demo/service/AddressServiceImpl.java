package com.example.demo.service;

import com.example.demo.dtos.AddressDto;
import com.example.demo.dtos.AddressSpecificationDTO;
import com.example.demo.entity.Address;
import com.example.demo.repo.AddressRepo;
import com.example.demo.specification.AddressSpecification;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AddressServiceImpl  implements AddressService{

    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }


    @Override
    public AddressDto create(AddressDto dto) {
        Address addressToPersist = new AddressDto().toEntity(dto);
        addressToPersist= addressRepo.save(addressToPersist);
        return new AddressDto().toDto(addressToPersist);
    }

    @Override
    public AddressDto getById(Long id) {
        return new AddressDto().toDto(addressRepo.findById(id).orElseThrow(()->
                new RuntimeException("Address iD NOT FOUND")));
    }

    @Override
    public List<AddressDto> getAll() {
        return addressRepo.findAll().parallelStream().map(x->new AddressDto().toDto(x)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        addressRepo.findById(id).orElseThrow(()-> new RuntimeException("ID not dound"));
        addressRepo.deleteById(id);
    }

    @Override
    public Page<AddressDto> addressSpecification(AddressSpecificationDTO addresSpecificationDto) {
        Pageable pageable = PageRequest.of(addresSpecificationDto.getPage(),
                addresSpecificationDto.getLimit(),
                Sort.by("id").descending());
          Page<Address> addresses  =addressRepo.findAll(new AddressSpecification(addressRepo,addresSpecificationDto),pageable);
        List<Address> addressList = addresses.getContent();

        PageImpl<AddressDto> addressDtos = new PageImpl<>(addressList.stream().map(x ->
                new AddressDto().toDto(x)).collect(Collectors.toList()), addresses.getPageable(), addresses.getTotalElements()
        );
        return addressDtos;
    }
}
