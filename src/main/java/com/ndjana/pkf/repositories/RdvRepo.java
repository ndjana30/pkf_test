package com.ndjana.pkf.repositories;

import com.ndjana.pkf.models.RDV;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RdvRepo extends JpaRepository<RDV,Long> //repository, to use CRUD methods
{
}
