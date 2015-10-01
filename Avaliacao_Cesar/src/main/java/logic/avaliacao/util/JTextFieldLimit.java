/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.avaliacao.util;

/**
 *
 * @author LogicPC
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {

    private int limit;
    private boolean somenteNumero=false;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }
    
    public JTextFieldLimit(int limit, boolean somenteNumero) {
        super();
        this.limit = limit;
        this.somenteNumero = somenteNumero;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (somenteNumero) {
            if (!str.matches("\\d+")) {
                return;
            }
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
