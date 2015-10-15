package com.example.coupang.amyshoppingmall;

import android.widget.SearchView;

/**
 * Created by Amy on 2015. 8. 18..
 */
public class TextQueryListner implements SearchView.OnQueryTextListener {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
