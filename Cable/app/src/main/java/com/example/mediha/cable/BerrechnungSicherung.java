package com.example.mediha.cable;

import android.content.Intent;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class BerrechnungSicherung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berrechnung_sicherung);
    }


    public void startBtn(View view) {

        berechnung();


    }

    private void berechnung() {

        EditText textLeistung = (EditText) findViewById(R.id.editLeistung);
        float leistung = Float.valueOf(textLeistung.getText().toString()); // Leistung in Meter

        Spinner spannungsSpinner = (Spinner) findViewById(R.id.spinner2);
        int spannungsposition = spannungsSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Spannung

        Spinner querrschnittspinner = (Spinner) findViewById(R.id.spinner1) ;
        float querrschnitt = querrschnittspinner.getSelectedItemPosition();


        Intent i = new Intent(this, SendSicherung.class);
        startActivity(i);


    }
}
