package com.example.nhem.story.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import com.example.nhem.story.R;
import com.example.nhem.story.adapters.ReadingAdapter;
import com.example.nhem.story.adapters.StoryAdapter;
import com.example.nhem.story.databases.StoryModel;
import com.example.nhem.story.utils.Utils;

public class ReadingActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        viewPager = (ViewPager) findViewById(R.id.vp_reading);
        ReadingAdapter readingAdapter = new ReadingAdapter(
                getSupportFragmentManager(),
                (StoryModel) getIntent().getExtras().getSerializable(StoryAdapter.KEY_STORY),
                Utils.getActivityWidth(this), Utils.getActivityHeight(this));
        viewPager.setAdapter(readingAdapter);

        progressBar = (ProgressBar) findViewById(R.id.pb_reading);
        progressBar.setMax(readingAdapter.getCount());
        progressBar.setProgress(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                progressBar.setProgress(viewPager.getCurrentItem() + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
