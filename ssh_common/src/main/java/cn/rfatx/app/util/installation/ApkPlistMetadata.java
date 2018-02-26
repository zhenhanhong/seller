package cn.rfatx.app.util.installation;

/**
 * Apk参数元信息的数据
 */
public class ApkPlistMetadata {
    private String packageName;
    private String discription;
    private String versionNum;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getVersionNum() {
        return this.versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }
}

