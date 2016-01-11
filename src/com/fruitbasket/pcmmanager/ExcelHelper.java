package com.fruitbasket.pcmmanager;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelHelper {
	private static final ExcelHelper mExcelHelper=new ExcelHelper();
	
	private ExcelHelper(){}
	
	public static ExcelHelper getInstance(){
		return mExcelHelper;
	}
	
	public static void appendData(short[] data,File excelFile) 
			throws IOException{
		
		OutputStream os=new FileOutputStream(excelFile);
		BufferedOutputStream bos=new BufferedOutputStream(os);
		DataOutputStream output=new DataOutputStream(bos);
		
		for(int i=0;i<data.length;++i){
			output.writeBytes(i+"	"+data[i]+"\n");
		}
		output.close();
	}
}
