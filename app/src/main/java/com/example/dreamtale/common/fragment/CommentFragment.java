package com.example.dreamtale.common.fragment;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.adater.CommentAdapter;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.AuthRequestBody;
import com.example.dreamtale.network.dto.Comment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomePresenter;
import com.example.dreamtale.ui.mediaplayer.DetailFragment;
import com.example.dreamtale.ui.mediaplayer.MediaControlPresenter;
import com.example.dreamtale.ui.mediaplayer.MediaControlPresenterImpl;
import com.example.dreamtale.ui.mediaplayer.MediaControlView;
import com.example.dreamtale.ui.mediaplayer.MediaControllerFragment;
import com.example.dreamtale.utils.CompatibilityUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentFragment extends BaseFragment<MediaControlPresenter, HomeActivity> implements SwipeRefreshLayout.OnRefreshListener, MediaControlView {

    @BindView(R.id.shimmer_layout)
    ShimmerFrameLayout shimmerFrameLayout;
    @BindView(R.id.rcy_cmt)
    RecyclerView rcyComment;
    @BindView(R.id.rating)
    RatingBar ratingBar;
    @BindView(R.id.btn_submit_cmt)
    Button btnComment;
    @BindView(R.id.edt_comment)
    EditText edtComment;
    @BindView(R.id.tv_number_text_cmt)
    TextView tvNumber;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private CommentAdapter commentAdapter;
    private ContentDTO<Comment> contentDTO = null;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_comment;
    }

    public static CommentFragment instance;

    public static synchronized CommentFragment getInstance() {
        return instance;
    }

    public static synchronized CommentFragment newInstance() {
        CommentFragment commentFragment = new CommentFragment();
        instance = commentFragment;

        return commentFragment;
    }

    @Override
    public void onPrepareLayout() {
        setListener();
    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        edtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    btnComment.setEnabled(true);
                } else {
                    btnComment.setEnabled(false);
                }

                tvNumber.setText(charSequence.length() + "/500");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comment comment = new Comment();
                comment.setComment(edtComment.getText().toString());
                comment.setId((int) DetailFragment.getInstance().getContent().getId());
                comment.setStar((int) ratingBar.getRating());
                comment.setType(DetailFragment.getInstance().getType().ordinal());

                getPresenter().doComment(comment);
            }
        });
    }


    public void loadData(int pageSize, int pageNumber, int id, int type) {
        getPresenter().getComment(id, pageSize, pageNumber, type);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (DetailFragment.getInstance() != null && DetailFragment.getInstance().getContent() != null && DetailFragment.getInstance().getType() != null) {
            if (contentDTO == null) {
                loadData(8, 1, (int) DetailFragment.getInstance().getContent().getId(), DetailFragment.getInstance().getType().ordinal());
            } else {
                loadComment(contentDTO);
            }
        }
    }

    @Override
    public MediaControlPresenter onCreatePresenter() {
        return new MediaControlPresenterImpl(this);
    }

    @Override
    public void onRefresh() {
        loadData(8, 1, (int) DetailFragment.getInstance().getContent().getId(), DetailFragment.getInstance().getType().ordinal());
    }

    @Override
    public void loadListContent(ContentDTO<Content> data) {

    }

    @Override
    public void loadComment(ContentDTO<Comment> data) {
        shimmerFrameLayout.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        if (contentDTO == null) {
            contentDTO = data;
        }
        commentAdapter = new CommentAdapter(contentDTO.getContentList(), getViewContext());
//        rcyComment.addItemDecoration(new HorizontalItemDecoration(CompatibilityUtils.getItemSpacing(getViewContext())));

        rcyComment.setLayoutManager(new LinearLayoutManager(getViewContext(), RecyclerView.VERTICAL, false));
        rcyComment.setAdapter(commentAdapter);
    }

    @Override
    public void doCommentSuccess(Comment data) {
        data.getAu().setUsername("You");
        edtComment.setText("");
        ratingBar.setRating(0);
        contentDTO.getContentList().add(0, data);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void popupMessage() {

    }
}
