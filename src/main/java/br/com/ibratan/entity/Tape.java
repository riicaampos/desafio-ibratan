package br.com.ibratan.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
/**
 * @comp = comprimento da fita
 * @pos = as posições que tera reagente pingado
 * @tape = a fita, criada passando o @comp como parametro
 * @dias = a quantiade de dias que a fita demorará para ser tomada
 */
public class Tape implements Serializable {

    private Integer comp;

    private List<Integer> pos;

    private String[] tape;

    private Integer dias;


    public void setFita(String p, Integer pos){
        this.tape[pos] = p;
    }

    public String pegarPosicao(Integer p){
        return this.tape[p];
    }

}
