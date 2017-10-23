package com.repair.proj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        rv = findViewById(R.id.rv);
        GridLayoutManager lm = new GridLayoutManager(this,1) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(lm);
        rv.setAdapter(new MyAdapter(this));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        MainActivity activity;

        public MyAdapter(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = activity.getLayoutInflater().inflate(R.layout.activity_register_flow_line, null, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 4;
        }

        class MyHolder extends RecyclerView.ViewHolder {

            public MyHolder(View itemView) {
                super(itemView);
            }
        }
    }


}
