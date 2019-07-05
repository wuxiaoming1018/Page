package com.ming.page.source;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import com.ming.page.bean.DataBean;

public class ItemSource extends ItemKeyedDataSource<Integer, DataBean> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<DataBean> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull DataBean item) {
        return item.id;
    }
}
