package com.app.fitv1.Features.BeforeLogin;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.app.fitv1.Features.Login.LoginActivity;
import com.app.fitv1.Features.Navigations.Navigation;
import com.app.fitv1.Features.Register.Register;
import com.app.fitv1.R;

public class BeforeLoginTabs extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout, llLoginOptions;
    TextView[] dots;
    private int[] layouts;
    Button btnNext, btnSkipForNow, btnLogin, btnRegister, btnFacebookLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_before_login);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        llLoginOptions = findViewById(R.id.llLoginOptions);
        btnNext = findViewById(R.id.btn_next);
        btnSkipForNow = findViewById(R.id.btnSkipForNow);
        btnFacebookLogin = findViewById(R.id.btnFacebookLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        layouts = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        // changeStatusBarColor();


        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnNext.setOnClickListener(this);
        btnFacebookLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSkipForNow.setOnClickListener(this);
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            if (position == layouts.length - 1) {
                btnSkipForNow.setVisibility(View.VISIBLE);
                llLoginOptions.setVisibility(View.VISIBLE);
                dotsLayout.setVisibility(View.GONE);
                btnNext.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.skip));
                btnSkipForNow.setVisibility(View.GONE);
                llLoginOptions.setVisibility(View.GONE);
                dotsLayout.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                LoginActivity.start(BeforeLoginTabs.this);
                finish();
                break;
            case R.id.btnSkipForNow:
                Navigation.start(BeforeLoginTabs.this);
                finish();
                break;
            case R.id.btnLogin:
                LoginActivity.start(BeforeLoginTabs.this);
                finish();
                break;
            case R.id.btnRegister:
                Register.start(BeforeLoginTabs.this);
                finish();
                break;
            case R.id.btnFacebookLogin:
                // TODO: 2019-06-21 Facebook login implementation
                Navigation.start(BeforeLoginTabs.this);
                finish();
                break;
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        MyViewPagerAdapter() {
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert layoutInflater != null;
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
