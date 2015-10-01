package logic.avaliacao.event;

import logic.avaliacao.model.Filme;

public class IncluirFilmeEvent extends AbstractFilmeEvent<Filme> {

    public IncluirFilmeEvent(Filme m) {
        super(m);
    }
}
