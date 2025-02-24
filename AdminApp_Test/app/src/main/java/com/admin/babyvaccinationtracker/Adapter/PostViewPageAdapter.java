package com.admin.babyvaccinationtracker.Adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.admin.babyvaccinationtracker.Post_VisitsFragment;
import com.admin.babyvaccinationtracker.Fragment_delete_post;
import com.admin.babyvaccinationtracker.model.Post;

import java.util.ArrayList;

public class PostViewPageAdapter extends FragmentPagerAdapter {
    ArrayList<Post> posts;
    public PostViewPageAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<Post> posts) {
        super(fm, behavior);
        this.posts = posts;
    }
    public void setPost(ArrayList<Post> posts){
        this.posts = posts;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                Log.i("POST", posts+"");
                return Post_VisitsFragment.newInstance(posts);
//            case 1 :
//                Log.i("POST", posts+"");
//                return Fragment_delete_post.newInstance(posts);
            default: return Post_VisitsFragment.newInstance(posts);
        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Thống kê bài đăng";
//            case 1: return "Xóa bài bài đăng";
        }
        return super.getPageTitle(position);
    }
}
