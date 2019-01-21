package com.example.dataloader.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.dataloader.domain.Product;

public class FileService {
	 private static Logger log = LoggerFactory.getLogger(FileService.class);
	 
	public static <T> void createFileWithoutStream(Iterable<Product> products) {
		long startTime = System.currentTimeMillis();
		writeInFile(Stream.of(products).map(t -> t.toString()).collect(Collectors.toList()), Paths.get("G:\\A-Workspace\\dataFile\\product-withoutStream-"+System.currentTimeMillis()+".txt"));
		long endTime = System.currentTimeMillis();
		log.info("Time taken to generate file (without Stream) : Second "+ (endTime - startTime) );
	}
	public static void createFileWithStream(Stream<Product> resultSetStream) {
		long startTime = System.currentTimeMillis();
		writeInFile(resultSetStream.map(t -> t.toString()).collect(Collectors.toList()), Paths.get("G:\\A-Workspace\\dataFile\\product-withStream"+".txt"));
		long endTime = System.currentTimeMillis();
		log.info("Time taken to generate file (Stream) : Second "+ (endTime - startTime) );
	}
	
	private static void writeInFile(List<CharSequence> charSequenceList , Path path) {
		try {
			Files.write(path, charSequenceList, Charset.forName("UTF-8"), StandardOpenOption.WRITE,StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			log.info("Total lines of product write in file [{}]",charSequenceList.size());
		} catch (IOException e) {
			log.error("Exception occured during file writing."+ e.getMessage(),e);
		}
	}
	
	
	public static void copyFile(File sourceFile , File targetFile) {
		InputStream inputStream = null;
		OutputStream outputStream = null ; 
		try {
			inputStream = new FileInputStream(sourceFile);
			outputStream =  new FileOutputStream(targetFile);
			IOUtils.copyLarge(inputStream,outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
		}
	}


}
