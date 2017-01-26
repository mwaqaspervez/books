package com.mwaqaspervez.books.Fragments;

import android.graphics.Bitmap;
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

import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.mwaqaspervez.books.R;
import com.mwaqaspervez.books.Utils.Util;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.rendering.PDFRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

public class BookShelf extends Fragment {
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_layout, container, false);


        textView = new TextView(getContext());
        textView.setMovementMethod(new ScrollingMovementMethod());
        //new myTask().execute();

        PDFView pdfView = (PDFView) rootView.findViewById(R.id.pdf_view);


       /* pdfView.fromFile(imagelist[0])
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();*/
        new myTask().execute();

        return textView;
    }

    public Bitmap stripText(File file) {
        String parsedText = null;
        PDDocument document = null;
        try {
            document = PDDocument.load(file);

            PDFRenderer renderer = new PDFRenderer(document);
            // Render the image to an RGB Bitmap
            Bitmap pageImage = renderer.renderImage(0, 1, Bitmap.Config.RGB_565);
            File root = android.os.Environment.getExternalStorageDirectory();
            String path = root.getAbsolutePath() + "/Download/render.jpg";
            File renderFile = new File(path);
            FileOutputStream fileOut = new FileOutputStream(renderFile);
            pageImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOut);
            fileOut.close();


            return pageImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*  try {
            PDFTextStripper pdfStripper = new PDFTextStripper();

            parsedText = "Parsed text: " + pdfStripper.getText(document);
        } catch (Exception e) {
            e.printStackTrace();
        }*/ finally {
            try {
                if (document != null) document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
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
            String text = "";
            File images = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File[] imagelist = images.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return ((name.endsWith(".pdf")));
                }
            });
            PdfReader reader = null;
            try {
                reader = new PdfReader(URI.create("http://www.nsaahome.org/textfile/journ/multipdf.pdf").toURL());
                PdfReaderContentParser parser = new PdfReaderContentParser(reader);

                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    text += (PdfTextExtractor.getTextFromPage(reader, i));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return text;
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
