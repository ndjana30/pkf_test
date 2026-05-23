package com.ndjana.pkf.Repositories;

import com.ndjana.pkf.models.S_Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<S_Service,Long> //repository, to use CRUD methods
{

}
