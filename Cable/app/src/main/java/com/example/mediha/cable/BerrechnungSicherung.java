package com.example.mediha.cable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

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

        TextView textentry = (TextView) findViewById(R.id.textView10);


        List<String[]> sicherungen = new ArrayList<>();

        sicherungen.add(0, new String []{"6", "1.0"});
        sicherungen.add(1, new String []{"10", "1.0"});
        sicherungen.add(2, new String []{"13", "1.5"});
        sicherungen.add(3, new String []{"16", "2.5"});
        sicherungen.add(4, new String []{"20", "4"});
        sicherungen.add(5, new String []{"25", "6"});
        sicherungen.add(5, new String []{"32", "10"});
        sicherungen.add(6, new String []{"40", "10"});
        sicherungen.add(7, new String []{"50", "16"});
        sicherungen.add(8, new String []{"63", "25"});
        sicherungen.add(9, new String []{"80", "35"});


        double ampere = 0;
        String text;
        switch (spannungsposition) {


            case 0:
                ampere = leistung / 230;
                for (int i = 0; i<=10; i++){
                    String[] test = sicherungen.get(i);
                    if (ampere <= Double.valueOf(test[0])){
                        text = "Empfohlene Sicherung :";
                        text = text + test[0];
                        text = text + " A";
                        text = text + " Empfohlener Querschnitt :";
                        text = text + test[1];
                        text = text + " mm²";
                        textentry.setText(text);

                        break;
                    }

                }
                break;
            case 1:
                ampere = leistung / (400 * Math.sqrt(3));
                for (int i = 0; i<=10; i++){
                    String[] test = sicherungen.get(i);
                    if (ampere <= Double.valueOf(test[0])){
                        text = "Empfohlene Sicherung :";
                        text = text + test[0];
                        text = text + " A";
                        text = text + " Empfohlener Querschnitt :";
                        text = text + test[1];
                        text = text + " mm²";
                        textentry.setText(text);
                        break;
                    }
                }
                break;





        }






    }

    public void shareS(View v) {
        final Button share = (Button) findViewById(R.id.btnShare);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bitmap bitmap = getScreenShot(v);

                shareImage(bitmap);




            }
        });



    };


    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }



    public void shareImage(Bitmap bm){
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);

        File f = new File (getExternalCacheDir()+"/image.png");
        f.deleteOnExit();
        try {
            FileOutputStream outStream = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_SUBJECT, "Look this !");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        intent.setType("text/plain");

        startActivity(Intent.createChooser(intent, "Wählen"));

    }
    }

