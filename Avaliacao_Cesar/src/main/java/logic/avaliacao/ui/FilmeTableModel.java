package logic.avaliacao.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import logic.avaliacao.model.Filme;

public class FilmeTableModel extends AbstractTableModel {

    private List<Filme> filmes;

    private String[] colNomes = {"Filme", "Ano"};

    private Class<?>[] colTipos = {String.class, String.class};

    public FilmeTableModel() {
    }

    public void reload(List<Filme> filmes) {
        this.filmes = filmes;
        //atualiza o componente na tela
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        return colTipos[coluna];
    }

    //@Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int coluna) {
        return colNomes[coluna];
    }

    //@Override
    public int getRowCount() {
        if (filmes == null) {
            return 0;
        }
        return filmes.size();
    }

    //@Override
    public Object getValueAt(int linha, int coluna) {
        Filme f = filmes.get(linha);
        switch (coluna) {
            case 0:
                return f.getNome();
            case 1:
                return f.getAnoLancamento();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Filme getFilmeAt(int index) {
        return filmes.get(index);
    }

}
