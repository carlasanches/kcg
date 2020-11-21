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
public class Solution1 {
    
    private List<Integer> items;
    private double profit;
    private double weight;

    public Solution1(int size) {
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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
