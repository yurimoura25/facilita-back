package com.facilitaAPi.facilitaapi.Controller.form;

import com.facilitaAPi.facilitaapi.models.Endereco;
import com.facilitaAPi.facilitaapi.models.Instituicao;

import java.util.List;

public class InstituicaoForm {

    private Instituicao instituicao;
    private List<Endereco> enderecos;

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
