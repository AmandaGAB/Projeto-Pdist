package com.example.projetopdist.controllers;

import java.util.List;

import com.example.projetopdist.mensageria.Configuracoes;
import com.example.projetopdist.model.BancoDeHoras;
import com.example.projetopdist.model.Funcionario;
import com.example.projetopdist.services.FuncionarioService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    private final RabbitTemplate rabbitTemplate;

    public FuncionarioController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public String enviar(Integer total) {

        rabbitTemplate.convertAndSend(
                Configuracoes.EXCHANGE_NAME,
                "funcionariosapi",
                total);

        return "Mensagem enviada! :" + "";
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){

        return ResponseEntity.ok(funcionarioService.listarFuncionarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id){
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);

        if (funcionario != null){
            return ResponseEntity.ok(funcionarioService.buscarFuncionarioPorId(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario){
        return ResponseEntity.ok(funcionarioService.cadastrarFuncionario(funcionario));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody Funcionario obj) {
        obj.setId(id);
        obj = funcionarioService.alterar(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id){
        Funcionario funcionario= funcionarioService.buscarFuncionarioPorId(id);
        if (funcionario != null){
            funcionarioService.apagarFuncionario(funcionario);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/horas", method = RequestMethod.PUT)
    public ResponseEntity<Void> alterarHora(@PathVariable Long id, @RequestBody BancoDeHoras hora) {
        Funcionario obj = funcionarioService.buscarFuncionarioPorId(id);
        obj.getHoras().add(hora);
        obj = funcionarioService.alterar(obj);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/totalHoras")
    public Integer gethoras(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
        Integer total = 0;
        for (int i = 0; i<funcionario.getHoras().size(); i++){
            total+=(Integer) funcionario.getHoras().get(Math.toIntExact(id)).getHora();
        }
        enviar(total);
        return total;
    }


}
