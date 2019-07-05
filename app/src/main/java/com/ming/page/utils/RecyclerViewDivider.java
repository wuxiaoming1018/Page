package com.ming.page.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * RecyclerViewDivider
 */

public class RecyclerViewDivider extends RecyclerView.ItemDecoration {
    private static final int UNINIT_ORIENTATION = -2;
    private static final int INVALID_ORIENTATION = -1;
    private static final int LINEAR_LAYOUT_VERTICAL = 1;
    private static final int LINEAR_LAYOUT_HORIZONTAL = 2;
    private static final int STAGGER_LAYOUT = 3;
    private static final int GRID_LAYOUT = 4;

    private int mDividerSpan;
    private Drawable mDrawable;
    private int mLeft, mTop, mRight, mBottom;
    private int mLayoutOrientation = UNINIT_ORIENTATION;

    public RecyclerViewDivider(){this(2, 0xFFCCCCCC);}

    public RecyclerViewDivider(int dividerHeight, int dividerColor){
        this(dividerHeight, new ColorDrawable(dividerColor));
    }

    public RecyclerViewDivider(int dividerHeight, Drawable drawable){
        mDividerSpan = dividerHeight;
        mDrawable = null == drawable ? new ColorDrawable(0xffdd6611) : drawable;
    }

    public void setItemMargin(int left, int top, int right, int bottom){
        mLeft = left;
        mTop = top;
        mRight = right;
        mBottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        getOrientation(parent);
        setItemOffset(outRect);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(LINEAR_LAYOUT_VERTICAL == mLayoutOrientation){
            drawHorizontalDivider(c, parent);
        }else if(LINEAR_LAYOUT_HORIZONTAL == mLayoutOrientation){
            drawVerticalDivider(c, parent);
        }
    }

    private void getOrientation(RecyclerView parent){
        if(UNINIT_ORIENTATION == mLayoutOrientation){
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if(layoutManager instanceof LinearLayoutManager){
                int orientation = ((LinearLayoutManager) layoutManager).getOrientation();
                mLayoutOrientation = LinearLayoutManager.VERTICAL == orientation ? LINEAR_LAYOUT_VERTICAL : LINEAR_LAYOUT_HORIZONTAL;
            }else if(layoutManager instanceof StaggeredGridLayoutManager){
                mLayoutOrientation = STAGGER_LAYOUT;
            }else{
                mLayoutOrientation = GRID_LAYOUT;
            }
            optimizePadding(parent);
        }
    }

    private void setItemOffset(Rect outRect){
        switch(mLayoutOrientation){
            case LINEAR_LAYOUT_VERTICAL: outRect.set(0, 0, 0, mDividerSpan);
                break;
            case LINEAR_LAYOUT_HORIZONTAL: outRect.set(0, 0, mDividerSpan, 0);
                break;
            case STAGGER_LAYOUT:
            case GRID_LAYOUT: outRect.set(mLeft / 2, mTop, mRight / 2, 0);
                break;
        }
    }

    private void optimizePadding(RecyclerView recyclerView){
        if(STAGGER_LAYOUT == mLayoutOrientation || GRID_LAYOUT == mLayoutOrientation){
            mLeft += recyclerView.getPaddingLeft();
            mRight += recyclerView.getPaddingRight();
            recyclerView.setPadding(mLeft / 2, recyclerView.getPaddingTop(), mRight / 2, recyclerView.getPaddingBottom());
        }
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
        int width = parent.getWidth() - mRight;
        View child;
        for(int i = 0; i < childCount - 1; i++){
            child = parent.getChildAt(i);
            mDrawable.setBounds(mLeft, child.getBottom(), width, child.getBottom() + mDividerSpan);
            mDrawable.draw(c);
        }
    }

    private void drawVerticalDivider(Canvas c, RecyclerView parent){
        int childCount = parent.getChildCount();
        int height = parent.getHeight() - mBottom;
        View child;
        for(int i = 0; i < childCount - 1; i++){
            child = parent.getChildAt(i);
            mDrawable.setBounds(child.getRight(), mTop, child.getRight() + mDividerSpan, height);
            mDrawable.draw(c);
        }
    }

}
