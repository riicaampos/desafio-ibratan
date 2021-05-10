package br.com.ibratan.controller;

import br.com.ibratan.entity.Tape;
import br.com.ibratan.service.TapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.xml.ws.Response;
import java.util.ArrayList;

@RestController
public class TapeController {

    @Autowired
    private TapeService service;

    private Tape tape;

    @PostConstruct
    public void init() {
        tape = new Tape();
    }

    @GetMapping("/verificar-fita")
    public Tape getTapeExample() {
        return tape;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/preencher-fita")
    public ResponseEntity enviaJson(@RequestBody Tape t) {
        if (t.getComp() <= 1 || t.getComp() >= 100000) {
           return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("A fita deve ter comprimento maior que 1 e menor que 100.000");
        } else if (t.getPos().size() >= 1000) {
           return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Não é possível preencher mais que 1.000 posições");
        } else {
            tape = service.calculaTempo(t);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("Fita Criada com sucesso");
        }
    }

}
