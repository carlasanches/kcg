/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carla
 */
public class PMC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Item item1 = new Item(10,3);
        Item item2 = new Item(8,4);
        Item item3 = new Item(4,4);
        Item item4 = new Item(6,5);
        Item item5 = new Item(8,7);
        Item item6 = new Item(6,9);
        Item item7 = new Item(9,9);
        Item item8 = new Item(6,10);
        
        List<Item> itens = new ArrayList();   
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        itens.add(item4);
        itens.add(item5);
        itens.add(item6);
        itens.add(item7);
        itens.add(item8);

        itens.forEach((item)->System.out.println(item.toString()));
    }    
}
