package com.example.dreamtale.ui.home;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.dialog.ContentAdapter;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.Category;
import com.example.dreamtale.utils.CompatibilityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListeningBoxFragment extends BaseFragment<HomePresenter, HomeActivity> implements HomeBoxView{

    @BindView(R.id.tv_type_content)
    TextView txtTypeContent;
    @BindView(R.id.rcy_content)
    RecyclerView rcyContent;
    private ContentAdapter contentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_listening;
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
        contentAdapter = new ContentAdapter(list, 10, getViewContext());
        rcyContent.setLayoutManager(layoutManager);
        rcyContent.addItemDecoration(itemDecoration);
        rcyContent.setAdapter(contentAdapter);
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
