package com.example.dreamtale.ui.home;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.base.BasePresenter;
import com.example.dreamtale.common.dialog.ContentAdapter;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.utils.CompatibilityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> {
    @BindView(R.id.edt_search)
    protected EditText edtSearch;
    @BindView(R.id.img_menu)
    protected ImageView imgMenu;
    @BindView(R.id.container_forme)
    protected LinearLayout ctnForme;
    @BindView(R.id.container_libs)
    protected LinearLayout ctnLibs;
    @BindView(R.id.rcy_test)
    protected RecyclerView recyclerView;


    public void selectedTopNavigationBar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onPrepareLayout() {

        List<Category> list = new ArrayList<>();
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        list.add(new Category());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getViewContext(), LinearLayoutManager.HORIZONTAL, false);
        HorizontalItemDecoration itemDecoration = new HorizontalItemDecoration(CompatibilityUtils.getItemSpacing(getViewContext()));
        ContentAdapter contentAdapter = new ContentAdapter(list, getViewContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(contentAdapter);

    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
