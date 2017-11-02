package com.repair.proj.customView;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by HX·罗 on 2017/7/14.
 */

public class CustomRecyclerView extends RecyclerView {

    private boolean isLoading = true ;
    private int currentPage = 1;

    public void resetPage(){
        currentPage = 1 ;
    }
    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public interface LoadMoreListener{
        void onLoadMore(int currentPage) ;
    }

    private LoadMoreListener loadMoreListener ;

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public CustomRecyclerView(Context context) {
        this(context,null) ;
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        addItemDecoration(new MyItemDecoration());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context){
            @Override
            protected int getExtraLayoutSpace(State state) {
                return 20;
            }
        } ;

        setLayoutManager(linearLayoutManager);
        try{
            addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int currentPage) {
                    if(loadMoreListener != null){
                        loadMoreListener.onLoadMore(currentPage);
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public abstract class EndlessRecyclerOnScrollListener extends OnScrollListener {
        int totalItemCount,lastVisibleItem;

        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerOnScrollListener(
                LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            totalItemCount = mLinearLayoutManager.getItemCount();
            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition() ;
            if (!isLoading
                    && lastVisibleItem == totalItemCount-1) {
                isLoading = true ;
                currentPage++;
                onLoadMore(currentPage);
            }
        }

        public abstract void onLoadMore(int currentPage);
    }
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        /**
         *
         * @param outRect 边界
         * @param view recyclerView ItemView
         * @param parent recyclerView
         * @param state recycler 内部数据管理
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //设定底部边距为1px
            outRect.set(0, 0, 0, 20);
        }
    }
}
