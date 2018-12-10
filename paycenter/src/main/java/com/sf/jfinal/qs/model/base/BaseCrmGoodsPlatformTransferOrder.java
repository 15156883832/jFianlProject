package com.sf.jfinal.qs.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCrmGoodsPlatformTransferOrder<M extends BaseCrmGoodsPlatformTransferOrder<M>> extends Model<M> implements IBean {

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

	public void setProvince(String province) {
		set("province", province);
	}

	public String getProvince() {
		return get("province");
	}

	public void setCity(String city) {
		set("city", city);
	}

	public String getCity() {
		return get("city");
	}

	public void setArea(String area) {
		set("area", area);
	}

	public String getArea() {
		return get("area");
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

	public void setLogisticsPrice(java.math.BigDecimal logisticsPrice) {
		set("logistics_price", logisticsPrice);
	}

	public java.math.BigDecimal getLogisticsPrice() {
		return get("logistics_price");
	}

	public void setPlacingOrderBy(String placingOrderBy) {
		set("placing_order_by", placingOrderBy);
	}

	public String getPlacingOrderBy() {
		return get("placing_order_by");
	}

	public void setSiteselfOrderId(String siteselfOrderId) {
		set("siteself_order_id", siteselfOrderId);
	}

	public String getSiteselfOrderId() {
		return get("siteself_order_id");
	}

	public void setPlacingOrderTime(java.util.Date placingOrderTime) {
		set("placing_order_time", placingOrderTime);
	}

	public java.util.Date getPlacingOrderTime() {
		return get("placing_order_time");
	}

	public void setPayer(String payer) {
		set("payer", payer);
	}

	public String getPayer() {
		return get("payer");
	}

	public void setPaymentTime(java.util.Date paymentTime) {
		set("payment_time", paymentTime);
	}

	public java.util.Date getPaymentTime() {
		return get("payment_time");
	}

	public void setPayStatus(String payStatus) {
		set("pay_status", payStatus);
	}

	public String getPayStatus() {
		return get("pay_status");
	}

	public void setStatus(String status) {
		set("status", status);
	}

	public String getStatus() {
		return get("status");
	}

	public void setPaymentType(String paymentType) {
		set("payment_type", paymentType);
	}

	public String getPaymentType() {
		return get("payment_type");
	}

	public void setTradeNo(String tradeNo) {
		set("trade_no", tradeNo);
	}

	public String getTradeNo() {
		return get("trade_no");
	}

	public void setLogisticsName(String logisticsName) {
		set("logistics_name", logisticsName);
	}

	public String getLogisticsName() {
		return get("logistics_name");
	}

	public void setLogisticsNo(String logisticsNo) {
		set("logistics_no", logisticsNo);
	}

	public String getLogisticsNo() {
		return get("logistics_no");
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

	public void setSupplierId(String supplierId) {
		set("supplier_id", supplierId);
	}

	public String getSupplierId() {
		return get("supplier_id");
	}

	public void setConfirmTime(java.util.Date confirmTime) {
		set("confirm_time", confirmTime);
	}

	public java.util.Date getConfirmTime() {
		return get("confirm_time");
	}

	public void setSendgoodTime(java.util.Date sendgoodTime) {
		set("sendgood_time", sendgoodTime);
	}

	public java.util.Date getSendgoodTime() {
		return get("sendgood_time");
	}

	public void setFinishTime(java.util.Date finishTime) {
		set("finish_time", finishTime);
	}

	public java.util.Date getFinishTime() {
		return get("finish_time");
	}

	public void setPayConfirm(String payConfirm) {
		set("pay_confirm", payConfirm);
	}

	public String getPayConfirm() {
		return get("pay_confirm");
	}

	public void setNoPassTime(java.util.Date noPassTime) {
		set("no_pass_time", noPassTime);
	}

	public java.util.Date getNoPassTime() {
		return get("no_pass_time");
	}

	public void setNoPassSource(String noPassSource) {
		set("no_pass_source", noPassSource);
	}

	public String getNoPassSource() {
		return get("no_pass_source");
	}

}
