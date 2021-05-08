package com.facilitaAPi.facilitaapi.Controller.dto;

import com.facilitaAPi.facilitaapi.models.Endereco;
import com.facilitaAPi.facilitaapi.models.Instituicao;

import java.util.List;

public class InstituicaoDTO {

    public Integer id;
    public String email;
    public String cnpj;
    public String razaoSocial;
    public List<EnderecoDTO> listEnderecos;

    public InstituicaoDTO(Instituicao instituicao) {
        this.id = instituicao.getId();
        this.email = instituicao.getEmail();
        this.cnpj = instituicao.getCnpj();
        this.razaoSocial = instituicao.getRazaoSocial();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public List<EnderecoDTO> getListEnderecos() {
        return listEnderecos;
    }

    public void setEnderecos(List<EnderecoDTO> listEnderecos) {
        this.listEnderecos = listEnderecos;
    }
}
