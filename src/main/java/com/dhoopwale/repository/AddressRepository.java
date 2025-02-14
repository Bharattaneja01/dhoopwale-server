package com.dhoopwale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhoopwale.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
