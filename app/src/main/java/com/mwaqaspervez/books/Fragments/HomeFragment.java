package com.mwaqaspervez.books.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.mwaqaspervez.books.Adapters.AuthorAdapter;
import com.mwaqaspervez.books.Models.Author;
import com.mwaqaspervez.books.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements ViewPagerEx.OnPageChangeListener {


    @BindView(R.id.first_slider)
    SliderLayout mSliderLayout;

    private AuthorAdapter adapter;


    private void setUpSlider() {

        HashMap<String, Integer> file_maps = new HashMap<>();
        file_maps.put("Dubai", R.drawable.image2);
        file_maps.put("Malaysia", R.drawable.image3);
        file_maps.put("Europe", R.drawable.image4);
        file_maps.put("Thailand", R.drawable.image1);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.addOnPageChangeListener(this);
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_place, container, false);
        ButterKnife.bind(this, rootView);
        setUpSlider();
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
        adapter = new AuthorAdapter();
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.author_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        setDummyData();

        return rootView;
    }

    private void setDummyData() {
        adapter.add(new Author("Ravi", R.drawable.ic_default));
        adapter.add(new Author("Ravi", R.drawable.ic_default));
        adapter.add(new Author("Ravi", R.drawable.ic_default));
        adapter.add(new Author("Ravi", R.drawable.ic_default));
        adapter.add(new Author("Ravi", R.drawable.ic_default));
        adapter.add(new Author("Ravi", R.drawable.ic_default));
        adapter.add(new Author("Ravi", R.drawable.ic_default));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onStop() {
        mSliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onResume() {
        if (mSliderLayout != null)
            mSliderLayout.startAutoCycle();
        super.onResume();
    }
}
