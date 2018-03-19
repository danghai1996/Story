package com.example.nhem.story.fragments;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nhem.story.R;
import com.example.nhem.story.adapters.StoryAdapter;
import com.example.nhem.story.utils.PageSpliter;
import com.example.nhem.story.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {
    private String content;
    private int width;
    private int height;


    public PageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page, container, false);

        content = getArguments().getString(StoryAdapter.KEY_STORY);
        width = Utils.getActivityWidth(getActivity());
        height = Utils.getActivityHeight(getActivity());

        ImageView imageView = view.findViewById(R.id.iv);
        imageView.setImageBitmap(getBitmap());
        // Inflate the layout for this fragment
        return view;
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        StaticLayout staticLayout = PageSpliter.creatStaticLayout(content);
        staticLayout.draw(canvas);
        return bitmap;
    }
}
