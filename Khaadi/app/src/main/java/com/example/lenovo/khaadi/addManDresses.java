package com.example.lenovo.khaadi;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.khaadi.Database.DBHelper;


public class addManDresses extends AppCompatActivity {
    EditText dress_code,quantity;
    Spinner dress_type;
    Button addMan_Dress;
    DBHelper DB_Helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_man_dresses);
        dress_code=(EditText)findViewById(R.id.manDid);
        dress_type=(Spinner)findViewById(R.id.menType);
        quantity=(EditText)findViewById(R.id.quantity);
        addMan_Dress=(Button)findViewById(R.id.addDressM);
        DB_Helper=new DBHelper(this);
        addMan_Dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long rid = DB_Helper.insert(dress_code.getText().toString(), dress_type.getSelectedItem().toString(),"Man", Integer.parseInt(quantity.getText().toString()));
                Cursor b = DB_Helper.read(rid);
                String[] rows = new String[b.getCount()];
                while (b.moveToNext()) {
                    rows[b.getPosition()] = "ID: " + b.getInt(b.getColumnIndex(DBHelper.ID))
                            + "\n" + "DCODE: " + b.getString(b.getColumnIndex(DBHelper.DCODE));
                }
               Toast.makeText(addManDresses.this,dress_type.getSelectedItem()+" added",Toast.LENGTH_LONG).show();

                b.close();
                dress_code.setText("");
                quantity.setText("");
            }
        });
    }






}