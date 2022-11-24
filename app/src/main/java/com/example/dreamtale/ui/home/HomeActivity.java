package com.example.dreamtale.ui.home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.dialog.YesNoDialog;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.mediaplayer.MediaControllerFragment;
import com.example.dreamtale.utils.DialogUtils;
import com.example.dreamtale.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter> {
    @BindView(R.id.edt_search)
    protected EditText edtSearch;
    @BindView(R.id.img_menu)
    protected ImageView imgMenu;
    @BindView(R.id.container_for_me)
    protected LinearLayout ctnForme;
    @BindView(R.id.container_libs)
    protected LinearLayout ctnLibs;
    @BindView(R.id.container_tool_bar)
    LinearLayout ctnSearch;
    @BindView(R.id.top_navigation_bar)
    RelativeLayout ctnTopNavigation;
    @BindView(R.id.tool_bar_item)
    RelativeLayout ctnToolBar;
    @BindView(R.id.img_content)
    ImageView ivContent;
    @BindView(R.id.txt_tool_title)
    TextView tvToolTitle;
    @BindView(R.id.img_like)
    ImageView ivHeart;
    @BindView(R.id.btn_back)
    ImageView ivBtnBack;

    private static HomeActivity instance;
    private boolean doubleBackExit = false;
    private List<String> toolTitle;
    public static HomeActivity getInstance() {
        return instance;
    }

    public static void setInstance(HomeActivity instance) {
        HomeActivity.instance = instance;
    }


    public void setupToolBar(String title, String img, Boolean heartIc, Boolean canSearch, Boolean showNavigation) {
        if (title != null) {
            tvToolTitle.setVisibility(View.VISIBLE);
            tvToolTitle.setText(title);
            toolTitle.add(title);
        }

        if (img != null) {
            ivContent.setVisibility(View.VISIBLE);
            ImageUtils.loadImageCorner(getViewContext(), ivContent, img);
        }

        if (heartIc != null) {
            ivHeart.setVisibility(View.VISIBLE);
            ivHeart.setImageResource(heartIc ? R.drawable.ic_heart_select : R.drawable.ic_heart_unselected);
        }
    }


    @SuppressLint("ResourceAsColor")
    public void selectedTopNavigationBar() {

        ctnForme.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                addFragment(R.id.frg_common_content, new HomeBoxFragment(), null, true, HomeBoxFragment.class.getSimpleName());
            }
        });

        ctnLibs.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                addFragment(R.id.frg_common_content, new HomeLibFragment(), null, true, HomeLibFragment.class.getSimpleName());
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onPrepareLayout() {

        this.addFragment(R.id.frg_common_content, new HomeBoxFragment(), null, false, MediaControllerFragment.class.getSimpleName());
        selectedTopNavigationBar();
        instance = this;

        ivBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    public void addFragment(int containerId, Fragment fragment, Bundle bundle,
                            boolean addToBackStack, String tag) {
        super.addFragment(containerId, fragment, bundle, addToBackStack, tag);
        Drawable bgSelected = AppCompatResources.getDrawable(getViewContext(), R.drawable.bg_top_navigation_selected);
        Drawable bgUnSelected = AppCompatResources.getDrawable(getViewContext(), R.drawable.bg_top_navigation_unselected);

        if (bundle != null) {
            Boolean isShowNavigation = bundle.getBoolean(Constant.Extras.NAVIGATION);
            Boolean canSearch = bundle.getBoolean(Constant.Extras.CAN_SEARCH);

            Content content = (Content) bundle.getSerializable(Constant.Extras.DATA);

            if ( canSearch != null && !canSearch) {
                ctnSearch.setVisibility(View.GONE);
            }

            if ( isShowNavigation != null && !isShowNavigation) {
                ctnTopNavigation.setVisibility(View.GONE);
            }

            ctnToolBar.setVisibility(View.VISIBLE);

            if (content != null) {
                ImageUtils.loadImageCorner(getViewContext(), ivContent, content.getCoverImg());
            }
        }



        if (fragment instanceof HomeLibFragment) {
            ctnLibs.setBackground(bgSelected);
            ctnForme.setBackground(bgUnSelected);
        } else {
            ctnForme.setBackground(bgSelected);
            ctnLibs.setBackground(bgUnSelected);
        }

    }

    boolean confirmExit = false;

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 0 && !confirmExit) {
            YesNoDialog yesNoDialog = new YesNoDialog();
            yesNoDialog.setListener(new YesNoDialog.ItemClickListener() {
                @Override
                public void btnYesClick() {
                    confirmExit = true;
                    onBackPressed();
                }

                @Override
                public void btnNoClick() {
                    yesNoDialog.dismiss();
                    return;
                }
            });

            yesNoDialog.init(getViewContext(), "Bạn có chắc chắn muốn thoát khỏi ứng dụng ?");
            yesNoDialog.show(getSupportFragmentManager(), null);
            if (!confirmExit) {
                return;
            }
        }

        super.onBackPressed();

        Log.e("longtv", "onBackPressed: " + fragmentManager.getBackStackEntryCount() );
        Fragment fragment = fragmentManager.findFragmentById(R.id.frg_common_content);

        Drawable bgSelected = AppCompatResources.getDrawable(getViewContext(), R.drawable.bg_top_navigation_selected);
        Drawable bgUnSelected = AppCompatResources.getDrawable(getViewContext(), R.drawable.bg_top_navigation_unselected);
        if (fragment instanceof HomeLibFragment) {
            ctnLibs.setBackground(bgSelected);
            ctnForme.setBackground(bgUnSelected);
            ctnToolBar.setVisibility(View.GONE);
            ctnSearch.setVisibility(View.VISIBLE);
            ctnTopNavigation.setVisibility(View.VISIBLE);
        } else if (fragment instanceof HomeBoxFragment) {
            ctnForme.setBackground(bgSelected);
            ctnLibs.setBackground(bgUnSelected);
            ctnToolBar.setVisibility(View.GONE);
            ctnSearch.setVisibility(View.VISIBLE);
            ctnTopNavigation.setVisibility(View.VISIBLE);
        }



//        fragmentManager.popBackStack();
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
