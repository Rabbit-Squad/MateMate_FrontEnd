package org.matemate.Participate.Approvement;
import com.google.gson.annotations.SerializedName;
// 승인 || 거부 할 때 보낼 approval data..

public class ApproveData {
    @SerializedName("approval")
    private int approval;

    public ApproveData(int approval) {
        this.approval = approval;
    }

    public int getApproval() {
        return approval;
    }
}
