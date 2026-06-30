package com.krakedev.proyectos2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krakedev.proyectos2.entity.Tarea;


@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {


}
