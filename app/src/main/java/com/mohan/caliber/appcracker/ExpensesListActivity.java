package com.mohan.caliber.appcracker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpensesListActivity extends ActionBarActivity {

    private DBManager dbManager;
    Button bt;
    private Context context;
    private ListView listView;
    FloatingActionButton f1;
    private DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
    private SimpleCursorAdapter adapter2;
    private SQLiteDatabase db;
final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.ITEM,DatabaseHelper.CATEGORY, DatabaseHelper.UNIT, DatabaseHelper.PRICE };
    final int[] to = new int[] { R.id.id, R.id.title, R.id.desc ,R.id.un, R.id.pi};
    int cnt;
    TextView ntx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        ntx = (TextView)findViewById(R.id.exp);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));
        f1 = (FloatingActionButton)findViewById(R.id.addbut);
/*http://stackoverflow.com/questions/36279878/calculate-sum-of-column-in-sqlite-android*/

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to,0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
        dbManager.read();
        Cursor cursor1 = dbManager.sum();
        cnt = cursor.getCount();
        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(ExpensesListActivity.this, AddExpensesActivity.class);
                add_mem.putExtra("cunt",cnt);
                startActivity(add_mem);
            }
        });
        Log.v("Row Count", String.valueOf(cnt));
        String h = String.valueOf(cursor1.getInt(0));
        ntx.setText(h);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);
                TextView descTextView = (TextView) view.findViewById(R.id.desc);
                TextView unitTextView = (TextView) view.findViewById(R.id.un);
                TextView priceTextView = (TextView) view.findViewById(R.id.pi);
                String id = idTextView.getText().toString();
                String title = titleTextView.getText().toString();
                String desc = descTextView.getText().toString();
                String unit = unitTextView.getText().toString();
                String price = priceTextView.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(), ModifyExpensesActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("uniz",unit);
                modify_intent.putExtra("count",cnt);
                modify_intent.putExtra("pricez",price);
                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_dropdown1:
                Cursor cursor = dbManager.fetch();
                Log.v("Onclick******",cursor.toString());
                adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Cursor cursor2  = dbManager.sum();
                String total = String.valueOf(cursor2.getInt(0));
                ntx.setText(total);
                break;
            case R.id.action_dropdown2:
                Cursor cursor3 = dbManager.setlist();
                Log.v("Onclick******",cursor3.toString());
                adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor3, from, to, 0);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Cursor cursor4  = dbManager.select();
                String total2 = String.valueOf(cursor4.getInt(0));
                ntx.setText(total2);
                break;
         case R.id.action_dropdown3:
                Cursor cursor5 = dbManager.setlist2();
                Log.v("Onclick******",cursor5.toString());
                adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor5, from, to, 0);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Cursor cursor6  = dbManager.select2();
                String total3 = String.valueOf(cursor6.getInt(0));
                ntx.setText(total3);
             break;
             case R.id.action_dropdown4:
                Cursor cursor7 = dbManager.setlist3();
                Log.v("Onclick******",cursor7.toString());
                adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor7, from, to, 0);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Cursor cursor8  = dbManager.select3();
                String total4 = String.valueOf(cursor8.getInt(0));
                ntx.setText(total4);
                 break;
            case R.id.action_dropdown5:
                Cursor cursor9 = dbManager.setlist4();
                Log.v("Onclick******",cursor9.toString());
                adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor9, from, to, 0);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Cursor cursor10  = dbManager.select4();
                String total5 = String.valueOf(cursor10.getInt(0));
                ntx.setText(total5);
                 break;

        }
        return super.onOptionsItemSelected(item);

    }

}