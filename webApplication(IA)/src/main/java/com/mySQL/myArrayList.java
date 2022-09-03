package com.mySQL;

// need to use dynamic arrays to store the data from mySQL queries
// https://www.youtube.com/watch?v=FEegPsVlB40 - youtube explanation of code

public class myArrayList { // use normal OOP concepts to use this program. 

    private String[] theArray;
    private int numItems;
    int Index;

    public myArrayList(int StartingIndex){ // contructor
        theArray = new String [StartingIndex];
        numItems = 0;
        Index = StartingIndex; // index stores the current amount of indexes in the array
        

    }

    public void addItem(String str){
        if (numItems == theArray.length){
            growArray();  // the number of indexes double
        }
        theArray[numItems] = str; // adds the string here
        numItems++;
        

    }

    public String getItem(int index){
        return theArray[index];
    }

    public void growArray(){ // doubles the index of the array (the dynamic part of the code)
        String[] tempArr = new String[theArray.length*2];
        Index = Index * 2;
        for (int i = 0; i < numItems; i++){
            tempArr[i] = theArray[i];
        }
        theArray = tempArr;
    }
   
    public String[] getArray(){
        return theArray;
    }
    
    public void close() {
     theArray = null;
     numItems = 0;
     Index = 0;
    
}
    
    




}
