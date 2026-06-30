package com.krakedev.proyectos2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krakedev.proyectos2.entity.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {


}