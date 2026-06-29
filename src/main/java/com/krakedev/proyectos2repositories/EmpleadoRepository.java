package com.krakedev.proyectos2repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krakedev.proyectos2.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {


}