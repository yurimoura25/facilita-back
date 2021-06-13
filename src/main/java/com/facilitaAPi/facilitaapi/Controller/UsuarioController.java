package com.facilitaAPi.facilitaapi.Controller;

import com.facilitaAPi.facilitaapi.Config.CustomValidationExpection;
import com.facilitaAPi.facilitaapi.Controller.dto.UsuarioDTO;
import com.facilitaAPi.facilitaapi.models.Endereco;
import com.facilitaAPi.facilitaapi.models.Instituicao;
import com.facilitaAPi.facilitaapi.models.Usuario;
import com.facilitaAPi.facilitaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>>  listUsuarioDTO(){
        List<Usuario> listUsuario = usuarioRepository.findAll();
        List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
        if(!listUsuario.isEmpty()){
            listUsuario.forEach(usuario -> {
                listUsuarioDTO.add(new UsuarioDTO(usuario));
            });
        } else {
            throw new CustomValidationExpection("Usuario", "Não há nenhum usuário cadastrado no sistema");
        }
        return ResponseEntity.ok(listUsuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO>  listUsuarioDTO(@PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(new UsuarioDTO(usuarioOptional.get()));
        } else {
            throw new CustomValidationExpection("id", "Não foi encontrado o ID");
        }
    }

//    @GetMapping
//    public ResponseEntity<?> usuarioValido(@RequestBody Usuario usuario) {
//        if(usuario.getEmail() != null && usuario.getSenha() != null) {
//
//        }
//        return ResponseEntity.badRequest().build();
//    }
    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        if(usuario!=null){
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            throw new CustomValidationExpection("Usuario", "Objeto inválido");
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            throw new CustomValidationExpection("id", "Não foi encontrado o ID");
        }
    }
}
