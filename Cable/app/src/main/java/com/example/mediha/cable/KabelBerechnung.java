package com.example.mediha.cable;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.delay;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.example.mediha.cable.R.array.querschnitt;

public class KabelBerechnung extends AppCompatActivity {

    List<Float> temp60;
    List<Float> temp70;
    List<Double> temp90 = new ArrayList<>();
    List<Double> haufa = new ArrayList<>();
    List<Double> haufc = new ArrayList<>();
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


    public void berechnen(View view) {


        final Button shareKa = (Button) findViewById(R.id.buttonShare);
        TextView showAmpere = (TextView)findViewById(R.id.textView10);

        final Button btnStart = (Button) findViewById(R.id.buttonStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                berechnung();
                shareKa.setVisibility(VISIBLE);
            }


        });
    }






    private void berechnung() {

        TextView showAmpere = (TextView) findViewById(R.id.textView10);


        EditText textLeistung = (EditText) findViewById(R.id.editLeistung);
        double leistung = Float.valueOf(textLeistung.getText().toString()); // Leistung in Meter

        EditText textLaenge = (EditText) findViewById(R.id.editLaenge);
        double laenge = Float.valueOf(textLaenge.getText().toString()); // länge in Meter

        EditText textCos = (EditText) findViewById(R.id.editCos);
        double cos = Float.valueOf(textCos.getText().toString());  // cosinus wert

        Spinner verlegeSpinner = (Spinner) findViewById(R.id.spinnerVerlege);
        int verlegeposition = verlegeSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Verlegeart

        Spinner haufSpinner = (Spinner) findViewById(R.id.spinnerHauf);
        int haufposition = haufSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Verlegeart

        Spinner tempSpinner = (Spinner) findViewById(R.id.spinnerTemp);
        int tempposition = tempSpinner.getSelectedItemPosition();

        Spinner spannungsSpinner = (Spinner) findViewById(R.id.spinner2);
        int spannungsposition = spannungsSpinner.getSelectedItemPosition();  // Hier wissen wir was ausgewählt wurde Spannung

        Double amp = 0.0;
        Double volt = 0.0;
        Double quer0 = 0.0;

        switch (spannungsposition) {

            case 0:
                volt = 230.0;
                break;

            case 1:
                volt = 400.0;
                break;

        }


        temp90.add(0, 1.11);
        temp90.add(1, 1.08);
        temp90.add(2, 1.04);
        temp90.add(3, 1.0);
        temp90.add(4, 0.96);
        temp90.add(5, 0.92);
        temp90.add(6, 0.88);
        temp90.add(7, 0.84);
        temp90.add(8, 0.79);
        temp90.add(9, 0.73);
        temp90.add(10, 0.68);
        temp90.add(11, 0.63);
        temp90.add(12, 0.56);
        temp90.add(13, 0.48);
        temp90.add(14, 0.39);
        temp90.add(15, 0.28);

        haufa.add(0,  1.00);
        haufa.add(1,  0.8);
        haufa.add(2,  0.7);
        haufa.add(3,  0.65);
        haufa.add(4,  0.6);
        haufa.add(5,  0.57);
        haufa.add(6,  0.52);
        haufa.add(7,  0.48);
        haufa.add(8,  0.45);
        haufa.add(9,  0.43);

        haufc.add(0,  1.00);
        haufc.add(1,  0.85);
        haufc.add(2,  0.79);
        haufc.add(3,  0.73);
        haufc.add(4,  0.72);
        haufc.add(5,  0.71);
        haufc.add(6,  0.71);
        haufc.add(7,  0.70);
        haufc.add(8,  0.70);
        haufc.add(9,  0.70);


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

                    for (int i = 0; i < 10; i++) {

                        String[] test01 = bemessA12.get(i);
                        if (amp <= Double.valueOf(test01[0])) {
                            amp2 = Integer.valueOf(test01[0]);
                            quer0 = Double.valueOf(test01[1]);

                            break;
                        }
                        else if(amp > 25){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 10; i++) {

                        String[] test02 = bemessA13.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 80){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }


                    }

                }

            case 1:


                if (volt == 230) {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessA22.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 25){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessA23.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 80){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }


                    }

                }


            case 2:


                if (volt == 230) {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessB12.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 25){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessB13.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 100){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }
                    }

                }

            case 3:


                if (volt == 230) {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessB22.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 25){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessB23.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 100){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }
                    }

                }

            case 4:



                if (volt == 230) {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessC12.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 35){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        String[] test02 = bemessC13.get(i);
                        if (amp <= Double.valueOf(test02[0])) {
                            amp2 = Integer.valueOf(test02[0]);
                            quer0 = Double.valueOf(test02[1]);

                            break;
                        }
                        else if(amp > 125){

                            showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                            break;
                        }
                    }

                }

        }



        // Berechnung mit Faktoren

        Double amp3 = 0.0;
        Double tempfaktor = Double.valueOf(temp90.get(tempposition));
        Double faktorhauf = 0.0;

        switch (verlegeposition) {

            case 0:

                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 /( tempfaktor * faktorhauf));

            case 1:

                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 /( tempfaktor * faktorhauf));
            case 2:

                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 /( tempfaktor * faktorhauf));
            case 3:
                faktorhauf = haufa.get(haufposition);
                amp3 = (amp2 /( tempfaktor * faktorhauf));
            case 4:
                faktorhauf = haufc.get(haufposition);
                amp3 = (amp2 /( tempfaktor * faktorhauf));


        }

        switch (verlegeposition){

        case 0:

        if (volt == 230) {

            for (int i = 0; i < 10; i++) {

                String[] test01 = bemessA12.get(i);
                if (amp3 <= Double.valueOf(test01[0])) {
                    quer0 = Double.valueOf(test01[1]);

                    break;
                }
                else if(amp > 25){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }

            }
        } else {
            for (int i = 0; i < 10; i++) {

                String[] test02 = bemessA13.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                }else if(amp > 80){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }


            }

        }

        case 1:


        if (volt == 230) {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessA22.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                } else if(amp > 25){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }


            }
        } else {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessA23.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                }else if(amp > 80){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }


            }

        }


        case 2:


        if (volt == 230) {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessB12.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                } else if(amp > 25){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }


            }
        } else {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessB13.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                }else if(amp > 100){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }

            }

        }

        case 3:


        if (volt == 230) {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessB22.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                } else if(amp > 25){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }


            }
        } else {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessB23.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                }else if(amp > 100){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }

            }

        }

        case 4:



        if (volt == 230) {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessC12.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                } else if(amp > 35){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }


            }
        } else {
            for (int i = 0; i < 10; i++) {
                String[] test02 = bemessC13.get(i);
                if (amp3 <= Double.valueOf(test02[0])) {
                    quer0 = Double.valueOf(test02[1]);

                    break;
                }else if(amp > 125){

                    showAmpere.setText("Zu Hoher Strom für diese Verlegemethode");
                    break;
                }

            }

        }

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
        }

        else {

                    showAmpere.setText("Leider sind wir auf kein Ergebnis gekommen");
        }




        //Intent i = new Intent(this, SendKabel.class);
        //startActivity(i);

        showAmpere.setText(querschnitt);

        showAmpere.setVisibility(View.VISIBLE);






    }


    public void shareK(View view) {
        Bitmap test = viewToBitmap(view);
        Button shareKa = (Button) findViewById(R.id.buttonShare) ;

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

        view.destroyDrawingCache();
        //shareKa.setVisibility(INVISIBLE);
    finish();

    }

    public Bitmap viewToBitmap(View view) {

        view = view.getRootView();
        LinearLayout view1 = (LinearLayout) view.findViewById(R.id.kabelLayout1);
        view1.setDrawingCacheEnabled(true);
        view1.buildDrawingCache();

        Bitmap bm = view1.getDrawingCache();


        return bm;

    }



}
