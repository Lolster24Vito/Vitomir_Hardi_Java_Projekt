/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.dragTransfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.TransferHandler;

/**
 *
 * @author vitom
 */
public class ImportTransferHandler1 extends TransferHandler {
    //this is inside frame
     // we define whether we can import stringFlavor that we need for JList<String>
        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        // we import the data
        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            Transferable transferable = support.getTransferable();
            
            
               // String data = (String) transferable.getTransferData(DataFlavor.stringFlavor);

               /* if (!destinationModel.contains(data)) 
                {
                    destinationModel.addElement(data);
                    lsDestination.setModel(destinationModel);
                    // we remove the item from the source, in case of MOVE
                    //((DefaultListModel<String>)lsSource.getModel()).remove(lsSource.getSelectedIndex());
                    return true;
                }
*/                    return true;


            
        }
}
