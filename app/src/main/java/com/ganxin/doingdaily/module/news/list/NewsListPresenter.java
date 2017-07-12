package com.ganxin.doingdaily.module.news.list;

import com.ganxin.doingdaily.common.data.model.NewsContentlistBean;
import com.ganxin.doingdaily.common.data.model.NewsContent;
import com.ganxin.doingdaily.common.data.source.callback.NewsDataSource;
import com.ganxin.doingdaily.common.data.source.NewsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : NewsPresenter  <br/>
 * author : WangGanxin <br/>
 * date : 2016/11/4 <br/>
 * email : ganxinvip@163.com <br/>
 */
public class NewsListPresenter extends NewsListContract.Presenter {

    @Override
    public void onStart() {

    }

    @Override
    protected void getListContent(String channelId, final int pageIndex) {
        Map<String, String> options = new HashMap<>();
        options.put("channelId", channelId);
        options.put("page", String.valueOf(pageIndex));
        options.put("needContent", "1");
        options.put("needHtml", "1");
        options.put("needAllList", "0");
        options.put("maxResult", "20");

        NewsRepository.getInstance().getChannelContent(options, new NewsDataSource.GetNewsContentCallback() {
            @Override
            public void onNewsContentLoaded(NewsContent newsContent) {
                if (newsContent != null) {
                    List<NewsContentlistBean> contentlistBeanList = newsContent.getShowapi_res_body().getPagebean().getContentlist();

                    if (contentlistBeanList != null && contentlistBeanList.size() > 0) {
                        if (pageIndex == 1) {
                            getView().refreshContentList(contentlistBeanList);
                        } else {
                            getView().addContentList(contentlistBeanList);
                        }
                    }
                }
                getView().loadComplete();
            }

            @Override
            public void onDataNotAvailable() {
                getView().loadComplete();
            }
        });
    }
}
