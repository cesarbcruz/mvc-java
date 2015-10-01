package logic.avaliacao.controller;

import java.util.List;

import javax.swing.SwingUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import logic.avaliacao.data.FilmeDAO;
import logic.avaliacao.event.AbstractEventListener;
import logic.avaliacao.event.AtualizarListarFilmeEvent;
import logic.avaliacao.event.BuscarFilmeEvent;
import logic.avaliacao.event.DeletarFilmeEvent;
import logic.avaliacao.event.IncluirFilmeEvent;
import logic.avaliacao.model.Filme;
import logic.avaliacao.view.ManterFilmesGUI;

public class ManterFilmesCtrl extends AbstractFilmeController {

    private ManterFilmesGUI frame;
    private IncluirFilmeController incluirController;

    public ManterFilmesCtrl() throws Exception {
        this.frame = new ManterFilmesGUI();
        this.frame.addWindowListener(this);

        this.incluirController = new IncluirFilmeController(this);

        registerEventListener(IncluirFilmeEvent.class, new AbstractEventListener<IncluirFilmeEvent>() {
            public void handleEvent(IncluirFilmeEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            refreshTable();
                        } catch (Exception ex) {
                            Logger.getLogger(ManterFilmesCtrl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }
        });

        registerEventListener(DeletarFilmeEvent.class, new AbstractEventListener<DeletarFilmeEvent>() {
            public void handleEvent(DeletarFilmeEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        refreshTable();
                    }
                });
            }
        });

        registerEventListener(AtualizarListarFilmeEvent.class, new AbstractEventListener<AtualizarListarFilmeEvent>() {
            public void handleEvent(AtualizarListarFilmeEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        refreshTable();
                    }
                });
            }
        });

        registerEventListener(BuscarFilmeEvent.class, new AbstractEventListener<BuscarFilmeEvent>() {
            public void handleEvent(final BuscarFilmeEvent event) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        List<Filme> list = event.getTarget();
                        if (list != null) {
                            frame.refreshTable(event.getTarget());
                        }
                    }
                });
            }
        });

        frame.setVisible(true);
        refreshTable();
    }

    private void refreshTable() {
        try {
            frame.refreshTable(getFilmeDAO().listarFilme());
        } catch (Exception ex) {
            Logger.getLogger(ManterFilmesCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public FilmeDAO getFilmeDAO() {
        return new FilmeDAO();
    }

    public ManterFilmesGUI getFrame() {
        return frame;
    }
    
}
