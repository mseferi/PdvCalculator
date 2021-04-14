package com.example.matopdv;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matopdv.adapter.MyRecyclerView;
import com.example.matopdv.entity.CalculationEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_DATA = "KEY_DATA";
    MyRecyclerView adapter;

    private EditText etbaseNumber, etvatAmount;
    private TextView tvPlus, tvVatAmount, tvEquals;
    private Button btnCalculate;
    ArrayList<CalculationEntry> data = new ArrayList<>();

    CalculationEntry lastEntry = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        EditText etBaseNumber = findViewById(R.id.etbaseNumber);
        EditText etVat = findViewById(R.id.etVatAmount);
        Button btnCalculate = findViewById(R.id.btnCalculate);


        btnCalculate.setOnClickListener(v -> {
            //chech if etBaseNumber is empty
            if (etBaseNumber.length() == 0) {
                etBaseNumber.requestFocus();
                etBaseNumber.setError(" Molimo unesite vrijednost");
                return;

                //check if etVat is empty
            }
            if ((etVat.length() == 0)) {
                etVat.requestFocus();
                etVat.setError("Unesite iznos PDV-a");
                return;
            }


            double input = Double.parseDouble((etBaseNumber.getText().toString()));
            double vatAmount = Double.parseDouble(etVat.getText().toString());
            CalculationEntry entry = new CalculationEntry(input, vatAmount, "ss");

            if (lastEntry != null && lastEntry.getBaseNumber() == entry.getBaseNumber() && lastEntry.getVatAmount() == entry.getVatAmount()) {
                etBaseNumber.requestFocus();
                etBaseNumber.setError("Unesite drugu vrijednost");
                return;
            }


            adapter.getmData().add(0, entry); //add it to top of list
            adapter.notifyDataSetChanged();
            lastEntry = entry;

            //Hides keyboard on button click
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(btnCalculate.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

        });


        adapter = new MyRecyclerView(this);
        adapter.setmData(data);
        RecyclerView rvData = findViewById(R.id.rV);
        rvData.setAdapter(adapter);
        rvData.setLayoutManager(new LinearLayoutManager(this));
    }


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(btnCalculate.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
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
        Type type = new TypeToken<ArrayList<CalculationEntry>>() {
        }.getType();
        data = gson.fromJson(json, type);
        if (data == null) {
            data = new ArrayList<>();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveData();
        outState.putSerializable(KEY_DATA, lastEntry);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastEntry = (CalculationEntry) savedInstanceState.getSerializable(KEY_DATA);
    }
}
