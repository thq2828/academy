package com.academy.admin;

public enum  Color {
    RED(1, "红色"),GREEN(2, "绿色"), BLANK(3, "白色"), YELLO(4, "黄色");
    private int index;
    private String name;

    Color(int index, String name) {
        this.index = index;
        this.name = name;
    }
    public static String getName(int index){
        for (Color c:Color.values()){
            if(c.getIndex()==index){
                return c.name;
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(Color.getName(1));
    }
}
