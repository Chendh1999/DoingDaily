package com.ganxin.doingdaily.module.main;

import com.ganxin.doingdaily.framework.BasePresenter;
import com.ganxin.doingdaily.framework.BaseView;

/**
 * Description : 契约类  <br/>
 * author : WangGanxin <br/>
 * date : 2016/10/28 <br/>
 * email : ganxinvip@163.com <br/>
 */
interface MainContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getData(String content);
    }
}