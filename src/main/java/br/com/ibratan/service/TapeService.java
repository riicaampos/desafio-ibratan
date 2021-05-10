package br.com.ibratan.service;

import br.com.ibratan.entity.Tape;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TapeService {

    Integer dias = 0;

    List<Integer> listPreenchidos = new ArrayList<>();

    public Tape calculaTempo(Tape t) {

        t.setTape(new String[t.getComp()]);

        for (Integer i : t.getPos()) {
            t.setFita("P", i);
            adicionarPreenchidos(i);
        }

        percorre(t);

        return t;

    }

    public void percorre(Tape t) {

        boolean bDia = false;

        List<Integer> auxListP = new ArrayList<>();

        while (countNaoPreenchidos(t) > 0) {
            for (Integer i : listPreenchidos) {

                if (t.pegarPosicao(i) == null) {
                    t.setFita("P", i);
                    auxListP.add(i);
                } else if (i - 1 >= 0) {
                    if (t.pegarPosicao(i - 1) == null) {
                        t.setFita("P", i - 1);
                        auxListP.add(i - 1);
                        bDia = true;
                    }
                    if (i + 1 < t.getTape().length && t.pegarPosicao(i + 1) == null) {

                        t.setFita("P", i + 1);
                        auxListP.add(i + 1);
                        bDia = true;
                    }
                }

            }
            if (bDia == true) {
                dias += 1;
            }

            for (Integer in : auxListP) {
                adicionarPreenchidos(in);
            }

        }

        t.setDias(this.dias);

    }

    public int countNaoPreenchidos(Tape t) {

        int count = 0;

        for (int i = 0; i < t.getTape().length; i++) {
            if (t.pegarPosicao(i) == null) {
                count++;
            }
        }
        return count;
    }

    public void adicionarPreenchidos(Integer i) {
        if (!listPreenchidos.contains(i)) {
            listPreenchidos.add(i);
        }

    }


}
