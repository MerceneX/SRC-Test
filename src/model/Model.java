package model;

class Model {
    public int readID(String id){
        try{
            return Integer.parseInt(id);
        } catch (java.lang.NumberFormatException e){
            System.out.println("Napačen vnos! Prosimo, vnesite število.");
        }
        return -1;
    }
}
