package com.example.delli5547.testing_loop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.cleveroad.loopbar.adapter.ICategoryItem;
import com.cleveroad.loopbar.adapter.SimpleCategoriesAdapter;
import com.cleveroad.loopbar.model.MockedItemsFactory;
import com.cleveroad.loopbar.widget.LoopBarView;
import com.cleveroad.loopbar.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private SimpleCategoriesAdapter categoriesAdapter;
    private SimpleFragmentStatePagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewPager);


        final LoopBarView loopBarView = (LoopBarView) findViewById(R.id.endlessView);
        categoriesAdapter = new SimpleCategoriesAdapter(MockedItemsFactory.getCategoryItemsUniq());
        loopBarView.setCategoriesAdapter(categoriesAdapter);
        loopBarView.addOnItemClickListener(this);

        Fragment fragment = new Fragment1();
        Fragment fragment1 = new Fragment2();

        List<Fragment> list = new ArrayList<>(8);

        list.add(fragment);
        list.add(fragment1);

        pagerAdapter = new SimpleFragmentStatePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new AbstractPageChangedListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                loopBarView.setCurrentItem(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("tag", "on page scrolled");
            }
        });

    }


    @Override
    public void onItemClicked(int position) {
        if (toast != null) {
            toast.cancel();
        }

        ICategoryItem categoryItem = categoriesAdapter.getItem(position);
        viewPager.setCurrentItem(position);

//        toast = Toast.makeText(this, getString(R.string.mask_on_item_clicked, position, categoryItem.getCategoryName()), Toast.LENGTH_SHORT);
//        toast.show();
    }
}

