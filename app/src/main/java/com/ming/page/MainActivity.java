package com.ming.page;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.ming.page.adapter.MyAdapter;
import com.ming.page.bean.DataBean;
import com.ming.page.factory.CustomPageDataSourceFactory;
import com.ming.page.repository.CustomRepository;
import com.ming.page.utils.RecyclerViewDivider;

public class MainActivity extends AppCompatActivity {

    private PagedList.Config config;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        CustomRepository customRepository = new CustomRepository();
        LiveData<PagedList<DataBean>> liveData = new LivePagedListBuilder(/*new MyDataSourceFactory()*/new CustomPageDataSourceFactory(customRepository), config).build();
        liveData.observe(this, mAdapter::submitList);

        RecyclerView recyclerView = findViewById(R.id.rv_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new RecyclerViewDivider(1, 0xFFDCDCDC));
        recyclerView.setAdapter(mAdapter);
    }

    private void initList() {
        mAdapter = new MyAdapter(mDiffCallback);
        config = new PagedList.Config.Builder()
                .setPageSize(10) //配置分页加载的数量
                .setEnablePlaceholders(false) //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(10)//初始化加载的数量
                .setPrefetchDistance(3)//设置距离最后还有多少个item的时候即自动加载下一页
                .setEnablePlaceholders(true)//是否设置null占位符
                .build();
    }

    public DiffUtil.ItemCallback<DataBean> mDiffCallback = new DiffUtil.ItemCallback<DataBean>() {
        @Override
        public boolean areItemsTheSame(DataBean oldItem, DataBean newItem) {
            return oldItem.id == newItem.id;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(DataBean oldItem, DataBean newItem) {
            return (oldItem == newItem);
        }
    };
}
