package com.example.projetopdist.services;

import com.example.projetopdist.model.Funcionario;
import com.example.projetopdist.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Long id){
        return funcionarioRepository.findById(id).get();
    }

    public Funcionario cadastrarFuncionario(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public void apagarFuncionario(Funcionario funcionario){
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario alterar(Funcionario obj) {
        buscarFuncionarioPorId(obj.getId());
        return funcionarioRepository.save(obj);
    }
}
