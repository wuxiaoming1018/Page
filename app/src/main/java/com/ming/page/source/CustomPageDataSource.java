package com.ming.page.source;

import android.arch.paging.PageKeyedDataSource;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.ming.page.bean.DataBean;
import com.ming.page.repository.CustomRepository;

import java.util.List;

public class CustomPageDataSource extends PageKeyedDataSource<Integer, DataBean> {

    private CustomRepository customRepository;

    public CustomPageDataSource(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    /**
     * 接受初始加载的数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
        List<DataBean> dataInit = customRepository.getDataInit(params.requestedLoadSize);
        callback.onResult(dataInit, null, 1);
    }

    /**
     * 接收记载的数据
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        new Thread(() -> {
            SystemClock.sleep(3000);
            List<DataBean> dataByRange = customRepository.getDataByRange(params.key, params.requestedLoadSize);
            callback.onResult(dataByRange, params.key - 1);
        }).start();
    }

    /**
     * 加载数据之前
     *
     * @param params
     * @param callback
     */
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
        new Thread(() -> {
            //模拟耗时操作
            SystemClock.sleep(3000);
            List<DataBean> dataByPage = customRepository.getDataByPage(params.key, params.requestedLoadSize);
            callback.onResult(dataByPage, params.key - 1);
        }).start();
    }
}
