package com.mwaqaspervez.books.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mwaqaspervez.books.R;
import com.mwaqaspervez.books.Utils.Util;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class BookShelf extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_layout, container, false);


        textView = new TextView(getContext());
        textView.setMovementMethod(new ScrollingMovementMethod());
        new myTask().execute();
        return textView;
    }

    public String stripText(File file) {
        String parsedText = null;
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PDFTextStripper pdfStripper = new PDFTextStripper();

            parsedText = "Parsed text: " + pdfStripper.getText(document);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "ERROR";
    }

    public class myTask extends AsyncTask<Void, Void, String> {
        long startTime;
        long endTime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startTime = System.currentTimeMillis();
            Util.Log("Time Started : " + startTime);
        }

        @Override
        protected String doInBackground(Void... voids) {
            File images = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File[] imagelist = images.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return ((name.endsWith(".pdf")));
                }
            });
            return stripText(imagelist[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            endTime = System.currentTimeMillis();
            Util.Log("Time Ended : " + endTime);
            Util.Log("Time Taken : " + (endTime - startTime) / 1000);
            textView.setText(s);

        }
    }
}
