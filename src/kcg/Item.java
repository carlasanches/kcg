/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcg;

/**
 *
 * @author Carla
 */
public class Item {
    
    private int weight;
    private int profit;

    public Item(int profit, int weight) {
        this.weight = weight;
        this.profit = profit;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the profit
     */
    public int getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(int profit) {
        this.profit = profit;
    }    

    @Override
    public String toString() {
        return "Item{" + "weight=" + weight + ", value=" + profit + '}';
    }
}
