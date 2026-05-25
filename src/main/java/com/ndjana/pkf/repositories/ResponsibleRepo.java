package com.ndjana.pkf.repositories;

import com.ndjana.pkf.models.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepo extends JpaRepository<Responsible,Long> //repository, to use CRUD methods
{

}
