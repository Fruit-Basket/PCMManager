package com.fruitbasket.pcmmanager;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PCMHelper {
	private static PCMHelper mPCMHelper = new PCMHelper();

	public static final int PCM_16BIT = 1000;
	public static final int PCM_8BIT = 1001;

	private PCMHelper() {
	}

	public static PCMHelper getInstance() {
		return mPCMHelper;
	}

	public static boolean shortPCMToExcel(File pcmFile, File excelFile)
			throws IOException {
		InputStream is = new FileInputStream(pcmFile);
		BufferedInputStream bis = new BufferedInputStream(is);
		DataInputStream input = new DataInputStream(bis);

		long pcmLength = (pcmFile.length() / 2) + 1;
		short[] data;

		int i;
		if (pcmLength < Integer.MAX_VALUE) {
			data = new short[(int) pcmLength];
			i = 0;
			while (input.available() > 0) {// i<data.length must be true, when
											// input.available()>0 is true
				data[i] = input.readShort();
				++i;
			}
			ExcelHelper.appendData(data, excelFile);
		} else {
			data = new short[Integer.MAX_VALUE];
			do {
				pcmLength -= Integer.MAX_VALUE;
				i = 0;
				while (i < data.length) {// input.available()>0 must be true,
											// when i<data.length is true
					data[i] = input.readShort();
					i++;
				}
				ExcelHelper.appendData(data, excelFile);
			} while (pcmLength > Integer.MAX_VALUE);

			i = 0;
			while (i < pcmLength) {
				data[i] = input.readShort();
				i++;
			}
			ExcelHelper.appendData(data, excelFile);
		}
		input.close();
		return true;
	}
}
