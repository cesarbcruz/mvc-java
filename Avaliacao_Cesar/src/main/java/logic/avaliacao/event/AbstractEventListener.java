package logic.avaliacao.event;

public interface AbstractEventListener<M extends AbstractFilmeEvent<?>> {

	 public void handleEvent(M event);

}
