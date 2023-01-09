package com.example.dreamtale.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseActivity;
import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.dialog.YesNoDialog;
import com.example.dreamtale.common.fragment.SettingFragment;
import com.example.dreamtale.common.service.MediaNotificationService;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.network.dto.LogHistory;
import com.example.dreamtale.network.dto.PaymentRequest;
import com.example.dreamtale.network.dto.PaymentResponse;
import com.example.dreamtale.network.dto.ResponseDTO;
import com.example.dreamtale.ui.mediaplayer.CenterMediaControllerFragment;
import com.example.dreamtale.ui.mediaplayer.MediaControllerFragment;
import com.example.dreamtale.ui.search.SearchFragment;
import com.example.dreamtale.utils.DialogUtils;
import com.example.dreamtale.utils.ImageUtils;
import com.example.dreamtale.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity<HomePresenter> {
    @BindView(R.id.edt_search)
    protected TextView edtSearch;
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
    private List<String> historyTitle = new ArrayList<>();
    private List<String> historyImage = new ArrayList<>();
    private List<String> toolTitle = new ArrayList<>();
    private Box.Type type;
    private boolean verifyMomoPayment = false;

    public boolean isVerifyMomoPayment() {
        return verifyMomoPayment;
    }

    public void setVerifyMomoPayment(boolean verifyMomoPayment) {
        this.verifyMomoPayment = verifyMomoPayment;
    }

    public static HomeActivity getInstance() {
        return instance;
    }

    public static void setInstance(HomeActivity instance) {
        HomeActivity.instance = instance;
    }

    public Box.Type getType() {
        return type;
    }

    public void setType(Box.Type type) {
        this.type = type;
    }

    public void setupToolBar(String title, String img, Boolean heartIc, Boolean canSearch, Boolean showNavigation) {
        historyImage.add(img);
        historyTitle.add(title);
        Log.e("longtv", "setupToolBar: " + img );
        if (title != null) {
            Log.e("longtv", "setupToolBar: title " + title );
            ivContent.setVisibility(View.GONE);
            tvToolTitle.setVisibility(View.VISIBLE);
            tvToolTitle.setText(title);
            toolTitle.add(title);
        }

        if (img != null) {
            Log.e("longtv", "setupToolBar: " + img );
            tvToolTitle.setVisibility(View.GONE);
            ivContent.setVisibility(View.VISIBLE);
            ImageUtils.loadImageCorner(getViewContext(), ivContent, "https://gentlenobra.com/wp-content/uploads/2021/11/anh-gai-xinh-nobra.jpg");
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

    @OnClick(R.id.edt_search)
    public void onSearch() {
        Log.e("anth", "focus");
        if (getViewContext().getSupportFragmentManager().findFragmentByTag(SearchFragment.class.getSimpleName()) == null) {
            addFragment(R.id.frg_search, new SearchFragment(), null, true, SearchFragment.class.getSimpleName());
        }
    }

    @OnClick(R.id.img_menu)
    public void openMenu() {
        SettingFragment settingFragment = new SettingFragment();
        settingFragment.show(getSupportFragmentManager(), null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onPrepareLayout() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if ((bundle != null && !bundle.getBoolean("from_notification")) || bundle == null) {
            this.addFragment(R.id.frg_common_content, new HomeBoxFragment(), null, false, HomeBoxFragment.class.getSimpleName());
        }
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
    protected void onPause() {
        super.onPause();
        Log.e("longtv", "onPause: 2" );
        Fragment fragment = getViewContext().getSupportFragmentManager().findFragmentById(R.id.frg_common_content);
        if (CenterMediaControllerFragment.getInstance() != null && (fragment instanceof MediaControllerFragment || fragment instanceof MediaControllerFragment)) {
            CenterMediaControllerFragment.getInstance().getPlayer().setPlayWhenReady(true);
            Intent intent = new Intent(getViewContext(), MediaNotificationService.class);
            intent.putExtra("key_action", "notification");
            getViewContext().startService(intent);
        }
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
            String toolTitle = bundle.getString(Constant.Extras.TITLE);
            Content content = (Content) bundle.getSerializable(Constant.Extras.DATA);

            if ( canSearch != null && !canSearch) {
                ctnSearch.setVisibility(View.GONE);
            }

            if ( isShowNavigation != null && !isShowNavigation) {
                ctnTopNavigation.setVisibility(View.GONE);
            }

            if (toolTitle != null) {
                tvToolTitle.setVisibility(View.VISIBLE);
                tvToolTitle.setText(toolTitle);
                ivContent.setVisibility(View.GONE);
                ivHeart.setVisibility(View.GONE);
            }

            ctnToolBar.setVisibility(View.VISIBLE);

            if (content != null) {
                Log.e("longtv", "addFragment: add img" );

                ImageUtils.loadImageCorner(getViewContext(), ivContent, "https://gentlenobra.com/wp-content/uploads/2021/11/anh-gai-xinh-nobra.jpg");
                tvToolTitle.setVisibility(View.GONE);
            }
            historyTitle.add(toolTitle);
            historyImage.add(content != null ? content.getImg() : null);
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
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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

        fragmentManager.popBackStack(MediaControllerFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (historyImage.size()  != 0 && historyTitle.size() != 0) {
            historyTitle.remove(historyTitle.size() - 1);
            historyImage.remove(historyImage.size() - 1);
//            setupToolBar(historyTitle.get(historyTitle.size() - 1), historyImage.get(historyImage.size() - 1), false, false, false);
        }


//        fragmentManager.popBackStack();
    }

    public ContentDTO<Box> dataHomeBox = null;
    public ContentDTO<Box> getDataHomeBox() {
        return dataHomeBox;
    }

    public void setDataHomeBox(ContentDTO<Box> dataHomeBox) {
        this.dataHomeBox = dataHomeBox;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String scheme = intent.getScheme();

        Log.e("longtv", "onNewIntent: " + intent.getData() );
        if (scheme != null && scheme.equalsIgnoreCase("dreamldmomosdk")) {
            verifyMomoPayment = true;
        }
        Log.e("longtv", "onNewIntent: " + intent.getData() );
    }



    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Log.e("longtv", "onResume: " + intent.getData());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("longtv", "onStop: hombox" );
        setDataHomeBox(null);
    }

    public void logHistory(LogHistory logHistory) {
        ServiceBuilder.getService().logHistory(logHistory).enqueue(new Callback<ResponseDTO<LogHistory>>() {
            @Override
            public void onResponse(Call<ResponseDTO<LogHistory>> call, Response<ResponseDTO<LogHistory>> response) {

            }

            @Override
            public void onFailure(Call<ResponseDTO<LogHistory>> call, Throwable t) {

            }
        });
    }



    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
