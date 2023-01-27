package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {

    public static final String FILENAME="listinfo.dat";

    public static void writeData(ArrayList<String> item, Context context)
    {
        try {
            FileOutputStream fos=context.openFileOutput(FILENAME,Context.MODE_PRIVATE);
            ObjectOutputStream oas=new ObjectOutputStream(fos);
            oas.writeObject(item);
            oas.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readData(Context context) {
        ArrayList<String> itemList=null;
        try {
            FileInputStream fis=context.openFileInput(FILENAME);
            ObjectInputStream ois=new ObjectInputStream(fis);

            itemList= (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemList=new ArrayList<String>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }

}
