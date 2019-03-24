package com.cxh.jdbc.enums;

public enum DataBaseType {
    MYSQL("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/cxh?characterEncoding=utf8&useSSL=false&autoReconnect=true"),
    ORACLE("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:orcl");
    private String driveClass;
    private String url;



    public String getDriveClass() {
        return driveClass;
    }

    public void setDriveClass(String driveClass) {
        this.driveClass = driveClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    DataBaseType(String driveClass, String url) {
        this.driveClass = driveClass;
        this.url = url;
    }
}
