package remerl.me.studenthelper.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import remerl.me.studenthelper.R;
import remerl.me.studenthelper.dao.Category;
import remerl.me.studenthelper.ui.fragment.BaseFragment;
import remerl.me.studenthelper.ui.fragment.DrawerFragment;
import remerl.me.studenthelper.ui.fragment.TodoFragment;

/**
 * Created by qiugang on 2014/11/13.
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar toolbar;

    private BaseFragment contentFragment;

    private Category mCategory;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initDrawer();
        // 默认选择
        setCategory(Category.Personal);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);

                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            }
        });
    }

    private void initDrawer() {
        // Init drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getFragmentManager().beginTransaction().replace(R.id.left_drawer, new DrawerFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    public void setCategory(Category category) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if (mCategory == category) {
            return;
        }
        mCategory = category;
        switch (category) {
            case Home:
                break;
            case Personal:
                contentFragment = new TodoFragment();
                break;
            case MyClass:

                break;
            case MessageBoard:

                break;
            case Settings:

                break;
            default:
                break;
        }
        getFragmentManager().beginTransaction().replace(R.id.container, contentFragment)
                .commit();
        //Set title
        getSupportActionBar().setTitle(category.getDisplayName());
    }

    //Double click to exit
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            exitTime = System.currentTimeMillis();
            Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}
