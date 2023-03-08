package com.example.demo.dtos;

import com.example.demo.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDto {


    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String district;


    public Address toEntity (AddressDto dto){
        Address address = Address.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .district(dto.getDistrict())
                .build();
        return address;

    }

    public AddressDto toDto(Address entity){
        AddressDto dto = AddressDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .address(entity.getAddress())
                .district(entity.getDistrict())
                .build();
        return dto;
    }
}


