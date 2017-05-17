package com.mohan.caliber.appcracker;

/**
 *  Created by mohan on 13/05/17.
 */
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifyExpensesActivity extends Activity implements OnClickListener,AdapterView.OnItemSelectedListener {

    private EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;
    private  EditText edun,edpr;
    Spinner edcat;
    AutoCompleteTextView edit;
    String[] cetarr = new String[]{"Vegetables","Fruits","Transport","Other"};

    private int _id;
    String category;
    private DBManager dbManager;
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_record);

        dbManager = new DBManager(this);
        dbManager.open();
        edit =(AutoCompleteTextView) findViewById(R.id.item_edittext);
        edcat =(Spinner) findViewById(R.id.category_edittext);
        edun =(EditText)findViewById(R.id.unit_edittext);
        edpr =(EditText)findViewById(R.id.price_edittext);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_dropdown_item_1line,cetarr);
        edcat.setAdapter(adapter);
        edcat.setOnItemSelectedListener(this);
        String[] item = getResources().getStringArray(R.array.list_of_item);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this,android.R.layout.simple_dropdown_item_1line,item);
        edit.setAdapter(adapter1);
        edit.setOnItemSelectedListener(this);
        //titleText = (EditText) findViewById(R.id.subject_edittext);
      //  descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        String u = intent.getStringExtra("uniz");
        String p = intent.getStringExtra("pricez");
        count = intent.getIntExtra("count",0);
        _id = (int) Long.parseLong(id);
        edit.setText(name);
        if(desc.equals("Vegetables")){
            edcat.setSelection(0);
        }else if (desc.equals("Fruits")) {
            edcat.setSelection(1);

        }else if (desc.equals("Transport")) {
            edcat.setSelection(2);

        }else if (desc.equals("Other")) {
            edcat.setSelection(3);

        }

        edun.setText(u);
        edpr.setText(p);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:

                Log.i("onClick: ",String.valueOf(count));
                if((!(edit.getText().toString().equals(""))&&!(edun.getText().toString().equals("")))&&!(edpr.getText().toString().equals(""))){
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(ModifyExpensesActivity.this);
                dialog1.setCancelable(false);
                dialog1.setTitle("Modify Data Sheet");
                dialog1.setMessage("Are you sure you want to Update this entry?" );
                dialog1.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        String item = edit.getText().toString();
                        //String category = edcat.getText().toString();
                        int unit = Integer.parseInt(edun.getText().toString());
                        int price = Integer.parseInt(edpr.getText().toString());

                        dbManager.update(_id, item, category,unit,price);
                        this.returnHome();
                    }

                    private void returnHome() {
                        Intent home_intent = new Intent(getApplicationContext(), ExpensesListActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(home_intent);
                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }

                        });

                final AlertDialog alert1 = dialog1.create();
                alert1.show();}else {
                Toast.makeText(ModifyExpensesActivity.this, "Please fill all Text fields", Toast.LENGTH_SHORT).show();
            }

                break;

            case R.id.btn_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(ModifyExpensesActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Modify Data Sheet");
                dialog.setMessage("Are you sure you want to delete this entry?" );
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        dbManager.delete(_id,count);
                        this.returnHome();
                    }

                    private void returnHome() {
                        Intent home_intent = new Intent(getApplicationContext(), ExpensesListActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(home_intent);
                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }

                        });

                final AlertDialog alert = dialog.create();
                alert.show();

                /*dbManager.delete(_id,count);
                this.returnHome();*/
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ExpensesListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();
     // Toast.makeText(this, "OnItemSelected"+position, Toast.LENGTH_SHORT).show();
        category = label;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
