package com.example.mediha.cable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import static android.R.attr.data;

public class KabelBerechnung extends AppCompatActivity {

    List<Float> temp60;
    List<Float> temp70;
    List<Float> temp90;
    List<Float> haufa;
    List<Float> haufc;
    List<Float> bemessA12;
    List<Float> bemessA13;
    List<Float> bemessA22;
    List<Float> bemessA23;
    List<Float> bemessB12;
    List<Float> bemessB13;
    List<Float> bemessB22;
    List<Float> bemessB23;
    List<Float> bemessC12;
    List<Float> bemessC13;


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
        double quer0 = 0;

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

        int[] arrtemp = {10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85};

        // index = Anzah der Kabel  --- Wert = Faktor

        int[] arrhauf = {1, 2, 3, 4, 5, 6, 8, 10, 12, 14};

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


        // Index = Bemessungsstrom --- Wert = Querschnitt

        bemessA12.add(13, (float) 1.5);
        bemessA12.add(20, (float) 2.5);
        bemessA12.add(25, (float) 4);

        bemessA13.add(13, (float) 1.5);
        bemessA13.add(16, (float) 2.5);
        bemessA13.add(25, (float) 4);
        bemessA13.add(25, (float) 6);
        bemessA13.add(40, (float) 10);
        bemessA13.add(50, (float) 16);
        bemessA13.add(63, (float) 25);
        bemessA13.add(80, (float) 35);


        bemessA22.add(13, (float) 1.5);
        bemessA22.add(16, (float) 2.5);
        bemessA22.add(25, (float) 4);

        bemessA23.add(13, (float) 1.5);
        bemessA23.add(16, (float) 2.5);
        bemessA23.add(20, (float) 4);
        bemessA23.add(25, (float) 6);
        bemessA23.add(35, (float) 10);
        bemessA23.add(50, (float) 16);
        bemessA23.add(63, (float) 25);
        bemessA23.add(80, (float) 35);


        bemessB12.add(16, (float) 1.5);
        bemessB12.add(25, (float) 2.5);
        bemessB12.add(25, (float) 4);

        bemessB13.add(16, (float) 1.5);
        bemessB13.add(20, (float) 2.5);
        bemessB13.add(25, (float) 4);
        bemessB13.add(35, (float) 6);
        bemessB13.add(50, (float) 10);
        bemessB13.add(63, (float) 16);
        bemessB13.add(80, (float) 25);
        bemessB13.add(100, (float) 35);


        bemessB22.add(16, (float) 1.5);
        bemessB22.add(20, (float) 2.5);
        bemessB22.add(25, (float) 4);

        bemessB23.add(16, (float) 1.5);
        bemessB23.add(20, (float) 2.5);
        bemessB23.add(25, (float) 4);
        bemessB23.add(35, (float) 6);
        bemessB23.add(50, (float) 10);
        bemessB23.add(63, (float) 16);
        bemessB23.add(80, (float) 25);
        bemessB23.add(100, (float) 35);


        bemessC12.add(16, (float) 1.5);
        bemessC12.add(25, (float) 2.5);
        bemessC12.add(35, (float) 4);

        bemessC13.add(16, (float) 1.5);
        bemessC13.add(25, (float) 2.5);
        bemessC13.add(35, (float) 4);
        bemessC13.add(40, (float) 6);
        bemessC13.add(63, (float) 10);
        bemessC13.add(80, (float) 16);
        bemessC13.add(100, (float) 25);
        bemessC13.add(125, (float) 35);


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

                int[] arrA12 = {13, 20, 25};
                int[] arrA13 = {13, 16, 20, 25, 40, 50, 63, 80};
                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrA12[i]) {
                            amp2 = arrA12[i];
                            quer0 = bemessA12.get(arrA12[i]);

                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();

                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrA13[i]) {
                            amp2 = arrA13[i];
                            quer0 = bemessA13.get(arrA13[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }


                    }

                }

            case 1:

                int[] arrA22 = {13, 16, 25};
                int[] arrA23 = {13, 16, 20, 25, 35, 50, 63, 80};
                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrA22[i]) {
                            amp2 = arrA22[i];
                            quer0 = bemessA22.get(arrA22[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrA23[i]) {
                            amp2 = arrA23[i];
                            quer0 = bemessA23.get(arrA23[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }


                    }

                }


            case 2:

                int[] arrB12 = {16, 20, 25};
                int[] arrB13 = {16, 20, 25, 35, 50, 63, 80, 100};
                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrB12[i]) {
                            amp2 = arrB12[i];
                            quer0 = bemessB12.get(arrB12[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrB13[i]) {
                            amp2 = arrB13[i];
                            quer0 = bemessB13.get(arrB13[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                }

            case 3:

                int[] arrB22 = {16, 20, 25};
                int[] arrB23 = {16, 20, 25, 35, 50, 63, 80, 100};
                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrB22[i]) {
                            amp2 = arrB22[i];
                            quer0 = bemessB22.get(arrB22[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrB23[i]) {
                            amp2 = arrB23[i];
                            quer0 = bemessB23.get(arrB23[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }
                    }

                }

            case 4:


                int[] arrC12 = {16, 20, 25};
                int[] arrC13 = {16, 20, 25, 35, 50, 63, 80, 100};
                if (volt == 230) {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrC12[i]) {
                            amp2 = arrC12[i];
                            quer0 = bemessC12.get(arrC12[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }

                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        if (amp <= arrC13[i]) {
                            amp2 = arrC13[i];
                            quer0 = bemessC13.get(arrC13[i]);
                            break;
                        } else {
                            Toast.makeText(new KabelBerechnung(), "Ampere Overflow !",
                                    Toast.LENGTH_LONG).show();
                            break;

                        }
                    }

                }

        }

        Intent i = new Intent(this, SendKabel.class);
        startActivity(i);

        // Berechnung mit Faktoren

        float amp3 = 0;
        float tempfaktor = arrtemp[tempposition];
        float faktorhauf = 0;

        switch (verlegeposition) {

            case 0:

                faktorhauf = haufa.get(arrhauf[haufposition]);
                amp3 = (amp2 * tempfaktor * faktorhauf);

            case 1:

                faktorhauf = haufa.get(arrhauf[haufposition]);
                amp3 = (amp2 * tempfaktor * faktorhauf);
            case 2:

                faktorhauf = haufa.get(arrhauf[haufposition]);
                amp3 = (amp2 * tempfaktor * faktorhauf);
            case 3:
                faktorhauf = haufa.get(arrhauf[haufposition]);
                amp3 = (amp2 * tempfaktor * faktorhauf);
            case 4:
                faktorhauf = haufc.get(arrhauf[haufposition]);
                amp3 = (amp2 * tempfaktor * faktorhauf);


        }


        String text00 = null;
        if (amp <= amp2 && amp2 <= amp3) {

            double deltaU = 0;
            double flaeche = 0;
            flaeche = ((quer0 * quer0 * 3.14159265359) / 4);

            deltaU = ((2 * laenge * amp * cos) / (58000000 * flaeche));
            text00 = String.valueOf(deltaU);
        } else {

            Toast.makeText(new KabelBerechnung(), "Leider sind wir auf kein Ergebnis gekommen",
                    Toast.LENGTH_LONG).show();
        }


        Toast.makeText(new KabelBerechnung(), text00,
                Toast.LENGTH_LONG).show();

    }

    

}
