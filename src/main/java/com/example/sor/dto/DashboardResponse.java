
package com.example.sor.dto;

public class DashboardResponse {
    private long totalEmployees;
    private long totalDepartments;
    private long totalUsers;

    public DashboardResponse() {
    }

    public DashboardResponse(long totalEmployees, long totalDepartments, long totalUsers) {
        this.totalEmployees = totalEmployees;
        this.totalDepartments = totalDepartments;
        this.totalUsers = totalUsers;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }
}
