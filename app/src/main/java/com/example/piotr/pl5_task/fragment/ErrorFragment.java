package com.example.piotr.pl5_task.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piotr.pl5_task.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorFragment extends Fragment {
    private static final String MESSAGE = "message";
    @BindView(R.id.error_img)
    ImageView imageViewSad;
    @BindView(R.id.retry_btn)
    Button retryButton;
    @BindView(R.id.error_msg)
    TextView errorTextView;

    private String message;
    private OnRetryClickListener onRetryClickListener;

    public ErrorFragment() {
    }

    public static ErrorFragment newInstance(String message) {
        ErrorFragment fragment = new ErrorFragment();
        Bundle args = new Bundle();
        args.putString(MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.message = getArguments().getString(MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.error_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Picasso.get().load(R.mipmap.ic_launcher_round).centerCrop().fit().into(imageViewSad);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRetryClickListener.onRetryClicked();
            }
        });
        errorTextView.setText(message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRetryClickListener) {
            onRetryClickListener = (OnRetryClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ErrorFragment.OnRetryClickListener");
        }
    }

    public interface OnRetryClickListener {
        void onRetryClicked();
    }

}

