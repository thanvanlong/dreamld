package com.example.dreamtale.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.utils.PrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment<SearchPresenter, HomeActivity> implements SearchView{

    private static final int VOICE_SEARCH_REQUEST_CODE = 1000;
    @BindView(R.id.edv_search)
    EditText edtSearch;
    @BindView(R.id.rcy_result)
    RecyclerView rcyResultSearch;
    @BindView(R.id.iv_search_voice)
    ImageView ivSearchVoice;
    boolean isSearch = false;
    private SearchAdapter searchAdapter;
    private List<Content> contentList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onPrepareLayout() {
        setAdapter(R.layout.item_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPresenter().doSearch(charSequence + "");
                Log.e("longtv", "onTextChanged: " + charSequence );
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAdapter(int item) {
//        contentList.addAll(PrefManager.getHistorySearch(getViewContext()));
        searchAdapter = new SearchAdapter(getViewContext(), contentList, item);

        rcyResultSearch.setLayoutManager(new LinearLayoutManager(getViewContext(), LinearLayoutManager.VERTICAL, false));
        rcyResultSearch.setAdapter(searchAdapter);
    }

    @OnClick(R.id.iv_search_voice)
    public void onVoiceSearchClick() {
        Intent voiceSeachIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        Locale locale = new Locale("vi");
        voiceSeachIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale);
        voiceSeachIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceSeachIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Search voice");
        voiceSeachIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        voiceSeachIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 300);

        try {
            startActivityForResult(voiceSeachIntent, VOICE_SEARCH_REQUEST_CODE);
        } catch (Exception e) {
            Toast.makeText(getViewContext(), "Loi mat roi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VOICE_SEARCH_REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edtSearch.setText(results.get(0));
                Log.e("longtv", "onActivityResult: " + results.get(0) );
            }
        }
    }

    @Override
    public SearchPresenter onCreatePresenter() {
        return new SearchPresenterImpl(this);
    }

    @Override
    public void doSearch(List<Content> list) {
        Log.e("longtv", "doSearch: search response " + list.size() );
        if (contentList != null) {
            contentList.clear();
        }
        contentList.addAll(list);
//        setAdapter(R.layout.item_search);
        searchAdapter.notifyDataSetChanged();
    }
}
