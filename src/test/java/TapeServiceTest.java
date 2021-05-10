import br.com.ibratan.entity.Tape;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TapeServiceTest {

    Integer dias = 0;

    Tape t = new Tape();

    List<Integer> listPreenchidos = new ArrayList<>();

    @Test
    public void calculaTapeTest() {

        t.setComp(20);
        t.setTape(new String[t.getComp()]);
        t.setPos(new ArrayList<>());
        t.getPos().add(6);
        t.getPos().add(14);
        t.getPos().add(18);

        //Primeiro preenchimento com as posições escolhidas
        //Aqui é o dia é o 0
        for (Integer i : t.getPos()) {
            t.setFita("P", i);
            adicionarPreenchidos(i);
        }

        percorre(t);

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


    }

    public void adicionarPreenchidos(Integer i) {
        if (!listPreenchidos.contains(i)) {
            listPreenchidos.add(i);
        }

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

}
