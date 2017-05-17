package com.mohan.caliber.appcracker;

/**
 *  Created by mohan on 13/05/17.
 */
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddExpensesActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Button addTodoBtn,but;
    private AutoCompleteTextView subjectEditText;
    private Spinner descEditText;
    private EditText uniEditText;
    private EditText pirEditText;
    String desc = "";
    String[] cetarr = new String[]{"Vegetables","Fruits","Transport","Other"};
Spinner nwsp;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_record);

        subjectEditText = (AutoCompleteTextView) findViewById(R.id.item_edittext);
        descEditText = (Spinner) findViewById(R.id.category_edittext);
        uniEditText = (EditText)findViewById(R.id.unit_edittext);
        pirEditText = (EditText)findViewById(R.id.price_edittext);
        String[] item = getResources().getStringArray(R.array.list_of_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_dropdown_item_1line,cetarr);
        descEditText.setAdapter(adapter);
        descEditText.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this,android.R.layout.simple_dropdown_item_1line,item);
        subjectEditText.setAdapter(adapter1);
        subjectEditText.setOnItemSelectedListener(this);
        /*final ArrayAdapter<String> CategoryArray= new ArrayAdapter<String>(AddExpensesActivity.this,android.R.layout.simple_spinner_item, cetarr);
        CategoryArray.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        nwsp.setAdapter(CategoryArray);*/
        addTodoBtn = (Button) findViewById(R.id.add_record);
        Log.v("beforedbManager","1");
        dbManager = new DBManager(this);
        dbManager.open();
     Intent intent = getIntent();
final int ns = intent.getIntExtra("cunt",0);
        Log.d("onCreate: ",String.valueOf(ns));
        addTodoBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(subjectEditText.getText().toString().equals(""))){ //&& !(descEditText.getText().toString().equals("")))) {

                    if ((!(uniEditText.getText().toString().equals("")) && !(pirEditText.getText().toString().equals("")))) {
                        final String name = subjectEditText.getText().toString();
                        //final String desc = "tt";// descEditText.getText().toString();
                        final String u1 = uniEditText.getText().toString();
                        final String p1 = pirEditText.getText().toString();
                        int unit = Integer.parseInt(u1);
                        int prices = Integer.parseInt(p1);
                      int newid = ns + 1;
                       dbManager.insert(newid,name, desc, unit, prices);

                        Intent main = new Intent(AddExpensesActivity.this, ExpensesListActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(main);
                    } else {
                        Toast.makeText(AddExpensesActivity.this, " plz fill all textfields ", Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(this, " plz fill all textfields ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddExpensesActivity.this, " plz fill all textfields ", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, " plz fill all textfields ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
       // Toast.makeText(this, "OnItemSelected"+position, Toast.LENGTH_SHORT).show();
      desc = label;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
