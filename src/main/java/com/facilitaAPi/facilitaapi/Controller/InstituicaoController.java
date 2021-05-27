package com.facilitaAPi.facilitaapi.Controller;

import com.facilitaAPi.facilitaapi.Controller.dto.EnderecoDTO;
import com.facilitaAPi.facilitaapi.Controller.dto.InstituicaoDTO;
import com.facilitaAPi.facilitaapi.Controller.form.InstituicaoForm;
import com.facilitaAPi.facilitaapi.models.Endereco;
import com.facilitaAPi.facilitaapi.models.Instituicao;
import com.facilitaAPi.facilitaapi.repository.EnderecoRepository;
import com.facilitaAPi.facilitaapi.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/instituicao")
@CrossOrigin(origins = "http://localhost/3000")
public class InstituicaoController{

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    @GetMapping
    public List<InstituicaoDTO> getInstituicoes() {
        List<Instituicao> listInstituicoes = instituicaoRepository.findAll();

        List<InstituicaoDTO> listInstituicaoDTO = new ArrayList<>();

        if (!listInstituicoes.isEmpty()) {

            listInstituicoes.forEach(instituicao -> {
                List<Endereco> listEnderecos = enderecoRepository.findByInstituicaoId(instituicao.getId());
                InstituicaoDTO instituicaoDTO = new InstituicaoDTO(instituicao);

                List<EnderecoDTO> listEnderecosDTO = new ArrayList<>();

                if (!listEnderecos.isEmpty()) {

                    listEnderecos.forEach((endereco -> {
                        listEnderecosDTO.add(new EnderecoDTO(endereco));
                    }));
                }
                instituicaoDTO.setEnderecos(listEnderecosDTO);
                listInstituicaoDTO.add(instituicaoDTO);
            });
        }
        return listInstituicaoDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoDTO> getInstituicaoPorId(@PathVariable Integer id){
        Optional<Instituicao> instituicaoOptional = instituicaoRepository.findById(id);
        List<Endereco> listEndereco = enderecoRepository.findByInstituicaoId(id);
        if(instituicaoOptional.isPresent()){
            InstituicaoDTO instituicaoDTO = new InstituicaoDTO(instituicaoOptional.get());
            List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

            if(!listEndereco.isEmpty()){
                listEndereco.forEach(endereco->{
                    listEnderecoDTO.add(new EnderecoDTO(endereco));
                });
            }

            instituicaoDTO.setEnderecos(listEnderecoDTO);

            return ResponseEntity.ok(instituicaoDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<InstituicaoForm> cadastrarInstituicao(@RequestBody InstituicaoForm instituicaoForm){
        if(instituicaoForm!= null){
            instituicaoRepository.save(instituicaoForm.getInstituicao());
            if(instituicaoForm.getEnderecos()!=null){
                instituicaoForm.getEnderecos().forEach(endereco->{
                    endereco.setInstituicao(instituicaoForm.getInstituicao());
                    enderecoRepository.save(endereco);
                });
            }
        }
        return ResponseEntity.ok(instituicaoForm);
    }

    @PostMapping("/endereco/{id}")
    @Transactional
    public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco, @PathVariable Integer id) {
        if(id!= null) {
            Optional<Instituicao> instituicao = instituicaoRepository.findById(id);
            if(instituicao.isPresent()){
                if(endereco!=null){
                    endereco.setInstituicao(instituicao.get());
                    enderecoRepository.save(endereco);
                }

                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerInstituicaoPorId(@PathVariable Integer id){
        Optional<Instituicao> instituicaoOptional = instituicaoRepository.findById(id);
        List<Endereco> listEndereco = enderecoRepository.findByInstituicaoId(id);

        if(instituicaoOptional.isPresent()){
            removerListaEndereco(listEndereco);
            instituicaoRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


    public void removerListaEndereco(List<Endereco> listEndereco){
        try{
            listEndereco.forEach(endereco->{enderecoRepository.delete(endereco);});
        }catch(Exception e){
            System.out.println("ERROR: " + e);
        }
    }
}
