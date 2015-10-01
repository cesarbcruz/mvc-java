package logic.avaliacao.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;



import javax.swing.JOptionPane;
import logic.avaliacao.action.AbstractAction;
import logic.avaliacao.event.AbstractEventListener;
import logic.avaliacao.event.AbstractFilmeEvent;

public abstract class AbstractFilmeController implements ActionListener, WindowListener {
	
	private AbstractFilmeController parent;
	
	private Map<String, AbstractAction> actions = 
			new HashMap<String, AbstractAction>();
	
	private Map<Class<?>, List<AbstractEventListener<?>>> eventListeners = 
			new HashMap<Class<?>, List<AbstractEventListener<?>>>();
	
	public AbstractFilmeController(){
		this(null);
	}
	
	/**
	 * Controller possui um auto-relacionamento, útil em situações aonde uma hierarquia de controladores deve ser respeitada.
	 * @param parent controller <i>pai</i>
	 */
	public AbstractFilmeController(AbstractFilmeController parent){
		this.parent = parent;
	}

	/**
	 * Registra uma <code>ação</code> a um componente <code>button</code>.
	 * 
	 * @param source
	 * @param action
	 */
	protected void registerAction(AbstractButton source, AbstractAction action) {
		if (source.getActionCommand() == null) {
			throw new RuntimeException("Componente (Button) sem ação definida!");
		}
        source.addActionListener(this);
        this.actions.put(source.getActionCommand(), action);
    }
	
	/**
	 * Notifica o <code>evento</code>.
	 * 
	 * @param event tipo do evento
	 */
	@SuppressWarnings("unchecked")
	protected void fireEvent(AbstractFilmeEvent<?> event) {
		if (eventListeners.get(event.getClass()) != null) {
            for (AbstractEventListener eventListener : eventListeners.get(event.getClass())) {
                eventListener.handleEvent(event);
            }
        }
		if (parent != null)
			parent.fireEvent(event);
	}
	
	/**
	 * Registra um <code>listener</code> que deve ser acionado de acordo com o tipo do <code>evento</code>.
	 * 
	 * @param eventClass tipo do evento
	 * @param eventListener tratador (listener) do evento
	 */
	protected void registerEventListener(Class<?> eventClass, AbstractEventListener<?> eventListener) {
        java.util.List<AbstractEventListener<?>> listenersForEvent = eventListeners.get(eventClass);
        if (listenersForEvent == null) {
        	listenersForEvent = new ArrayList<AbstractEventListener<?>>(); 
        }
        listenersForEvent.add(eventListener);
        eventListeners.put(eventClass, listenersForEvent);
    }
	
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			AbstractButton button = (AbstractButton) actionEvent.getSource();
			String actionCommand = button.getActionCommand();
			AbstractAction action = actions.get(actionCommand);

			if (action != null) {
				try {
					action.actionPerformed();
				} catch (Exception ex) {
					handlerException(ex);
				}
			}
		} catch (ClassCastException e) {
			handlerException(new IllegalArgumentException("Action source não é um Abstractbutton: " + actionEvent));
		}
	}
	
	/**
	 * Caso ocorra alguma falha durante a <code>ação</code> apresenta uma mensagem.
	 * 
	 * @param ex
	 */
	protected void handlerException(Exception ex) {
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public AbstractFilmeController getParentController() {
        return parent;
    }
	
	/**
	 * Método utilizado para liberar recursos carregados pela Controller.
	 */
	protected void cleanUp() {}
	
	public void windowClosing(WindowEvent windowEvent) { 
		cleanUp(); 
	}
	
    public void windowOpened(WindowEvent windowEvent) {}
    public void windowClosed(WindowEvent windowEvent) {}
    public void windowIconified(WindowEvent windowEvent) {}
    public void windowDeiconified(WindowEvent windowEvent) {}
    public void windowActivated(WindowEvent windowEvent) {}
    public void windowDeactivated(WindowEvent windowEvent) {}
    
}
