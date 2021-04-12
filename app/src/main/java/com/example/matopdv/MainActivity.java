package com.example.matopdv;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";
    MyRecyclerView adapter;

    private EditText etBroj, etPdv;
    private TextView tvPlus, tvPosto, tvJednako;
    private Button btnIzracunaj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etBroj = findViewById(R.id.etBroj);
        EditText etPdv = findViewById(R.id.etPdv);
        TextView tvPlus = findViewById(R.id.tvPlus);
        TextView tvPosto = findViewById(R.id.tvPosto);
        TextView tvJednako = findViewById(R.id.tvJednako);
        Button btnIzracunaj = findViewById(R.id.btnIzracunaj);


        ArrayList<ListItem> data = new ArrayList<>();

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DATA)) {
            data = (ArrayList<ListItem>) savedInstanceState.getSerializable(KEY_DATA);
        } else {
            data.add(new ListItem("", "", "", "", "", "", "", "", "", ""));

        }

        adapter = new MyRecyclerView(this);
        adapter.setmData(data);
        RecyclerView rvData = findViewById(R.id.rV);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_DATA, (Serializable) adapter.getmData());
    }
}
