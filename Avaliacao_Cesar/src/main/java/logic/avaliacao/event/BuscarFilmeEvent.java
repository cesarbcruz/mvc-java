package logic.avaliacao.event;

import java.util.List;
import logic.avaliacao.model.Filme;

public class BuscarFilmeEvent extends AbstractFilmeEvent<List<Filme>> {

    public BuscarFilmeEvent(List<Filme> f) {
        super(f);
    }

}
