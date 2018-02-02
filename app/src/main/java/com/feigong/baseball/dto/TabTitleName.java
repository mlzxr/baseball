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

    private String titleType;
    private String titleName;
    @Id
    private String titleCode;
    private int titleVersion;
    @Generated(hash = 522390529)
    public TabTitleName(String titleType, String titleName, String titleCode,
            int titleVersion) {
        this.titleType = titleType;
        this.titleName = titleName;
        this.titleCode = titleCode;
        this.titleVersion = titleVersion;
    }
    @Generated(hash = 2102576007)
    public TabTitleName() {
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
