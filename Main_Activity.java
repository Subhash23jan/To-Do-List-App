package com.example.todolist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> itemList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item=findViewById(R.id.ediText);
        add=findViewById(R.id.button);
        listView=findViewById(R.id.list);

        itemList=FileHandler.readData(this);

        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1,itemList);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = item.getText().toString();
                item.setText(" ");
                if(itemName.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"The task shouldn't be empty.!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                itemList.add(itemName);
                FileHandler.writeData(itemList,getApplicationContext());
                arrayAdapter.notifyDataSetChanged();
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Delete");
                alert.setMessage("Do you want to delete this item ..?");
                alert.setCancelable(false);
                alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                       itemList.remove((int) l);
                        arrayAdapter.notifyDataSetChanged();
                       FileHandler.writeData(itemList,getApplicationContext());
                    }
                });

                AlertDialog alertDialog= alert.create();
                alertDialog.show();
            }
        });

    }
}