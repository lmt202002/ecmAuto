package common;

public class UserRelationsData {
    public String wx="";
    public String name="";
    public String phone="";
    public String parent="";
    public String reference="";


    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public static String[][] userData(){

//初始化14个用户,每个用户五个属性顺序为：微信号、姓名、手机号码、上级、推荐人
         String[][] users={
                 {"atds1","at董事","18111110001","总部",""},
                 {"atzc1","at总裁1","18222220001","at董事",""},
                 {"atmx1","at梦想1","18333330001","at总裁1",""},
                 {"atyj1","at一级1","18444440001","at梦想1",""},
                 {"atds2","at董事2","19111110001","总部","atds1"},
                 {"atzc2","at总裁2","18222220002","at董事","atzc1"},
                 {"atzc3","at总裁3","18222220003","at董事","atzc2"},
                 {"atzc4","at总裁4","18222220004","at董事","atzc3"},
                 {"atmx2","at梦想2","18333330002","at总裁1","atmx1"},
                 {"atmx3","at梦想3","18333330003","at总裁1","atmx2"},
                 {"atmx4","at梦想4","18333330004","at总裁1","atmx3"},
                 {"atyj2","at一级2","18444440002","at梦想1","atyj1"},
                 {"atyj3","at一级3","18444440003","at梦想1","atyj2"},
                 {"atyj4","at一级4","18444440004","at梦想1","atyj3"},
         };

        return users;
    }

}
