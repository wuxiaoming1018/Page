package com.ming.page;

import android.arch.core.util.Function;
import android.arch.paging.DataSource;
import android.arch.paging.DataSource.Factory;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.ming.page.bean.DataBean;
import com.ming.page.source.PositionlSource;

import java.util.ArrayList;
import java.util.List;

public class MyDataSourceFactory extends Factory<Integer, DataBean> {
    @Override
    public DataSource<Integer, DataBean> create() {
//        return new MyDataSource();
        return new PositionlSource();
    }

    private  class MyDataSource extends PositionalDataSource<DataBean> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DataBean> callback) {
            callback.onResult(loadData(0,10),0,10);
        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DataBean> callback) {
            callback.onResult(loadData(params.startPosition,10));
        }
    }

    private List<DataBean> loadData(int startPosition, int count) {
        List<DataBean> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            DataBean bean = new DataBean();
            bean.id = startPosition+i;
            bean.content = "测试内容="+bean.id;
            list.add(bean);
        }
        return list;
    }
}
