package com.example.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressSpecificationDTO  extends PaginationDto{


    private String name;
    private String phoneNumber;
    private String district;
}


