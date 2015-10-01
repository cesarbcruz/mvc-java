package logic.avaliacao.action;

public abstract class AbstractAction {

	/**
	 * Método principal, define o processamento da <code>AbstractAction</code>.
	 */
	protected abstract void action();
	
	/**
	 * Método acionado <strong>antes</string> de <code>action()</code>.
	 * <p>Caso uma exceção (<code>RuntimeException</code>) seja lançada, a execução de toda a <code>AbstractAction</code> é interrompida.</p>
	 */
	protected void preAction(){}

	/**
	 * Método executado após a conclusão de <code>action()</code>.
	 */
	protected void posAction(){}
	
	/**
	 * Método é acionado quando alguma falha ocorre durante a execução de <code>action</code>, <code>preAction</code> ou <code>posAction</code>.
	 */
	protected void actionFailure(){}
	
	/**
	 * Método responsável por organizar e executar a cadeia de métodos de <code>AbstractAction</code>.
	 * @throws <code>RuntimeException</code> caso algum erro ocorra.
	 */
	public final void actionPerformed() {
    	try {
    		preAction();
    		action();
    		posAction();
    	} catch (Exception ex) {
    		actionFailure();
    		throw new RuntimeException(ex);
    	}
	}
	
}
