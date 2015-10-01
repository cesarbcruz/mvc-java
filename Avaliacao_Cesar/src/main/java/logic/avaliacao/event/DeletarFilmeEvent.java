package logic.avaliacao.event;

import logic.avaliacao.model.Filme;

public class DeletarFilmeEvent extends AbstractFilmeEvent<Filme> {
	
	public DeletarFilmeEvent(Filme m) {
		super(m);
	}

}
