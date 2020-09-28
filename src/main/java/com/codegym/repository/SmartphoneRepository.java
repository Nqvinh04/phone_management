package com.codegym.repository;

import com.codegym.model.SmartPhone;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SmartphoneRepository extends PagingAndSortingRepository<SmartPhone, Integer> {
}
