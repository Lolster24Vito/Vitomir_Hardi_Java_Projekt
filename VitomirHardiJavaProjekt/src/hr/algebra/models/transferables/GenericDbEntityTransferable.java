/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models.transferables;

import hr.algebra.models.Actor;
import hr.algebra.models.GenericDbEntity;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author vitom
 */
public class GenericDbEntityTransferable implements Transferable {

    public static final DataFlavor ENTITY_FLAVOR=new DataFlavor(GenericDbEntity.class,"GenericDbEntity");
        private static final DataFlavor[] SUPPORTED_FLAVORS={ENTITY_FLAVOR};

    
    private final GenericDbEntity entity;

    public GenericDbEntity getEntity() {
        return entity;
    }
    public GenericDbEntityTransferable(GenericDbEntity entity) {
        this.entity=entity;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
return ENTITY_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if(isDataFlavorSupported(flavor)){
                return entity;
            }
        throw new UnsupportedFlavorException((flavor));

    }
    
}
