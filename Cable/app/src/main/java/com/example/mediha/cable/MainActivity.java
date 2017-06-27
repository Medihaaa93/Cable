package com.example.mediha.cable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.attr.id;
import static android.R.attr.scrollbarDefaultDelayBeforeFade;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        berrechnungSicherungIntent();
        berechnungKabel();
        impressumBtn();

    }

    public void berrechnungSicherungIntent() { //Sicherung ist ImageView4
        ImageView sicherung = (ImageView) findViewById(R.id.imageView4);
        sicherung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BerrechnungSicherung.class);
                startActivity(i);
            }
        });

    }

    public void berechnungKabel() { //Kable ist ImageView3
        ImageView sicherung = (ImageView) findViewById(R.id.imageView3);
        sicherung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), KabelBerechnung.class);
                startActivity(i);
            }
        });

    }


    public void impressumBtn() {

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Impressum.class);
                startActivity(i);
            }
        });
    }
}