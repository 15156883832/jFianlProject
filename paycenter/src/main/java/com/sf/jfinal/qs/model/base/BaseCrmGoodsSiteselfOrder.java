package com.sf.jfinal.qs.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCrmGoodsSiteselfOrder<M extends BaseCrmGoodsSiteselfOrder<M>> extends Model<M> implements IBean {

	public void setId(String id) {
		set("id", id);
	}

	public String getId() {
		return get("id");
	}

	public void setNumber(String number) {
		set("number", number);
	}

	public String getNumber() {
		return get("number");
	}

	public void setGoodId(String goodId) {
		set("good_id", goodId);
	}

	public String getGoodId() {
		return get("good_id");
	}

	public void setGoodNumber(String goodNumber) {
		set("good_number", goodNumber);
	}

	public String getGoodNumber() {
		return get("good_number");
	}

	public void setGoodName(String goodName) {
		set("good_name", goodName);
	}

	public String getGoodName() {
		return get("good_name");
	}

	public void setGoodIcon(String goodIcon) {
		set("good_icon", goodIcon);
	}

	public String getGoodIcon() {
		return get("good_icon");
	}

	public void setGoodBrand(String goodBrand) {
		set("good_brand", goodBrand);
	}

	public String getGoodBrand() {
		return get("good_brand");
	}

	public void setGoodModel(String goodModel) {
		set("good_model", goodModel);
	}

	public String getGoodModel() {
		return get("good_model");
	}

	public void setGoodCategory(String goodCategory) {
		set("good_category", goodCategory);
	}

	public String getGoodCategory() {
		return get("good_category");
	}

	public void setGoodSource(String goodSource) {
		set("good_source", goodSource);
	}

	public String getGoodSource() {
		return get("good_source");
	}

	public void setCustomerName(String customerName) {
		set("customer_name", customerName);
	}

	public String getCustomerName() {
		return get("customer_name");
	}

	public void setCustomerContact(String customerContact) {
		set("customer_contact", customerContact);
	}

	public String getCustomerContact() {
		return get("customer_contact");
	}

	public void setCustomerAddress(String customerAddress) {
		set("customer_address", customerAddress);
	}

	public String getCustomerAddress() {
		return get("customer_address");
	}

	public void setPurchaseNum(java.math.BigDecimal purchaseNum) {
		set("purchase_num", purchaseNum);
	}

	public java.math.BigDecimal getPurchaseNum() {
		return get("purchase_num");
	}

	public void setGoodAmount(java.math.BigDecimal goodAmount) {
		set("good_amount", goodAmount);
	}

	public java.math.BigDecimal getGoodAmount() {
		return get("good_amount");
	}

	public void setRealAmount(java.math.BigDecimal realAmount) {
		set("real_amount", realAmount);
	}

	public java.math.BigDecimal getRealAmount() {
		return get("real_amount");
	}

	public void setConfirmAmount(java.math.BigDecimal confirmAmount) {
		set("confirm_amount", confirmAmount);
	}

	public java.math.BigDecimal getConfirmAmount() {
		return get("confirm_amount");
	}

	public void setSalesCommissions(java.math.BigDecimal salesCommissions) {
		set("sales_commissions", salesCommissions);
	}

	public java.math.BigDecimal getSalesCommissions() {
		return get("sales_commissions");
	}

	public void setPlacingOrderBy(String placingOrderBy) {
		set("placing_order_by", placingOrderBy);
	}

	public String getPlacingOrderBy() {
		return get("placing_order_by");
	}

	public void setPlacingName(String placingName) {
		set("placing_name", placingName);
	}

	public String getPlacingName() {
		return get("placing_name");
	}

	public void setPlacingOrderTime(java.util.Date placingOrderTime) {
		set("placing_order_time", placingOrderTime);
	}

	public java.util.Date getPlacingOrderTime() {
		return get("placing_order_time");
	}

	public void setOutstockType(String outstockType) {
		set("outstock_type", outstockType);
	}

	public String getOutstockType() {
		return get("outstock_type");
	}

	public void setConfirmBy(String confirmBy) {
		set("confirm_by", confirmBy);
	}

	public String getConfirmBy() {
		return get("confirm_by");
	}

	public void setConfirmor(String confirmor) {
		set("confirmor", confirmor);
	}

	public String getConfirmor() {
		return get("confirmor");
	}

	public void setConfirmTime(java.util.Date confirmTime) {
		set("confirm_time", confirmTime);
	}

	public java.util.Date getConfirmTime() {
		return get("confirm_time");
	}

	public void setOutstockTime(java.util.Date outstockTime) {
		set("outstock_time", outstockTime);
	}

	public java.util.Date getOutstockTime() {
		return get("outstock_time");
	}

	public void setStatus(String status) {
		set("status", status);
	}

	public String getStatus() {
		return get("status");
	}

	public void setSiteId(String siteId) {
		set("site_id", siteId);
	}

	public String getSiteId() {
		return get("site_id");
	}

	public void setCreator(String creator) {
		set("creator", creator);
	}

	public String getCreator() {
		return get("creator");
	}

}