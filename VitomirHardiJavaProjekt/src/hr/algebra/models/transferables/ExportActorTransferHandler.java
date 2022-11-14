/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models.transferables;

import hr.algebra.models.Actor;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;


/**
 *
 * @author vitom
 */
public class ExportActorTransferHandler extends TransferHandler {
    private final JList<Actor>  allActors;

    public ExportActorTransferHandler(JList<Actor> allActors) {
        this.allActors = allActors;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new ActorTransferable(allActors.getSelectedValue());
        //return super.createTransferable(c); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSourceActions(JComponent c) {
                    return COPY;

        //return super.getSourceActions(c); //To change body of generated methods, choose Tools | Templates.
    }
    
}
