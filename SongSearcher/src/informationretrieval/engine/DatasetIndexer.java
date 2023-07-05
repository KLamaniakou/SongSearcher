package informationretrieval.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
public class DatasetIndexer {
	
	private IndexWriter writer;

	public DatasetIndexer() {
	}
	
	public void createIndex() throws IOException {
		System.out.println("Indexing process started!");
		Directory indexDirectory = FSDirectory.open(FileSystems.getDefault().getPath(LuceneConstants.INDEX_PATH));
		IndexWriterConfig indexConfig = new IndexWriterConfig(new StandardAnalyzer());
		writer = new IndexWriter(indexDirectory, indexConfig);
		File datasetdir = new File(LuceneConstants.COLLECTION_PATH);
		for (File file : datasetdir.listFiles())
			indexFile(file);
		writer.close();
		System.out.println("Indexing process ended!");
	}
	
	private void indexFile(File file) throws IOException {
		Document document = new Document();
		String title = new String("");
		String lyrics = new String("");
		String artist = new String("");
		try (FileInputStream fis = new FileInputStream(file))
        {
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			String str = new String(data, "UTF-8");
			String splitBy = ","; 
			String[] text = str.split(splitBy);
			artist =text[0];
			title = text[1];	
			lyrics = text[2];
			
			fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		document.add(new Field(LuceneConstants.ARTIST, artist, TextField.TYPE_STORED));
		document.add(new Field(LuceneConstants.TITLE, title, TextField.TYPE_STORED));
		document.add(new Field(LuceneConstants.LYRICS, lyrics, TextField.TYPE_STORED));
		writer.addDocument(document);
	}

}
