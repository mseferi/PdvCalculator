package com.example.matopdv;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";
    MyRecyclerView adapter;

    private EditText etbaseNumber, etvatAmount;
    private TextView tvPlus, tvPosto, tvJednako;
    private Button btnIzracunaj;
    ArrayList<CalculationEntry> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        EditText etBroj = findViewById(R.id.etbaseNumber);
        EditText etPdv = findViewById(R.id.etvatAmount);
        TextView tvPlus = findViewById(R.id.tvPlus);
        TextView tvPosto = findViewById(R.id.tvPosto);
        TextView tvJednako = findViewById(R.id.tvJednako);
        Button btnIzracunaj = findViewById(R.id.btnIzracunaj);




        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DATA)) {
            data = (ArrayList<CalculationEntry>) savedInstanceState.getSerializable(KEY_DATA);
        } else {
            btnIzracunaj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (etBroj.length() == 0) {
                        etBroj.requestFocus();
                        etBroj.setError(" Molimo unesite vrijednost");
                    } else if ((etPdv.length() == 0)) {
                        etPdv.requestFocus();
                        etPdv.setError("Unesite iznos PDV-a");


                    } else {
                        double input = Double.parseDouble((etBroj.getText().toString()));
                        double vatAmount = Double.parseDouble(etPdv.getText().toString());
                        double pdvPosto = vatAmount / 100;
                        CalculationEntry entry = new CalculationEntry(input, vatAmount, "ss");
                        adapter.getmData().add(0,entry); //dodaje na vrh liste
                        adapter.notifyDataSetChanged();



                    }


                    //Hides keyboard on button click
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(btnIzracunaj.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                }
            });




        }




        adapter = new MyRecyclerView(this);
        adapter.setmData(data);
        RecyclerView rvData = findViewById(R.id.rV);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));


    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<CalculationEntry>>() {}.getType();
        data = gson.fromJson(json, type);
        if (data == null) {
            data = new ArrayList<>();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY_DATA, (Serializable) adapter.getmData());
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }
}
