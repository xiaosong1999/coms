package com.dfbz.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ComsOrder {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.id
     *
     * @mbg.generated
     */
    private Integer id;

    private Integer supId;

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.stall_id
     *
     * @mbg.generated
     */
    private Integer stallId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.amount_total
     *
     * @mbg.generated
     */
    private BigDecimal amountTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.sup_name
     *
     * @mbg.generated
     */
    private String supName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column coms_order.sup_phone
     *
     * @mbg.generated
     */
    private String supPhone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.id
     *
     * @return the value of coms_order.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.id
     *
     * @param id the value for coms_order.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.stall_id
     *
     * @return the value of coms_order.stall_id
     *
     * @mbg.generated
     */
    public Integer getStallId() {
        return stallId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.stall_id
     *
     * @param stallId the value for coms_order.stall_id
     *
     * @mbg.generated
     */
    public void setStallId(Integer stallId) {
        this.stallId = stallId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.amount_total
     *
     * @return the value of coms_order.amount_total
     *
     * @mbg.generated
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.amount_total
     *
     * @param amountTotal the value for coms_order.amount_total
     *
     * @mbg.generated
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.status
     *
     * @return the value of coms_order.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.status
     *
     * @param status the value for coms_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.create_time
     *
     * @return the value of coms_order.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.create_time
     *
     * @param createTime the value for coms_order.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.update_time
     *
     * @return the value of coms_order.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.update_time
     *
     * @param updateTime the value for coms_order.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.sup_name
     *
     * @return the value of coms_order.sup_name
     *
     * @mbg.generated
     */
    public String getSupName() {
        return supName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.sup_name
     *
     * @param supName the value for coms_order.sup_name
     *
     * @mbg.generated
     */
    public void setSupName(String supName) {
        this.supName = supName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column coms_order.sup_phone
     *
     * @return the value of coms_order.sup_phone
     *
     * @mbg.generated
     */
    public String getSupPhone() {
        return supPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column coms_order.sup_phone
     *
     * @param supPhone the value for coms_order.sup_phone
     *
     * @mbg.generated
     */
    public void setSupPhone(String supPhone) {
        this.supPhone = supPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stallId=").append(stallId);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", supName=").append(supName);
        sb.append(", supPhone=").append(supPhone);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ComsOrder other = (ComsOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStallId() == null ? other.getStallId() == null : this.getStallId().equals(other.getStallId()))
            && (this.getAmountTotal() == null ? other.getAmountTotal() == null : this.getAmountTotal().equals(other.getAmountTotal()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSupName() == null ? other.getSupName() == null : this.getSupName().equals(other.getSupName()))
            && (this.getSupPhone() == null ? other.getSupPhone() == null : this.getSupPhone().equals(other.getSupPhone()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table coms_order
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStallId() == null) ? 0 : getStallId().hashCode());
        result = prime * result + ((getAmountTotal() == null) ? 0 : getAmountTotal().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSupName() == null) ? 0 : getSupName().hashCode());
        result = prime * result + ((getSupPhone() == null) ? 0 : getSupPhone().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table coms_order
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        stallId("stall_id", "stallId", "INTEGER", false),
        amountTotal("amount_total", "amountTotal", "DECIMAL", false),
        status("status", "status", "INTEGER", true),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        supName("sup_name", "supName", "VARCHAR", false),
        supPhone("sup_phone", "supPhone", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table coms_order
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}