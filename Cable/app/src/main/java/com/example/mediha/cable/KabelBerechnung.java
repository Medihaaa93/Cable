package com.example.mediha.cable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class KabelBerechnung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabel_berechnung);



    }


    private void berechnungStarten(){

        Button btnStart = (Button) findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                berechnung();
            }
        });



    }

    private void berechnung(){

        EditText textLeistung = (EditText) findViewById(R.id.editLeistung);
        float leistung = Float.valueOf(textLeistung.getText().toString()); // Leistung in Meter

        EditText textLaenge = (EditText) findViewById(R.id.editLaenge);
        float laenge = Float.valueOf(textLaenge.getText().toString()); // länge in Meter

        EditText textCos = (EditText) findViewById(R.id.editCos);
        float cos = Float.valueOf(textCos.getText().toString());  // cosinus wert

        Spinner verlegeSpinner = (Spinner) findViewById(R.id.spinnerVerlege);
        int verlegepositione = verlegeSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Verlegeart

        Spinner spannungsSpinner = (Spinner) findViewById(R.id.spinner2);
        int spannungsposition = spannungsSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Spannung


    }

}
