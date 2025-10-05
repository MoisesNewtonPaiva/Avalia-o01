package br.com.empresa.Controller;

import br.com.empresa.Model.Funcionario;
import br.com.empresa.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping 
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping 
    public Funcionario criarFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetalhes) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(funcionarioDetalhes.getNome());
                    funcionario.setEmail(funcionarioDetalhes.getEmail());
                    funcionario.setDataAdmissao(funcionarioDetalhes.getDataAdmissao());
                    funcionario.setDepartamento(funcionarioDetalhes.getDepartamento());
                    Funcionario atualizado = funcionarioRepository.save(funcionario);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionarioRepository.delete(funcionario);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}