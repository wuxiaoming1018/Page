package com.ming.page.repository;

import android.support.annotation.IntRange;

import com.ming.page.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

public class CustomRepository {
    List<DataBean> beans = new ArrayList<>();

    public CustomRepository() {
        for (int i = 0; i < 100; i++) {
            DataBean bean = new DataBean();
            bean.id = i;
            bean.content = "测试数据 = " + bean.id;
            beans.add(bean);
        }
    }

    public List<DataBean> getDataInit(@IntRange(from = 0) int size) {
        return beans.subList(0, size);
    }

    public List<DataBean> getDataByRange(@IntRange(from = 0) int from, int to) {
        return beans.subList(from, to);
    }

    public List<DataBean> getDataByPage(@IntRange(from = 0) int page, int size) {
        int totalPage;
        if (beans.size() % size == 0) {
            totalPage = beans.size() / size;
        } else {
            totalPage = beans.size() / size + 1;
        }
        if (page > totalPage || page < 0) {
            return null;
        }
        if (page == totalPage - 1) {
            return beans.subList(page * size, beans.size());
        }
        return beans.subList(page * size, (page + 1) * size);
    }
}
