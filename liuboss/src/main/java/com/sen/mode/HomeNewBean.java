package com.sen.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sen on 2016/2/22.
 */
public class HomeNewBean {

    private String Name;
    private String id;
    private List data;

    public HomeNewBean(){
        data = new ArrayList<String>();
        data.add("http://img4.duitang.com/uploads/item/201112/03/20111203202432_BPPR3.thumb.600_0.jpg");
        data.add("http://img5.duitang.com/uploads/item/201407/31/20140731223506_Hx4PM.thumb.700_0.jpeg");
        data.add("http://pic.nipic.com/2007-11-14/20071114232440935_2.jpg");
        data.add("http://img4.duitang.com/uploads/item/201405/06/20140506001247_sZeQV.thumb.600_0.jpeg");
        data.add("http://img0.imgtn.bdimg.com/it/u=2900221769,2653773917&fm=21&gp=0.jpg");
        data.add("http://img4.imgtn.bdimg.com/it/u=3770158393,1662384103&fm=21&gp=0.jpg");
        data.add("http://img4.imgtn.bdimg.com/it/u=874107384,3107805724&fm=21&gp=0.jpg");
        data.add("http://cdn.duitang.com/uploads/item/201201/01/20120101115642_rfn2t.jpg");
        data.add("http://img4.imgtn.bdimg.com/it/u=2248447279,928629987&fm=21&gp=0.jpg");
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
