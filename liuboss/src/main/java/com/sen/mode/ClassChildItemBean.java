package com.sen.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class ClassChildItemBean {

    private String imgUrl;
    private String desTxt;
    private float priceTxt;
    private int loveCount;
    private List<ClassChildItemBean> data;

    public ClassChildItemBean(String url, String des, float priceTxt, int loveCount) {
        this.imgUrl = url;
        this.desTxt = des;
        this.priceTxt = priceTxt;
        this.loveCount = loveCount;
    }

    public ClassChildItemBean() {
        data = new ArrayList<ClassChildItemBean>();
        data.add(new ClassChildItemBean("http://img4.duitang.com/uploads/item/201112/03/20111203202432_BPPR3.thumb.600_0.jpg", "m2555",27.5f,183));
        data.add(new ClassChildItemBean("http://img5.duitang.com/uploads/item/201407/31/20140731223506_Hx4PM.thumb.700_0.jpeg", "d555",28.5f,183));
        data.add(new ClassChildItemBean("http://pic.nipic.com/2007-11-14/20071114232440935_2.jpg", "555",38.5f,183));
        data.add(new ClassChildItemBean("http://img4.duitang.com/uploads/item/201405/06/20140506001247_sZeQV.thumb.600_0.jpeg", "f555",28.9f,183));
        data.add(new ClassChildItemBean("http://img0.imgtn.bdimg.com/it/u=2900221769,2653773917&fm=21&gp=0.jpg", "t555",28.9f,183));
        data.add(new ClassChildItemBean("http://img4.imgtn.bdimg.com/it/u=3770158393,1662384103&fm=21&gp=0.jpg", "y555",27.9f,183));
        data.add(new ClassChildItemBean("http://img4.imgtn.bdimg.com/it/u=874107384,3107805724&fm=21&gp=0.jpg", "g555",29.9f,183));
        data.add(new ClassChildItemBean("http://cdn.duitang.com/uploads/item/201201/01/20120101115642_rfn2t.jpg", "h555",49.9f,183));
        data.add(new ClassChildItemBean("http://img4.imgtn.bdimg.com/it/u=2248447279,928629987&fm=21&gp=0.jpg", "j555",59.9f,183));
    }

    public float getPriceTxt() {
        return priceTxt;
    }

    public void setPriceTxt(float priceTxt) {
        this.priceTxt = priceTxt;
    }

    public int getLoveCount() {
        return loveCount;
    }

    public void setLoveCount(int loveCount) {
        this.loveCount = loveCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesTxt() {
        return desTxt;
    }

    public void setDesTxt(String desTxt) {
        this.desTxt = desTxt;
    }

    public List<ClassChildItemBean> getData() {
        return data;
    }

    public void setData(List<ClassChildItemBean> data) {
        this.data = data;
    }
}
