package com.krakedev.proyectos2.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.proyectos2.entity.Proyecto;
import com.krakedev.proyectos2.repositories.ProyectoRepository;

@Service
public class ProyectoService {

	// Inyeccion y constructor:
	private final ProyectoRepository proyectoRepository;

	public ProyectoService(ProyectoRepository proyectoRepository) {
		this.proyectoRepository = proyectoRepository;
	}

	// Servicios CRUD:

	public Proyecto crear(Proyecto proyecto) {

		if (proyecto == null) {
			throw new RuntimeException("El proyecto no puede ser nulo");
		}

		if (proyecto.getNombre() == null || proyecto.getNombre().isEmpty()) {
			throw new RuntimeException("El nombre del proyecto no puede ser nulo o estar vacio");
		}

		if (proyecto.getDescripcion() == null || proyecto.getDescripcion().isEmpty()) {
			throw new RuntimeException("La descripcion del proyecto no puede ser nulo o estar vacio");
		}

		if (proyecto.getFechaInicio() == null) {
			throw new RuntimeException("La Fecha de inicio del proyecto no puede ser nulo o estar vacio");
		}

		return proyectoRepository.save(proyecto);
	}

	public List<Proyecto> listar() {

		return proyectoRepository.findAll();

	}

	public Proyecto buscarPorId(int id) {
		return proyectoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Proyecto con ID " + id + " no existe"));
	}

	public Proyecto actualizar(int id, Proyecto proyecto) {

		Proyecto proyectoExistente = buscarPorId(id);

		if (proyecto == null) {
			throw new RuntimeException("Los datos del proyecto no pueden ser nulos");
		}

		if (proyecto.getNombre() != null && !proyecto.getNombre().isEmpty()) {
			proyectoExistente.setNombre(proyecto.getNombre());
		}

		if (proyecto.getDescripcion() != null) {
			proyectoExistente.setDescripcion(proyecto.getDescripcion());
		}

		if (proyecto.getFechaInicio() != null) {
			proyectoExistente.setFechaInicio(proyecto.getFechaInicio());
		}

		return proyectoRepository.save(proyectoExistente);

	}

	public boolean eliminar(int id) {

		Proyecto proyectoConsultado = buscarPorId(id);

		if (proyectoConsultado == null) {
			throw new RuntimeException("No hay registro de proyecto para eliminar con el id: " + id);
		}

		proyectoRepository.delete(proyectoConsultado);

		return true;
	}

}
