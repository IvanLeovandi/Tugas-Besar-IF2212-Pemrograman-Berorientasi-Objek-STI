package com.simplicity.Interfaces;

public interface Purchasable extends Interactable{
    public int getPrice();
    public void setPrice(int price);
    public boolean getCanBePurchased();
    public void setCanBePurchased(boolean canBePurchased);
}
