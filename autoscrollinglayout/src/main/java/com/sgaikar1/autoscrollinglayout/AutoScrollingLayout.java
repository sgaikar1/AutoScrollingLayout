package com.sgaikar1.autoscrollinglayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Santosh on 10/09/17.
 */

public class AutoScrollingLayout extends FrameLayout implements OnPositionCalled{
    private static final int DEFAULT_TINT_COLOR = Color.TRANSPARENT;
    private static final float DEFAULT_SPEED = 1600f;
    private static final float DEFAULT_ALPHA = 0.9f;
    private static AutoScrollingLayout autoScrollingLayout;
    private float mAlpha;
    private float mSpeed;
    private Drawable mBackgroundImage;
    private int mTintColor;
    private Context mContext;
    private RecyclerView recyclerView;
    private FrameLayout emptyView;

    public static synchronized AutoScrollingLayout getInstance(Context context){
        if(autoScrollingLayout == null){
             autoScrollingLayout = new AutoScrollingLayout(context.getApplicationContext());
        }
        return autoScrollingLayout;
    }

    public AutoScrollingLayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public AutoScrollingLayout(Context context, AttributeSet attr) {
        this(context,attr,0);
        this.mContext = context;
        init();
    }

    public AutoScrollingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoScrollingLayout, defStyle, 0);
        mTintColor = a.getColor(R.styleable.AutoScrollingLayout_tint_color, DEFAULT_TINT_COLOR);
        mSpeed = a.getFloat(R.styleable.AutoScrollingLayout_scroll_speed, DEFAULT_SPEED);
        mAlpha = a.getFloat(R.styleable.AutoScrollingLayout_tint_alpha, DEFAULT_ALPHA);
        mBackgroundImage = a.getDrawable(R.styleable.AutoScrollingLayout_img_src);

        init();
    }

    // region Helper Methods
    public void init() {
        if (!isInEditMode()) {
            View root = inflate(getContext(), R.layout.auto_scrolling_layout, this);
            recyclerView = (RecyclerView) root.findViewById(R.id.rv);
            emptyView = (FrameLayout) root.findViewById(R.id.fl_parent);
            emptyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            emptyView.setBackgroundColor(mTintColor);
            emptyView.setAlpha(mAlpha);
            ArrayList<Drawable> imgList = new ArrayList<>();

            if(mBackgroundImage==null) {
                mBackgroundImage = ContextCompat.getDrawable(mContext,R.drawable.longimage);
            }
                for (int i = 0; i < 1000; i++) {
                    imgList.add(mBackgroundImage);
                }
                AutoScrollingAdapter mAdapter = new AutoScrollingAdapter(imgList, this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext()) {
                    @Override
                    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {

                            @Override
                            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                                return mSpeed / displayMetrics.densityDpi;
                            }

                        };
                        smoothScroller.setTargetPosition(position);
                        startSmoothScroll(smoothScroller);
                    }
                };
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);


        }

    }
    @Override
    public void onPositionCalled(int position) {
        if (position + 1 == 1000) {
            recyclerView.scrollToPosition(0);
        } else {
            recyclerView.smoothScrollToPosition(position + 1);
        }
    }

    public AutoScrollingLayout setBackgroundSrc(Bitmap bitmap){
        mBackgroundImage = new BitmapDrawable(getResources(),bitmap);
        return autoScrollingLayout;
    }

    public AutoScrollingLayout setBackgroundSrc(Drawable drawable){
        mBackgroundImage = drawable;

        return autoScrollingLayout;
    }

    public AutoScrollingLayout setTintColor(int color){
        mTintColor = color;
        emptyView.setBackgroundColor(mTintColor);
        return autoScrollingLayout;
    }

    public AutoScrollingLayout setSpeed(float speed){
        mSpeed = speed;
        return autoScrollingLayout;
    }

    public AutoScrollingLayout setBackgroundAlpha(float alpha){
        mAlpha =alpha;
        emptyView.setAlpha(mAlpha);
        return autoScrollingLayout;
    }


    public class AutoScrollingAdapter extends RecyclerView.Adapter<AutoScrollingAdapter.MyViewHolder> {

        private final OnPositionCalled callback;
        private ArrayList<Drawable> imgList;
        private ArrayList<String> data;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView img;

            public MyViewHolder(View view) {
                super(view);
                img = (ImageView)view.findViewById(R.id.img);
            }
        }


        public AutoScrollingAdapter(ArrayList<Drawable> moviesList, OnPositionCalled callback) {
            this.imgList = moviesList;
            this.callback=callback;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.imageitem, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            callback.onPositionCalled(position);

            holder.img.setImageDrawable(imgList.get(position));
        }

        @Override
        public int getItemCount() {
            return imgList.size();
        }

    }
}
