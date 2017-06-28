package com.example.mediha.cable;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
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

import static android.R.attr.delay;
import static com.example.mediha.cable.R.array.querschnitt;

public class KabelBerechnung extends AppCompatActivity {

    List<Float> temp60;
    List<Float> temp70;
    List<Float> temp90 = new ArrayList<>();
    List<Float> haufa = new ArrayList<>();
    List<Float> haufc = new ArrayList<>();
    List<String[]> bemessA12 = new ArrayList<>();
    List<String[]> bemessA13 = new ArrayList<>();
    List<String[]> bemessA22 = new ArrayList<>();
    List<String[]> bemessA23 = new ArrayList<>();
    List<String[]> bemessB12 = new ArrayList<>();
    List<String[]> bemessB13 = new ArrayList<>();
    List<String[]> bemessB22 = new ArrayList<>();
    List<String[]> bemessB23 = new ArrayList<>();
    List<String[]> bemessC12 = new ArrayList<>();
    List<String[]> bemessC13 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabel_berechnung);



    }

    public void berechnen(final View view) {

        final Button btnStart = (Button) findViewById(R.id.buttonStart);
        final TextView showAmpere = (TextView) findViewById(R.id.textView10);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                berechnung();
                btnStart.setVisibility(View.INVISIBLE);

                           }
        });
    }

    public void share(final View view) {

        final Button share = (Button) findViewById(R.id.buttonShare);
        final TextView showAmpere = (TextView) findViewById(R.id.textView10);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                share.setVisibility(View.INVISIBLE);

                takepic(view);
            }
        });
    }

    private void berechnung() {

        EditText textLeistung = (EditText) findViewById(R.id.editLeistung);
        float leistung = Float.valueOf(textLeistung.getText().toString()); // Leistung in Meter

        EditText textLaenge = (EditText) findViewById(R.id.editLaenge);
        float laenge = Float.valueOf(textLaenge.getText().toString()); // länge in Meter

        EditText textCos = (EditText) findViewById(R.id.editCos);
        float cos = Float.valueOf(textCos.getText().toString());  // cosinus wert

        Spinner verlegeSpinner = (Spinner) findViewById(R.id.spinnerVerlege);
        int verlegeposition = verlegeSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Verlegeart

        Spinner haufSpinner = (Spinner) findViewById(R.id.spinnerHauf);
        int haufposition = verlegeSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Verlegeart

        Spinner tempSpinner = (Spinner) findViewById(R.id.spinnerTemp);
        int tempposition = tempSpinner.getSelectedItemPosition();

        Spinner spannungsSpinner = (Spinner) findViewById(R.id.spinner2);
        int spannungsposition = spannungsSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Spannung

        double amp = 0;
        float volt = 0;
        Float quer0 = Float.valueOf(0);

        switch (spannungsposition) {

            case 0:
                volt = 230;
                break;

            case 1:
                volt = 400;
                break;

        }


        // Index = Temperatur --- Wert = Faktor
/*
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

*/
        temp90.add(0, (float) 1.11);
        temp90.add(1, (float) 1.08);
        temp90.add(2, (float) 1.04);
        temp90.add(3, (float) 1);
        temp90.add(4, (float) 0.96);
        temp90.add(5, (float) 0.92);
        temp90.add(6, (float) 0.88);
        temp90.add(7, (float) 0.84);
        temp90.add(8, (float) 0.79);
        temp90.add(9, (float) 0.73);
        temp90.add(10, (float) 0.68);
        temp90.add(11, (float) 0.63);
        temp90.add(12, (float) 0.56);
        temp90.add(13, (float) 0.48);
        temp90.add(14, (float) 0.39);
        temp90.add(15, (float) 0.28);

        int[] arrtemp = {10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85};

        // index = Anzah der Kabel  --- Wert = Faktor

        int[] arrhauf = {1, 2, 3, 4, 5, 6, 8, 10, 12, 14};

        haufa.add(0, (float) 1.00);
        haufa.add(1, (float) 0.8);
        haufa.add(2, (float) 0.7);
        haufa.add(3, (float) 0.65);
        haufa.add(4, (float) 0.6);
        haufa.add(5, (float) 0.57);
        haufa.add(6, (float) 0.52);
        haufa.add(7, (float) 0.48);
        haufa.add(8, (float) 0.45);
        haufa.add(9, (float) 0.43);

        haufc.add(0, (float) 1.00);
        haufc.add(1, (float) 0.85);
        haufc.add(2, (float) 0.79);
        haufc.add(3, (float) 0.73);
        haufc.add(4, (float) 0.72);
        haufc.add(5, (float) 0.71);
        haufc.add(6, (float) 0.71);
        haufc.add(7, (float) 0.70);
        haufc.add(8, (float) 0.70);
        haufc.add(9, (float) 0.70);


        // Index = Bemessungsstrom --- Wert = Querschnitt

        bemessA12.add(0, new String []{"13", "1.5"});
        bemessA12.add(1, new String []{"20", "2.5"});
        bemessA12.add(2, new String []{"25", "4"});

        bemessA13.add(0, new String []{"13", "1.5"});
        bemessA13.add(1, new String []{"16", "2.5"});
        bemessA13.add(2, new String []{"25", "4"});
        bemessA13.add(3, new String []{"25", "6"});
        bemessA13.add(4, new String []{"40", "10"});
        bemessA13.add(5, new String []{"50", "16"});
        bemessA13.add(6, new String []{"63", "25"});
        bemessA13.add(7, new String []{"80", "35"});

        bemessA22.add(0, new String []{"13", "1.5"});
        bemessA22.add(1, new String []{"16", "2.5"});
        bemessA22.add(2, new String []{"25", "4"});


        bemessA23.add(0, new String []{"13" ,"1.5"});
        bemessA23.add(1, new String []{"16" ,"2.5"});
        bemessA23.add(2, new String []{"20" ,"4"});
        bemessA23.add(3, new String []{"25" ,"6"});
        bemessA23.add(4, new String []{"35" ,"10"});
        bemessA23.add(5, new String []{"50" ,"16"});
        bemessA23.add(6, new String []{"63" ,"25"});
        bemessA23.add(7, new String []{"80" ,"35"});


        bemessB12.add(0, new String []{"16", "1.5"});
        bemessB12.add(1, new String []{"25", "2.5"});
        bemessB12.add(2, new String []{"25", "4"});

        bemessB13.add(0, new String []{"16", "1.5"});
        bemessB13.add(1, new String []{"20", "2.5"});
        bemessB13.add(2, new String []{"25", "4"});
        bemessB13.add(3, new String []{"35", "6"});
        bemessB13.add(4, new String []{"50", "10"});
        bemessB13.add(5, new String []{"63", "16"});
        bemessB13.add(6, new String []{"80", "25"});
        bemessB13.add(7, new String []{"100", "35"});


        bemessB22.add(0, new String []{"16", "1.5"});
        bemessB22.add(1, new String []{"20", "2.5"});
        bemessB22.add(2, new String []{"25", "4"});

        bemessB23.add(0, new String []{"16", "1.5"});
        bemessB23.add(1, new String []{"20", "2.5"});
        bemessB23.add(2, new String []{"25", "4"});
        bemessB23.add(3, new String []{"35", "6"});
        bemessB23.add(4, new String []{"50", "10"});
        bemessB23.add(5, new String []{"63", "16"});
        bemessB23.add(6, new String []{"80", "25"});
        bemessB23.add(7, new String []{"100", "35"});


        bemessC12.add( 0, new String [] {"16", "1.5"});
        bemessC12.add( 1, new String [] {"25", "2.5"});
        bemessC12.add( 2, new String [] {"35", "4"});

        bemessC13.add( 0, new String [] {"16", "1.5"});
        bemessC13.add( 1, new String [] {"25", "2.5"});
        bemessC13.add( 2, new String [] {"35", "4"});
        bemessC13.add( 3, new String [] {"40", "6"});
        bemessC13.add( 4, new String [] {"63", "10"});
        bemessC13.add( 5, new String [] {"80", "16"});
        bemessC13.add( 6, new String [] {"100", "25"});
        bemessC13.add( 7, new String [] {"125", "35"});


        // Anfang der Berechnung


        // Watt in Amp

        double sqrt = Math.sqrt(3);

        if (volt == 230) {
            amp = (leistung / (volt * cos));
        } else {
            amp = (leistung / (volt * cos * sqrt));
        }

        int amp2 = 0;
        switch (verlegeposition) {

            case 0:


                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessA12.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessA12.get(i)[0]);
                            quer0 = Float.valueOf(bemessA12.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessA13.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessA13.get(i)[0]);
                            quer0 = Float.valueOf(bemessA13.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }


                    }

                }

            case 1:


                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessA22.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessA22.get(i)[0]);
                            quer0 = Float.valueOf(bemessA22.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessA23.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessA23.get(i)[0]);
                            quer0 = Float.valueOf(bemessA23.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }


                    }

                }


            case 2:


                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessB12.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessB12.get(i)[0]);
                            quer0 = Float.valueOf(bemessB12.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessB13.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessB13.get(i)[0]);
                            quer0 = Float.valueOf(bemessB13.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }
                    }

                }

            case 3:


                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessB22.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessB22.get(i)[0]);
                            quer0 = Float.valueOf(bemessB22.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessB23.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessB23.get(i)[0]);
                            quer0 = Float.valueOf(bemessB23.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }
                    }

                }

            case 4:



                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessC12.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessC12.get(i)[0]);
                            quer0 = Float.valueOf(bemessC12.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= Integer.valueOf(bemessC13.get(i)[0])) {
                            amp2 = Integer.valueOf(bemessC13.get(i)[0]);
                            quer0 = Float.valueOf(bemessC13.get(i)[1]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }
                    }

                }

        }



        // Berechnung mit Faktoren

        float amp3 = 0;
        float tempfaktor = temp90.get(tempposition);
        float faktorhauf = 0;

        switch (verlegeposition) {

            case 0:

                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 * tempfaktor * faktorhauf);

            case 1:

                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 * tempfaktor * faktorhauf);
            case 2:

                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 * tempfaktor * faktorhauf);
            case 3:
                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 * tempfaktor * faktorhauf);
            case 4:
                faktorhauf = haufc.get(haufposition);
                amp3 = (amp2 * tempfaktor * faktorhauf);


        }


        String text00 = null;
        String querschnitt = null;
        if (amp <= amp2 && amp2 <= amp3) {

            double deltaU = 0;
            double flaeche = 0;
            flaeche = ((quer0 * quer0 * 3.14159265359) / 4);

            deltaU = ((2 * laenge * amp * cos) / (58000000 * flaeche));
            text00 = String.valueOf(deltaU);
            querschnitt = "Querschnitt =";
            querschnitt = querschnitt + String.valueOf(quer0);
            querschnitt = querschnitt + "mm²";
        } else {

            Toast.makeText(new KabelBerechnung(), "Leider sind wir auf kein Ergebnis gekommen",
                    Toast.LENGTH_LONG).show();
        }




        //Intent i = new Intent(this, SendKabel.class);
        //startActivity(i);
        TextView showAmpere = (TextView) findViewById(R.id.textView10);
        showAmpere.setText(querschnitt);

       // showAmpere.setVisibility(View.VISIBLE);






    }

    public void takepic(View v){

        Bitmap test = viewToBitmap(v);

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


    };


    public Bitmap viewToBitmap(View view) {
        view = view.getRootView();
        LinearLayout view1 = (LinearLayout) view.findViewById(R.id.linearlayout);
        view1.setDrawingCacheEnabled(true);
        view1.buildDrawingCache();
        view1.setBackgroundColor(Color.rgb(238,238,238));
        Bitmap bm = view1.getDrawingCache();

        return bm;
    }
}
