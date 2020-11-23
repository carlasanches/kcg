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
    private int conflict[][];

    public KCG(int capacity, List<Item> items, int conflict[][]) {
        this.capacity = capacity;        
        this.items = items;
        this.numItems = items.size();
        this.conflict = conflict;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Item> getItems() {
        return items;
    }


    public int[][] getConflict() {
        return conflict;
    }

    public int getNumItems() {
        return numItems;
    }    
    
    public void printConflict(){
        
        double numConflict = conflict.length;
        
        for(int x = 0; x < numConflict; x++){
            for(int y = 0; y < numConflict; y++){
                System.out.println("["+x+"]"+"["+y+"]"+ conflict[x][y]);                             
            }
        }
    }

    @Override
    public String toString() {
        return "KCG{" + "capacity=" + capacity + ", numItems=" + numItems + ", items=" + items;
    }    
}
