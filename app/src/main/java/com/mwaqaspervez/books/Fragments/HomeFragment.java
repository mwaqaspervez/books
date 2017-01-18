package com.mwaqaspervez.books.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mwaqaspervez.books.R;


public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_place, container, false);


       /* File images = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] imagelist = images.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return ((name.endsWith(".pdf")));
            }
        });

        Log.i("Files Amount :", "" + imagelist.length);
        for (File file : imagelist) {
            Log.i("Pdf File : ", file.getName());
            Log.i("Pdf Path : ", file.getAbsolutePath());

        }

        PDFView pdfView = (PDFView) rootView.findViewById(R.id.pdfView);
        pdfView.fromFile(imagelist[5])
                .defaultPage(1)
                .enableSwipe(true)
                .enableAnnotationRendering(false)
                .load();*/
        return rootView;
    }

}