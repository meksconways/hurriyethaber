package com.mek.hurriyethaber.home;

import com.bluelinelabs.conductor.Controller;
import com.mek.hurriyethaber.R;
import com.mek.hurriyethaber.base.BaseActivity;
import com.mek.hurriyethaber.articlenews.ArticleNewsController;
import com.mek.hurriyethaber.homecontroller.HomeController;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        // todo
        return new HomeController();
    }
}
