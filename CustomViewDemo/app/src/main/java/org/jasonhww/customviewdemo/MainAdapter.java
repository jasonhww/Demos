package org.jasonhww.customviewdemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<MainViewBean, BaseViewHolder> {
    public MainAdapter(List<MainViewBean> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainViewBean item) {
        helper.setText(R.id.itemTitle, item.getTitle());
    }
}
