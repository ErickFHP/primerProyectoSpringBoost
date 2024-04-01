package com.api.primerProyecto.repositories;

import com.api.primerProyecto.models.TareaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITareaRepository extends JpaRepository<TareaModel, Long> {



}
