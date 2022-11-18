/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.interfaces;

/**
 *
 * @author vitom
 */
public interface EntityAddable {
    void addEntity(String name,AddableEntities entityType);
    void showAddDialog(AddableEntities entityType);
}

