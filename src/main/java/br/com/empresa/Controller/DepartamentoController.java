package br.com.empresa.Controller;

import br.com.empresa.Model.Departamento;
import br.com.empresa.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping 
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Departamento> buscarDepartamentoPorId(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping 
    public Departamento criarDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}") 
    public ResponseEntity<Departamento> atualizarDepartamento(@PathVariable Long id, @RequestBody Departamento departamentoDetalhes) {
        return departamentoRepository.findById(id)
                .map(departamento -> {
                    departamento.setNome(departamentoDetalhes.getNome());
                    departamento.setLocalizacao(departamentoDetalhes.getLocalizacao());
                    Departamento atualizado = departamentoRepository.save(departamento);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<?> deletarDepartamento(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(departamento -> {
                    departamentoRepository.delete(departamento);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
