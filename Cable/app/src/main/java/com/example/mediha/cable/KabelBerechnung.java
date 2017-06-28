package com.example.mediha.cable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class KabelBerechnung extends AppCompatActivity {

    List<Float> temp60;
    List<Float> temp70;
    List<Float> temp90;
    List<Float> haufa;
    List<Float> haufc;


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

        temp60.add(10, (float) 1.19);
        temp60.add(15, (float) 1.13);
        temp60.add(20, (float) 1.07);
        temp60.add(25, (float) 1);
        temp60.add(30, (float) 0.93);
        temp60.add(35, (float) 0.84);
        temp60.add(40, (float) 0.76);
        temp60.add(45, (float) 0.66);
        temp60.add(50, (float) 0.54);
        temp60.add(55, (float) 0.38);

        temp70.add(10, (float) 1.15);
        temp70.add(15, (float) 1.10);
        temp70.add(20, (float) 1.06);
        temp70.add(25, (float) 1);
        temp70.add(30, (float) 0.94);
        temp70.add(35, (float) 0.89);
        temp70.add(40, (float) 0.82);
        temp70.add(45, (float) 0.75);
        temp70.add(50, (float) 0.67);
        temp70.add(55, (float) 0.58);
        temp70.add(60, (float) 0.47);
        temp70.add(65, (float) 0.33);

        temp90.add(10, (float) 1.11);
        temp90.add(15, (float) 1.08);
        temp90.add(20, (float) 1.04);
        temp90.add(25, (float) 1);
        temp90.add(30, (float) 0.96);
        temp90.add(35, (float) 0.92);
        temp90.add(40, (float) 0.88);
        temp90.add(45, (float) 0.84);
        temp90.add(50, (float) 0.79);
        temp90.add(55, (float) 0.73);
        temp90.add(60, (float) 0.68);
        temp90.add(65, (float) 0.63);
        temp90.add(70, (float) 0.56);
        temp90.add(75, (float) 0.48);
        temp90.add(80, (float) 0.39);
        temp90.add(85, (float) 0.28);

        haufa.add(1, (float) 1.00);
        haufa.add(2, (float) 0.8);
        haufa.add(3, (float) 0.7);
        haufa.add(4, (float) 0.65);
        haufa.add(5, (float) 0.6);
        haufa.add(6, (float) 0.57);
        haufa.add(8, (float) 0.52);
        haufa.add(10, (float) 0.48);
        haufa.add(12, (float) 0.45);
        haufa.add(14, (float) 0.43);

        haufc.add(1, (float) 1.00);
        haufc.add(2, (float) 0.85);
        haufc.add(3, (float) 0.79);
        haufc.add(4, (float) 0.73);
        haufc.add(5, (float) 0.72);
        haufc.add(6, (float) 0.71);
        haufc.add(8, (float) 0.71);
        haufc.add(10, (float) 0.70);
        haufc.add(12, (float) 0.70);
        haufc.add(14, (float) 0.70);





    }





}
