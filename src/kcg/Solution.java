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
public class Solution {
    
    private List<Integer> items;
    private int profit;
    private int weight;

    public Solution(int size) {
        this.items = new ArrayList(size);
        this.profit = 0;
        this.weight = 0;
    }
    
    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public void printItems(){
        
        System.out.print("S = {");
        
        for(Integer item : this.items){
            System.out.print(item + ", ");
        }
        
        System.out.println("}");
    }

    @Override
    public String toString() {
        return "Solution{" + "profit=" + profit + ", weight=" + weight + '}';
    }
    
    
}
