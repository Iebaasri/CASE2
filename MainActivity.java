package com.iebaasri.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText2;
    Button button_add,button_view;
    DatabaseHelperClass DatabaseHelperClass;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.menu_name);
        editText2 = (EditText) findViewById(R.id.menu_price);
        button_add = (Button) findViewById(R.id.button_add);
        button_view = (Button) findViewById(R.id.button_view);

        DatabaseHelperClass = new DatabaseHelperClass(this);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                String newEntry2 = editText2.getText().toString();

                if (editText.length() != 0 && editText2.length() != 0) {
                    AddData(newEntry,newEntry2);
                    editText.setText("");
                    editText2.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListMenu.class);
                startActivity(intent);
            }
        });

    }

    public void AddData(String newEntry,String newEntry2) {
        boolean insertData = DatabaseHelperClass.addData(newEntry,newEntry2);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}

