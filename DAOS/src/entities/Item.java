package entities;
public class Item {
    Integer id;
    String name;
    String desc;

    // getters and setters for each variable

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString(){
        return "Item [ID = "+ id + " Name = "+ name +" description = "+ desc+ "]";
    }
}
