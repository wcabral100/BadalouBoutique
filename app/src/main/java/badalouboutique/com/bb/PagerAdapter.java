package badalouboutique.com.bb;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//CLASSE PARA O FUNCIONAMENTO DAS ABAS

public class PagerAdapter extends FragmentPagerAdapter {
    private final int tabsNumber;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new badalouboutique.com.bb.FragInclude();
            case 1 :
                return new badalouboutique.com.bb.FragAge();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
