package logic.avaliacao.validation;

import logic.avaliacao.model.Filme;

public class FilmeValidator implements Validator<Filme> {

    @Override
    public String validate(Filme f) {
        StringBuilder sb = new StringBuilder();
        if (f != null) {
            if (f.getId() == 0) {
                sb.append("Código não informado\n");
            }
            if (f.getNome() == null || f.getNome().isEmpty()) {
                sb.append("Nome não informado\n");
            }
            if (f.getAnoLancamento() == null || f.getAnoLancamento().isEmpty()) {
                sb.append("Ano Lançamento não informado\n");
            }
        }
        return sb.toString();
    }

}
