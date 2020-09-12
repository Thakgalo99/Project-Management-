package com.projectmanagement.api.repositories;

import com.projectmanagement.api.entities.Client;
import com.projectmanagement.api.repositories.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CommonRepository<Client> {
    Client findByFirstName(String firstName);
    Client findByLastName (String lastName);
    Client findByFirstNameAndLastName(String firstName,String lastName);
    Client findByEmail(String email);

    @Query(value = "SELECT * FROM clients c WHERE (c.first_name LIKE %:search% OR c.last_name LIKE %:search%) OR c.email= :search",nativeQuery = true)
    Page<Client> findAllClientByCriteria(Pageable pageableRequest, @Param("search") String search);

}
