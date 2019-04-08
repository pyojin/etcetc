package com.lucy.plants.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExcelDto {
 private String send_name;
 private String send_tel;
 private String send_post;
 private String send_addr1;
 private String send_addr2;
 private String receive_name;	
 private String receive_tel;	
 private String receive_post;	
 private String receive_addr1;
 private String receive_addr2;
 private String goods_name;	
 private int goods_price;
 @JsonFormat(pattern="yyyy-MM-dd")
 private Date pickup_date;
 @JsonFormat(pattern="yyyy-MM-dd")
 private Date order_date;
public String getSend_name() {
	return send_name;
}
public void setSend_name(String send_name) {
	this.send_name = send_name;
}
public String getSend_tel() {
	return send_tel;
}
public void setSend_tel(String send_tel) {
	this.send_tel = send_tel;
}
public String getSend_post() {
	return send_post;
}
public void setSend_post(String send_post) {
	this.send_post = send_post;
}
public String getSend_addr1() {
	return send_addr1;
}
public void setSend_addr1(String send_addr1) {
	this.send_addr1 = send_addr1;
}
public String getSend_addr2() {
	return send_addr2;
}
public void setSend_addr2(String send_addr2) {
	this.send_addr2 = send_addr2;
}
public String getReceive_name() {
	return receive_name;
}
public void setReceive_name(String receive_name) {
	this.receive_name = receive_name;
}
public String getReceive_tel() {
	return receive_tel;
}
public void setReceive_tel(String receive_tel) {
	this.receive_tel = receive_tel;
}
public String getReceive_post() {
	return receive_post;
}
public void setReceive_post(String receive_post) {
	this.receive_post = receive_post;
}
public String getReceive_addr1() {
	return receive_addr1;
}
public void setReceive_addr1(String receive_addr1) {
	this.receive_addr1 = receive_addr1;
}
public String getReceive_addr2() {
	return receive_addr2;
}
public void setReceive_addr2(String receive_addr2) {
	this.receive_addr2 = receive_addr2;
}
public String getGoods_name() {
	return goods_name;
}
public void setGoods_name(String goods_name) {
	this.goods_name = goods_name;
}
public int getGoods_price() {
	return goods_price;
}
public void setGoods_price(int goods_price) {
	this.goods_price = goods_price;
}
public Date getPickup_date() {
	return pickup_date;
}
public void setPickup_date(Date pickup_date) {
	this.pickup_date = pickup_date;
}
public Date getOrder_date() {
	return order_date;
}
public void setOrder_date(Date order_date) {
	this.order_date = order_date;
}
 
 
 
}
