package com.ming.page.factory;

import android.arch.paging.DataSource;

import com.ming.page.bean.DataBean;
import com.ming.page.repository.CustomRepository;
import com.ming.page.source.CustomPageDataSource;

public class CustomPageDataSourceFactory extends DataSource.Factory<Integer, DataBean> {

    private CustomRepository customRepository;

    public CustomPageDataSourceFactory(CustomRepository customRepository){
        this.customRepository = customRepository;
    }

    @Override
    public DataSource<Integer, DataBean> create() {
        return new CustomPageDataSource(customRepository);
    }
}
