package br.com.ibratan.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Tape implements Serializable {

    private Integer comp;

    private List<Integer> pos;

    private String[] tape;


    public void setFita(String p, Integer pos){
        this.tape[pos] = p;
    }

    public String pegarPosicao(Integer p){
        return this.tape[p];
    }

}
