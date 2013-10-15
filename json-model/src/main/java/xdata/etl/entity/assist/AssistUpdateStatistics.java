
package xdata.etl.entity.assist;

import java.io.Serializable;

import xdata.etl.hbase.annotatins.HbaseTable;
import xdata.etl.hbase.entity.HbaseAttachmentList;
import xdata.etl.hbase.entity.assist.AssistEntity;
import xdata.etl.kafka.annotatins.Kafka;
import xdata.etl.kafka.transform.AssistTransformer;

import com.google.gson.annotations.SerializedName;

@HbaseTable(name = "msg_assist_update_statistics")
@Kafka(topic = "t_verupdate", transformer = AssistTransformer.class)
public class AssistUpdateStatistics
    extends AssistEntity
    implements Serializable
{

    private final static long serialVersionUID = -4465283893894165397L;
    @SerializedName("uid")
    private String uid;
    @SerializedName("oemid")
    private String oemid;
    @SerializedName("hid")
    private String hid;
    @SerializedName("packagename")
    private String packagename;
    @SerializedName("resultCode")
    private String resultCode;
    @SerializedName("resultText")
    private String resultText;
    @SerializedName("appId")
    private String appId;
    @SerializedName("xmlList")
    private HbaseAttachmentList<AssistUpdateStatisticsSrv> info = new HbaseAttachmentList<AssistUpdateStatisticsSrv>(this);
    
    

    public AssistUpdateStatistics() {
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOemid() {
        return this.oemid;
    }

    public void setOemid(String oemid) {
        this.oemid = oemid;
    }

    public String getHid() {
        return this.hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getPackagename() {
        return this.packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultText() {
        return this.resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public HbaseAttachmentList<AssistUpdateStatisticsSrv> getInfo() {
        return this.info;
    }

    public void setInfo(HbaseAttachmentList<AssistUpdateStatisticsSrv> info) {
        this.info = info;
    }


}
