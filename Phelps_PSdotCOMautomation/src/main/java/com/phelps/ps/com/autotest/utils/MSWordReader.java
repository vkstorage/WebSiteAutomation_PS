package com.phelps.ps.com.autotest.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class MSWordReader {

	/**
	 * @param args
	 */
	public List<String> readMyDocument(String filePath) {
	
		
		List<String> CLPfileText = new ArrayList<String>();
		POIFSFileSystem fs = null;
		try {
			fs = new POIFSFileSystem(new FileInputStream(filePath));
			HWPFDocument doc = new HWPFDocument(fs);

			WordExtractor we = new WordExtractor(doc);

			String[] paragraphs = we.getParagraphText();

			/*
			 * code to read pictures
			 * Range range = doc.getRange(); 
			 * PicturesTable pt =doc.getPicturesTable(); 
			 * List<Picture> listPic = pt.getAllPictures();
			 * Picture pic = listPic.get(0);  // iterate listPic upto its size to get
			 * images byte[] picArr = pic.getContent();
			 */

			System.out.println("Word Document has " + paragraphs.length + " paragraphs");
			for (int i = 0; i < paragraphs.length; i++) {
				System.out.println("" + paragraphs[i]);
				
				CLPfileText.add(paragraphs[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return CLPfileText;
	}
}
