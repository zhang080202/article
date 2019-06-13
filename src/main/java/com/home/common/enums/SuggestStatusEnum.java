package com.home.common.enums;

/**
 * 意见反馈枚举类
 * @author Fly
 *
 */
public enum SuggestStatusEnum {
	
	UNRESOLVED("未解决"),
	RESOLVED("已解决"),
	INVALID("无效建议");
	
	private String value;
	
	private SuggestStatusEnum(String value) {
		this.value = value;
	}	
	
	public static String getValue(int key) {
		SuggestStatusEnum value = null;
		switch (key) {
		case 0:
			value = UNRESOLVED;
			break;
		case 1:
			value = RESOLVED;
			break;
		case 2:
			value = INVALID;
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
