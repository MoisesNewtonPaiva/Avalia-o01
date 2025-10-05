package br.com.empresa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.Model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}