package com.ndjana.pkf.repositories;

import com.ndjana.pkf.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> //repository, to use CRUD methods
{
}
