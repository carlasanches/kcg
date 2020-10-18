/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carla
 */
public class KCG {

    /**
     * @param args the command line arguments
     */
    
    public static double upperBound(int currentWeight, int currentValue, int k, int capacity, List<Item> items){
        int weight = currentWeight;
        int profit = currentValue;
        
        for(int i = k+1; i < items.size(); i++){ 
            weight += items.get(i).getWeight();
            if(weight < capacity){
                profit += items.get(i).getProfit();
            }
            else return profit;
        }
        return profit;
    }
    
    public static void backtracking(int capacity, List<Item> items){
        Solution partialSolution = new Solution(capacity);
        Solution finalSolution = new Solution(capacity);
        int currentWeight = 0;
        int currentProfit = 0;
        int finalProfit = 0;
        int k = 0; //k is the current position of the item
        
        while(true){
            while(k < items.size() && (currentWeight + items.get(k).getWeight()) <= capacity){
                currentWeight += items.get(k).getWeight();
                currentProfit += items.get(k).getProfit();
                partialSolution.getItems().add(k,1);
                partialSolution.setProfit(currentProfit);
                partialSolution.setWeight(currentWeight);
                k++;
            }
            
            System.out.println("Partial S: ");
            partialSolution.printItems();

            if(k == items.size()){
                finalProfit = currentProfit;
                k = items.size() - 1;
                
                finalSolution.getItems().clear();
                finalSolution.getItems().addAll(partialSolution.getItems());
                finalSolution.setProfit(finalProfit);
                finalSolution.setWeight(currentWeight);
                
                System.out.println("Final S:");
                finalSolution.printItems();
            }
            else{
                partialSolution.getItems().add(k,0);
            }

            while(upperBound(currentWeight, currentProfit, k, capacity, items) <= finalProfit){
                
                while(k != 0 && partialSolution.getItems().get(k) != 1){
                    k--;
                }
                if(k == 0){
                    finalSolution.printItems();
                    System.out.println(finalSolution.toString());
                    return;
                }
                partialSolution.getItems().set(k, 0);
                currentWeight -= items.get(k).getWeight();
                currentProfit -= items.get(k).getProfit();
                
                System.out.println("Partial S: ");
                partialSolution.printItems();
            }
            k++;
        }
    }
    
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
        
        backtracking(15,itens);
    }    
}
