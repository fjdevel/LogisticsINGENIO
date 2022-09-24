package com.ingenio.logisticIngenio.repositories;

import com.ingenio.logisticIngenio.models.AppUser;
import com.ingenio.logisticIngenio.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Transactional
    void delete(Customer customer);

    Customer findByNumDoc(String numDoc);

    Customer findByUser(AppUser user);
}
