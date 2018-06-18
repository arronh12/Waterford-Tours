/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterfordtours;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Arron
 */
public class Data {
    
    File file;
    
    //method to add data to file
    public void addData(ArrayList<Attractions> TouristAttraction) throws FileNotFoundException, IOException{
        FileOutputStream FO = new FileOutputStream(file);
        ObjectOutputStream OS = new ObjectOutputStream(FO);
        
        for(Attractions a : TouristAttraction){
            OS.writeObject(a);
        }
        OS.close();
        FO.close();
    }
    
    //method to read data from file and add to arraylist
    public ArrayList<Attractions> loadData() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream FI = new FileInputStream(file);
        ObjectInputStream OI = new ObjectInputStream(FI);
        ArrayList<Attractions> attractionsOut = new ArrayList();
        
        try{
            while(true){
                Attractions a = (Attractions)OI.readObject();
                attractionsOut.add(a);
            }
        }catch(EOFException ex){   
        } 
        
        FI.close();
        OI.close();
        return attractionsOut;
    }
    
    //method to build file
    public void buildFile(){
    file = new File("AttractionsSave.txt");
    }
   
}
