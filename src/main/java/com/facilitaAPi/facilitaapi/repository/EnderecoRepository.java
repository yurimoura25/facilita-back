package com.facilitaAPi.facilitaapi.repository;

import com.facilitaAPi.facilitaapi.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findByInstituicaoId(Integer id);
}
