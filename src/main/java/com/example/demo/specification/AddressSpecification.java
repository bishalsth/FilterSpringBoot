package com.example.demo.specification;


import com.example.demo.dtos.AddressSpecificationDTO;
import com.example.demo.entity.Address;
import com.example.demo.repo.AddressRepo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;



public class AddressSpecification implements Specification<Address> {

private final AddressRepo addressRepo;
private final AddressSpecificationDTO addressSpecificationDTO;

    public AddressSpecification(AddressRepo addressRepo, AddressSpecificationDTO addressSpecificationDTO) {
        this.addressRepo = addressRepo;
        this.addressSpecificationDTO = addressSpecificationDTO;
    }


    @Override
    public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Path<Object> name = root.get("name");
        Path<Object> phoneNumber = root.get("phoneNumber");
        Path<Object> district = root.get("district");

        final List<Predicate>predicateList = new ArrayList<>();

    if(addressSpecificationDTO.getName()!=null){
        predicateList.add(criteriaBuilder.equal(name,addressSpecificationDTO.getName()));

    }

        if(addressSpecificationDTO.getPhoneNumber()!=null){
            predicateList.add(criteriaBuilder.equal(phoneNumber,addressSpecificationDTO.getPhoneNumber()));
        }

        if(addressSpecificationDTO.getDistrict()!=null){
            predicateList.add(criteriaBuilder.equal(district,addressSpecificationDTO.getDistrict()));
        }


        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
