package com.example.pdfdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class MainActivity extends AppCompatActivity {
    private PDFView mPdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPdfView = (PDFView) findViewById(R.id.pdfView);


    }
}
