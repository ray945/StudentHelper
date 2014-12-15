package remerl.me.studenthelper.view;

import android.app.LoaderManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import remerl.me.studenthelper.R;

/**
 * Created by qiugang on 14/12/13.
 */
public class LoadingFooter {

    protected View mLoadingFooter;

    protected TextView mLoadingTextView;

    private ProgressBar mProgressBar;

    protected State mState;

    private long mAnimationDuration;

    public static enum State {
        Idle, TheEnd, Loading
    }

    public LoadingFooter(Context context) {
        mLoadingFooter = LayoutInflater.from(context).inflate(R.layout.view_loadingfooter, null);
        mLoadingFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing
            }
        });
        mProgressBar = (ProgressBar) mLoadingFooter.findViewById(R.id.progressBar);
        mLoadingTextView = (TextView) mLoadingFooter.findViewById(R.id.textView);
        mAnimationDuration = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
    }

    public View getView() {
        return mLoadingFooter;
    }

    public State getState() {
        return mState;
    }

    public void setState(final State state, long delay) {
        mLoadingFooter.postDelayed(new Runnable() {
            @Override
            public void run() {
                mState = state;
            }
        }, delay);
    }

    public void setState(State status) {
        if (mState == status) {
            return;
        }
        mState = status;
        mLoadingFooter.setVisibility(View.VISIBLE);
        switch (status) {
            case Loading:
                mLoadingTextView.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                break;
            case TheEnd:
                mLoadingTextView.setVisibility(View.VISIBLE);
                mLoadingTextView.animate().withLayer().alpha(1).setDuration(mAnimationDuration);
                mProgressBar.setVisibility(View.GONE);
            default:
                mLoadingFooter.setVisibility(View.GONE);
        }
    }
}
