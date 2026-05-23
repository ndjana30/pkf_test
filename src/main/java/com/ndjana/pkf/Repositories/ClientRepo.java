package com.ndjana.pkf.Repositories;

import com.ndjana.pkf.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> //repository, to use CRUD methods
{
}
