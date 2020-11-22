/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

import java.util.List;

/**
 *
 * @author Carla
 */
public class KCG {
    
    private int capacity;
    private int numItems;
    private List<Item> items;
    private int numConflict;
    private int conflict[][];

    public KCG(int capacity, List<Item> items, int numConflict) {
        this.capacity = capacity;        
        this.items = items;
        this.numItems = items.size();
        this.numConflict = numConflict;
        
        for(int i = 0; i < numConflict; i++){
            for(int j = 0; j < numConflict; j++){
                this.conflict[i][j] = 0;
            }
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getNumConflict() {
        return numConflict;
    }

    public int[][] getConflict() {
        return conflict;
    }

    public int getNumItems() {
        return numItems;
    }    
}
