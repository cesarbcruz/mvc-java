package logic.avaliacao.ui;

import java.util.List;

import javax.swing.JTable;

import logic.avaliacao.model.Filme;

public class FilmeTable extends JTable {

	private FilmeTableModel modelo;
	
	public FilmeTable() {
		modelo = new FilmeTableModel();
		setModel(modelo);
	}
	
	/**
	 * @return <code>Filme</code> selecionada na tabela. Caso a tabela não tenha elementos, retorna <code>null</code>.
	 */
	public Filme getFilmeSelected() {
		int i = getSelectedRow();
		if (i < 0) {
			return null;
		}
		return modelo.getFilmeAt(i);
	}
	

	public void reload(List<Filme> filmes) {
		modelo.reload(filmes);
	}
}
