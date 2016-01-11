package com.fruitbasket.pcmmanager;

public class Condition {
	private static final Condition mCondition=new Condition();
	
	private static final String PCM_FILE_NAME="pcm.pcm";
	private static final String EXCEL_FILE_NAME="excel.xls";
	
	private static String pcmFileName=PCM_FILE_NAME;
	private static String excelFileName=EXCEL_FILE_NAME;
	
	private Condition(){}
	
	public static Condition getInstance(){
		return mCondition;
	}
	
	public static String getPcmFileName(){
		return pcmFileName;
	}
	
	public static String getExcelFileName(){
		return excelFileName;
	}
	
	public static void setPcmFileName(String pcmFileName){
		Condition.pcmFileName=pcmFileName;
	}
	
	public static void setExcelFileName(String excelFileName){
		Condition.excelFileName=excelFileName;
	}
}
