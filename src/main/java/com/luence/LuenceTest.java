package com.luence;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class LuenceTest {

	/**
	 * 简历索引
	 */
	@Test
	public void index() {

		// 1,创建Directory
		Directory directory = null;
		try {
			directory = FSDirectory.open(new File("d:/lucene_index/index01"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 2,创建IndexWriter
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(directory, iwc);

			Document doc = null;

			File file = new File("d:/lucene");
			for (File f : file.listFiles()) {
				doc = new Document();
				doc.add(new Field("content", new FileReader(f)));
				doc.add(new Field(("filename"), f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field(("filepath"), f.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				
				writer.addDocument(doc);
			}
		//	writer.commit();

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// 3,创建Document对象

		// 4,为Document添加Field

	}

}
