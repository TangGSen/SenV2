package com.sen.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class ClassChildItemBean {

    private String url;
    private String des;
    private List<ClassChildItemBean> data;
    public ClassChildItemBean(String url,String des){
        this.url = url;
        this.des = des;
    }

    public ClassChildItemBean(){
        data = new ArrayList<ClassChildItemBean>();
        data.add(new ClassChildItemBean("http://img4.duitang.com/uploads/item/201112/03/20111203202432_BPPR3.thumb.600_0.jpg","555"));
        data.add(new ClassChildItemBean("http://img5.duitang.com/uploads/item/201407/31/20140731223506_Hx4PM.thumb.700_0.jpeg","555"));
        data.add(new ClassChildItemBean("http://pic.nipic.com/2007-11-14/20071114232440935_2.jpg","555"));
        data.add(new ClassChildItemBean("http://img4.duitang.com/uploads/item/201405/06/20140506001247_sZeQV.thumb.600_0.jpeg","555"));
        data.add(new ClassChildItemBean("http://img0.imgtn.bdimg.com/it/u=2900221769,2653773917&fm=21&gp=0.jpg","555"));
        data.add(new ClassChildItemBean("http://img4.imgtn.bdimg.com/it/u=3770158393,1662384103&fm=21&gp=0.jpg","555"));
        data.add(new ClassChildItemBean("http://img4.imgtn.bdimg.com/it/u=874107384,3107805724&fm=21&gp=0.jpg","555"));
        data.add(new ClassChildItemBean("http://cdn.duitang.com/uploads/item/201201/01/20120101115642_rfn2t.jpg","555"));
        data.add(new ClassChildItemBean("http://img4.imgtn.bdimg.com/it/u=2248447279,928629987&fm=21&gp=0.jpg","555"));
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }



    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
