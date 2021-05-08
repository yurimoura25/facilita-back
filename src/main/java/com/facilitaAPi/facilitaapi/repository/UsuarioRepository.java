package com.facilitaAPi.facilitaapi.repository;

import com.facilitaAPi.facilitaapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
