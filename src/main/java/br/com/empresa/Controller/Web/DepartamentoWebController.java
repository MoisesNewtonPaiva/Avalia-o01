package br.com.empresa.Controller.Web;

import br.com.empresa.Model.Departamento;
import br.com.empresa.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/departamentos")
public class DepartamentoWebController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String listarDepartamentos(Model model) {
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "departamentos/list";
    }

    @GetMapping("/novo")
    public String novoDepartamentoForm(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "departamentos/form";
    }

    @PostMapping("/salvar")
    public String salvarDepartamento(@ModelAttribute Departamento departamento) {
        departamentoRepository.save(departamento);
        return "redirect:/web/departamentos";
    }

    @GetMapping("/editar/{id}")
    public String editarDepartamentoForm(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de departamento inválido:" + id));
        model.addAttribute("departamento", departamento);
        return "departamentos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirDepartamento(@PathVariable Long id) {
        departamentoRepository.deleteById(id);
        return "redirect:/web/departamentos";
    }
}
