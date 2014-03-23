package org.masque.qq.demo.util;

public enum StatusCode {
	
	SUCCESS("成功","200"),
	ERROR("失败","500");
	
	private String name;
	
	private String code;
	
	private StatusCode(String name,String code){
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public static String getNameByCode(String code){
		StatusCode [] statusCodes = StatusCode.values();
		for(StatusCode statusCode :statusCodes){
			if (statusCode.code.equals(code)) {
				return statusCode.name;
			}
		}
		return null;
	}
	
	public static String getCodeByName(String name){
		StatusCode [] statusCodes = StatusCode.values();
		for(StatusCode statusCode :statusCodes){
			if (statusCode.name.equals(name)) {
				return statusCode.code;
			}
		}
		return null;
	}
}
