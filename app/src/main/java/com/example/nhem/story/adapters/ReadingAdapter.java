package com.example.nhem.story.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.StaticLayout;
import com.example.nhem.story.databases.StoryModel;
import com.example.nhem.story.fragments.PageFragment;
import com.example.nhem.story.utils.PageSpliter;
import java.util.ArrayList;
import java.util.List;


public class ReadingAdapter extends FragmentStatePagerAdapter {
    private List<String> listPage = new ArrayList<>();

    public ReadingAdapter(FragmentManager fm, StoryModel storyModel,
                          int width, int height) {
        super(fm);

        new PageSpliter(storyModel.getContent(), width, height);
        StaticLayout staticLayout = PageSpliter.creatStaticLayout(storyModel.getContent());
        listPage = PageSpliter.splitPage(staticLayout);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(StoryAdapter.KEY_STORY, listPage.get(position));
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public int getCount() {
        return listPage.size();
    }
}
