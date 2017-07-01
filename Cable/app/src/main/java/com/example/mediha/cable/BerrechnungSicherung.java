package com.example.mediha.cable;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class BerrechnungSicherung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berrechnung_sicherung);
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
    }


    public void startBtn(View view) {

        berechnung();


    }

    private void berechnung() {
        Button share = (Button) findViewById(R.id.btnShare);
        share.setVisibility(VISIBLE);

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


    public void shareS(View view) {


        Button share = (Button) findViewById(R.id.btnShare);

        Bitmap test = viewToBitmap(view);

        File f = new File (getExternalCacheDir()+"/image.png");
        try {
            FileOutputStream outStream = new FileOutputStream(f);
            test.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Look this !");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        intent.setType("text/plain");

        startActivity(Intent.createChooser(intent, "Wählen"));
       // f.deleteOnExit();
        view.destroyDrawingCache();

        share.setVisibility(INVISIBLE);




    }

    public Bitmap viewToBitmap(View view) {

            view = view.getRootView();
            LinearLayout view1 = (LinearLayout) view.findViewById(R.id.sicherungLayout1 );
            view1.setDrawingCacheEnabled(true);
            view1.buildDrawingCache();

            Bitmap bm = view1.getDrawingCache();

            return bm;


    }

}

