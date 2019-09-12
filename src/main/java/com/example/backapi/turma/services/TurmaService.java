package com.example.backapi.turma.services;

import com.example.backapi.notificacao.Firebase;
import com.example.backapi.turma.domain.Turma;
import com.example.backapi.turma.repositories.TurmaRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    Firebase firebase;

    public Turma save(Turma turma) throws FirebaseMessagingException {
        if (turma.getNome() == null || turma.getChaveDeAcesso() == null){
            throw new ConstraintViolationException("Nome da turma e chave de acesso é necessário", null);
        }

        verificarSeNomeDaTurmaJaExiste(turma);

        verificarSeChaveDeAcessoJaExiste(turma);

        Turma turmaCadastrada = turmaRepository.save(turma);

        String topico = turmaCadastrada.getClass().toString().split(" ")[1].split("com.example.backapi.turma.domain.")[1]+"-"+ turmaCadastrada.getId();

        firebase.sendMessage(topico, "Turma cadastrada");

        return turma;
    }

    private void verificarSeChaveDeAcessoJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getChaveDeAcesso().equals(turmas.get(i).getChaveDeAcesso())){
                throw new ConstraintViolationException("Chave de acesso já cadastrado", null);
            }
        }
    }

    private void verificarSeNomeDaTurmaJaExiste(Turma turma) {
        List<Turma> turmas = findAll();
        for (int i = 0; i < turmas.size(); i++){
            if (turma.getNome().equals(turmas.get(i).getNome())){
                throw new ConstraintViolationException("Nome já cadastrado", null);
            }
        }
    }

    public List<Turma> findAll(){
        return turmaRepository.findAll();
    }

}