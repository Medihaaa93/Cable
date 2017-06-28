package com.example.mediha.cable;

import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BerrechnungSicherung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berrechnung_sicherung);
    }


    public void startBtn(View view) {





    }
    //************************************************************************* PDFmaker
/*
    public void makePDF (){

        // open a new document
        PrintedPdfDocument document = new PrintedPdfDocument(context,
                printAttributes);

        // start a page
        Page page = document.startPage(0);

        // draw something on the page
        View content = getContentView();
        content.draw(page.getCanvas());

        // finish the page
        document.finishPage(page);

        // add more pages

        // write the document content
        document.writeTo(getOutputStream());

        //close the document
        document.close();



    }***************************************************************************************/
}
