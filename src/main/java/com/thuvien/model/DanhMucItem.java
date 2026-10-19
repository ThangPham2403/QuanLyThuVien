package com.thuvien.model;

public class DanhMucItem {

    private String id;
    private String name;

    public DanhMucItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        // JComboBox hiển thị tên, nhưng vẫn giữ mã bên trong
        return name;
    }
}