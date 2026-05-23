package com.ndjana.pkf.Repositories;

import com.ndjana.pkf.models.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepo extends JpaRepository<Responsible,Long> {

}
