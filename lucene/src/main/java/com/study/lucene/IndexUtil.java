package com.study.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexReader.FieldOption;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class IndexUtil {

	private String[] ids = { "1", "2", "3", "4", "5", "6" };
	private String[] emails = { "aa@itat.org", "bb@itat.org", "cc@itat.org", "dd@itat.org", "ee@itat.org", "ff@itat.org" };
	private String[] contents = { "welcome to visited the space", "hello", "my name is cc", "like football", "dasda", "dqwww" };
	private int[] attachs = { 2, 1, 3, 4, 2, 4 };
	private String[] names = { "zhangsan", "lisi", "yuee", "yetty", "mike", "tom" };
	private Directory directory = null;

	public IndexUtil() {
		try {
			directory = FSDirectory.open(new File("D:/lucene_index/index02"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void index() {
		IndexWriter writer = null;

		try {
			writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));

			Document doc = null;
			for (int i = 0; i < ids.length; i++) {

				doc = new Document();
				doc.add(new Field("id", ids[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				doc.add(new Field("email", emails[i], Field.Store.YES, Field.Index.NOT_ANALYZED));
				doc.add(new Field("content", contents[i], Field.Store.NO, Field.Index.ANALYZED));
				doc.add(new Field("name", names[i], Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));

				writer.addDocument(doc);
			}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void query(){
		
		try {
			IndexReader reader = IndexReader.open(directory);
			System.out.println("numDocs " +reader.numDocs());
			System.out.println("maxDoc " +reader.maxDoc());
			
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
