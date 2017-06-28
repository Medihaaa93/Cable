package com.example.mediha.cable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ConverterWatToAmpere extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_wat_to_ampere);
    }

    public void convertIt(View view) {

        berrechnungConvert();

    }

    private void berrechnungConvert(){
        Spinner spannungSpinner = (Spinner) findViewById(R.id.spinner2);
        int spannung = spannungSpinner.getSelectedItemPosition();

        EditText leistungFeld = (EditText) findViewById(R.id.editLeistung);
        float leistung = Float.valueOf(leistungFeld.getText().toString());

        float ampere= spannung/leistung;
        String text = String.valueOf(ampere);

        TextView showAmpere = (TextView) findViewById(R.id.textView10);
        showAmpere.setText(text);

        showAmpere.setVisibility(View.VISIBLE);
    }

}
