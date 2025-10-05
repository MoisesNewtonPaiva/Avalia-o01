package br.com.empresa.Controller.Web;

import br.com.empresa.Model.Funcionario;
import br.com.empresa.Repository.DepartamentoRepository;
import br.com.empresa.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/funcionarios")
public class FuncionarioWebController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioRepository.findAll());
        return "funcionarios/list";
    }

    @GetMapping("/novo")
    public String novoFuncionarioForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "funcionarios/form";
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String editarFuncionarioForm(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de funcionário inválido:" + id));
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "funcionarios/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFuncionario(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/web/funcionarios";
    }
}
