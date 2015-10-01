package logic.avaliacao.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import logic.avaliacao.action.AbstractAction;
import logic.avaliacao.action.BooleanExpression;
import logic.avaliacao.action.ConditionalAction;

import logic.avaliacao.data.FilmeDAO;
import logic.avaliacao.event.DeletarFilmeEvent;
import logic.avaliacao.event.IncluirFilmeEvent;
import logic.avaliacao.model.Filme;
import logic.avaliacao.validation.FilmeValidator;
import logic.avaliacao.validation.Validator;
import logic.avaliacao.view.ManterFilmesGUI;

public class IncluirFilmeController extends AbstractFilmeController {

    private ManterFilmesGUI frame;
    private Validator<Filme> validador = new FilmeValidator();

    public IncluirFilmeController(final ManterFilmesCtrl parent) {
        super(parent);
        this.frame = parent.getFrame();

        frame.addWindowListener(this);
        registerAction(frame.getCancelar(), new AbstractAction() {
            @Override
            public void action() {
                cleanUp();
            }
        });

        registerAction(frame.getSalvar(),
                ConditionalAction.build()
                .addConditional(new BooleanExpression() {

                    public boolean conditional() {
                        Filme f = frame.getFilme();
                        String msg = validador.validate(f);
                        if (!"".equals(msg == null ? "" : msg)) {
                            JOptionPane.showMessageDialog(frame, msg, "Validação", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        }
                        return true;
                    }
                })
                .addAction(new AbstractAction() {
                    private Filme f;

                    @Override
                    public void action() {
                        f = frame.getFilme();
                        FilmeDAO dao = parent.getFilmeDAO();
                        try {
                            dao.criarFilme(f);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    @Override
                    public void posAction() {
                        cleanUp();
                        fireEvent(new IncluirFilmeEvent(f));
                        f = null;
                    }
                }
                ));

        registerAction(frame.getRemover(), new AbstractAction() {
            Filme f;

            @Override
            public void action() {
                Filme f = frame.getFilmeSelecionado();
                if (f == null) {
                    JOptionPane.showMessageDialog(frame, "Nenhum filme foi selecionado!");
                } else {
                    Integer id = f.getId();
                    if (id > 0) {
                        try {
                            f = parent.getFilmeDAO().obterFilme(id);
                            if (f != null) {
                                parent.getFilmeDAO().removerFilme(f);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(IncluirFilmeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

            @Override
            public void posAction() {
                cleanUp();
                fireEvent(new DeletarFilmeEvent(f));
                f = null;
            }
        });
    }
}
