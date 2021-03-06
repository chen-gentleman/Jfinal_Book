package com.chen.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseBook<M extends BaseBook<M>> extends Model<M> implements IBean {

	/**
	 * 书本id
	 */
	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	/**
	 * 书本id
	 */
	public java.lang.Integer getId() {
		return getInt("id");
	}

	/**
	 * 书本名字
	 */
	public void setBookName(java.lang.String bookName) {
		set("bookName", bookName);
	}
	
	/**
	 * 书本名字
	 */
	public java.lang.String getBookName() {
		return getStr("bookName");
	}

	/**
	 * 书本图片
	 */
	public void setBookPic(java.lang.String bookPic) {
		set("bookPic", bookPic);
	}
	
	/**
	 * 书本图片
	 */
	public java.lang.String getBookPic() {
		return getStr("bookPic");
	}

	/**
	 * 作者名字
	 */
	public void setBookAuthor(java.lang.String bookAuthor) {
		set("bookAuthor", bookAuthor);
	}
	
	/**
	 * 作者名字
	 */
	public java.lang.String getBookAuthor() {
		return getStr("bookAuthor");
	}

	/**
	 * 出版社
	 */
	public void setBookPress(java.lang.String bookPress) {
		set("bookPress", bookPress);
	}
	
	/**
	 * 出版社
	 */
	public java.lang.String getBookPress() {
		return getStr("bookPress");
	}

}
