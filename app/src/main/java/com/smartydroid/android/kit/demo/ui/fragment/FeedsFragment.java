/**
 * Created by YuGang Yang on October 28, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package com.smartydroid.android.kit.demo.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.smartydroid.android.kit.demo.api.ApiService;
import com.smartydroid.android.kit.demo.api.service.FeedService;
import com.smartydroid.android.kit.demo.model.entity.Feed;
import com.smartydroid.android.kit.demo.ui.viewholder.FeedsViewHolder;
import com.smartydroid.android.starter.kit.app.StarterPagedFragment;
import java.util.ArrayList;
import retrofit2.Call;

public class FeedsFragment extends StarterPagedFragment<Feed> {

  private FeedService mFeedService;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFeedService = ApiService.createFeedService();
  }

  @Override public Call<ArrayList<Feed>> paginate(int page, int perPage) {
    return mFeedService.getFeedList(page, perPage);
  }

  @Override public Object getKeyForData(Feed item) {
    return item.id;
  }

  @Override public void bindViewHolders(EasyRecyclerAdapter adapter) {
    adapter.bind(Feed.class, FeedsViewHolder.class);
  }

  @Override public void onItemClick(int position, View view) {
    super.onItemClick(position, view);
    final Feed feed = getItem(position);
    Toast.makeText(getContext(), feed.content, Toast.LENGTH_SHORT).show();
  }
}