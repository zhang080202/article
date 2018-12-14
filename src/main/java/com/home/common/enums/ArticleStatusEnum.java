package com.home.common.enums;

public enum ArticleStatusEnum {
	
	UNREVIEWED("未审核"),
	INREVIEW("审核中"),
	CHECKED("已审核"),
	NOT_PASS("未通过");
	
	private String value;
	
	private ArticleStatusEnum(String value) {
		this.value = value;
	}
	
	public static String findValue(int key) {
		ArticleStatusEnum value = null;
		switch (key) {
		case 0:
			value = UNREVIEWED;
			break;
		case 1:
			value = INREVIEW;
			break;
		case 2:
			value = CHECKED;
			break;
		case 3:
			value = NOT_PASS;
			break;
		default:
			break;
		}
		return value.getValue();
	}

	public String getValue() {
		return value;
	}
	
}
