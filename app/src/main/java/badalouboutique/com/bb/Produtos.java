package badalouboutique.com.bb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

//TELA DE INCLUSÃO DE PRODUTOS

public class Produtos extends AppCompatActivity implements View.OnClickListener{

    ViewPager pager;
    TabLayout tabLayout;
    TabItem secondItem,thirdItem;
    PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        //ATIVANDO A FUNÇÃO DA TOOLBAR ATRAVÉS DA ACTIONBAR
        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        secondItem = findViewById(R.id.tab_inc);
        thirdItem = findViewById(R.id.tab_age);

        adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.getTabCount());
        pager.setAdapter(adapter);

        //CÓDIGO DAS ABAS
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_back4) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
    }
}