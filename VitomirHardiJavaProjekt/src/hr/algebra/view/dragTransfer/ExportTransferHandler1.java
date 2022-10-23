/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.dragTransfer;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.MOVE;

/**
 *
 * @author vitom
 */
public class ExportTransferHandler1 extends TransferHandler {
    private final JList<String> jlist;
//this returns int and is outside frame
    public ExportTransferHandler1(JList<String> jlist) {
        this.jlist = jlist;
    }
     @Override
        public int getSourceActions(JComponent c) {
            // defines icon shown in target before drop
            //return COPY;
            return MOVE;
        }

        @Override
        public Transferable createTransferable(JComponent c) {
            return new StringSelection(jlist.getSelectedValue().toString());
        }
}
