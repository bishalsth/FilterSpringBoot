package com.example.demo.repo;

import com.example.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressRepo extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {
}
