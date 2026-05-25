package com.ndjana.pkf.repositories;

import com.ndjana.pkf.models.SService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<SService,Long> //repository, to use CRUD methods
{

}
