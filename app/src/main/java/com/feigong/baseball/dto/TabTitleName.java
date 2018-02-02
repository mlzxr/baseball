package com.feigong.baseball.dto;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by ruler on 18/2/1.
 * TAB头标题
 *
 */

@Entity
public class TabTitleName {

    @Id
    private String id;

    private String titleType;
    private String titleName;

    private String titleCode;
    private int titleVersion;
    @Generated(hash = 799656839)
    public TabTitleName(String id, String titleType, String titleName,
            String titleCode, int titleVersion) {
        this.id = id;
        this.titleType = titleType;
        this.titleName = titleName;
        this.titleCode = titleCode;
        this.titleVersion = titleVersion;
    }
    @Generated(hash = 2102576007)
    public TabTitleName() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitleType() {
        return this.titleType;
    }
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }
    public String getTitleName() {
        return this.titleName;
    }
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
    public String getTitleCode() {
        return this.titleCode;
    }
    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }
    public int getTitleVersion() {
        return this.titleVersion;
    }
    public void setTitleVersion(int titleVersion) {
        this.titleVersion = titleVersion;
    }



}
