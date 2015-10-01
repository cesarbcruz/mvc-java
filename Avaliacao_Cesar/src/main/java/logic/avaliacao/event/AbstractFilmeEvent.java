package logic.avaliacao.event;

public abstract class AbstractFilmeEvent<Target> {

	private Target target;

	public AbstractFilmeEvent(Target target) {
		this.target = target;
	}
	
	public Target getTarget() {
		return target;
	}
}
