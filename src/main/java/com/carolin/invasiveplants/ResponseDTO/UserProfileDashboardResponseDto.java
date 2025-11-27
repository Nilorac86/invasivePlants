package com.carolin.invasiveplants.ResponseDTO;

import java.util.List;

public class UserProfileDashboardResponseDto {

    private Integer points;
    private long pendingTotal;
    private long approvedTotal;
    private long giftsTotal;
    private long reportedTotal;

    private List<UserRemovedPlantsStatusResponseDto> pendingPreview;
    private List<UserRemovedPlantsStatusResponseDto> approvedPreview;
    private List<RewardPreviewResponseDto> giftsPreview;
    private List<ReportedPreviewResponceDto> reportedPreview;

    public UserProfileDashboardResponseDto() {
    }

    public UserProfileDashboardResponseDto(Integer points, long pendingTotal, long approvedTotal, long giftsTotal,
                                           long reportedTotal, List<UserRemovedPlantsStatusResponseDto> pendingPreview,
                                           List<UserRemovedPlantsStatusResponseDto> approvedPreview,
                                           List<RewardPreviewResponseDto> giftsPreview, List<ReportedPreviewResponceDto> reportedPreview) {
        this.points = points;
        this.pendingTotal = pendingTotal;
        this.approvedTotal = approvedTotal;
        this.giftsTotal = giftsTotal;
        this.reportedTotal = reportedTotal;
        this.pendingPreview = pendingPreview;
        this.approvedPreview = approvedPreview;
        this.giftsPreview = giftsPreview;
        this.reportedPreview = reportedPreview;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public long getPendingTotal() {
        return pendingTotal;
    }

    public void setPendingTotal(long pendingTotal) {
        this.pendingTotal = pendingTotal;
    }

    public long getApprovedTotal() {
        return approvedTotal;
    }

    public void setApprovedTotal(long approvedTotal) {
        this.approvedTotal = approvedTotal;
    }

    public long getGiftsTotal() {
        return giftsTotal;
    }

    public void setGiftsTotal(long giftsTotal) {
        this.giftsTotal = giftsTotal;
    }

    public long getReportedTotal() {
        return reportedTotal;
    }

    public void setReportedTotal(long reportedTotal) {
        this.reportedTotal = reportedTotal;
    }

    public List<UserRemovedPlantsStatusResponseDto> getPendingPreview() {
        return pendingPreview;
    }

    public void setPendingPreview(List<UserRemovedPlantsStatusResponseDto> pendingPreview) {
        this.pendingPreview = pendingPreview;
    }

    public List<UserRemovedPlantsStatusResponseDto> getApprovedPreview() {
        return approvedPreview;
    }

    public void setApprovedPreview(List<UserRemovedPlantsStatusResponseDto> approvedPreview) {
        this.approvedPreview = approvedPreview;
    }

    public List<RewardPreviewResponseDto> getGiftsPreview() {
        return giftsPreview;
    }

    public void setGiftsPreview(List<RewardPreviewResponseDto> giftsPreview) {
        this.giftsPreview = giftsPreview;
    }

    public List<ReportedPreviewResponceDto> getReportedPreview() {
        return reportedPreview;
    }

    public void setReportedPreview(List<ReportedPreviewResponceDto> reportedPreview) {
        this.reportedPreview = reportedPreview;
    }
}
