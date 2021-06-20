package com.facilitaAPi.facilitaapi.repository;

import com.facilitaAPi.facilitaapi.models.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {

    public Instituicao findByCnpj(String cnpj);

    public Instituicao findByEmail(String email);

    @Query("select i from Instituicao i where i.cnpj = ?1")
    public Instituicao findByCpfQuery(String cpf);
}
