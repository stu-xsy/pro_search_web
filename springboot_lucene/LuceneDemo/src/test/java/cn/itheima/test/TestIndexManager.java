package cn.itheima.test;

import cn.itheima.dao.SkuDao;
import cn.itheima.dao.SkuDaoImpl;
import cn.itheima.jieba.JiebaAnalyzer;
import cn.itheima.pojo.sku;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestIndexManager {

    @Test
    public void createIndexTest() throws  Exception{

        //1.采集数据
        SkuDao skuDao=new SkuDaoImpl();
        List<sku> skuList = skuDao.querySkuList();
        List<Document> docList =new ArrayList<>();


        //2.创建文档对象7
        for (sku sku : skuList) {
          Document document  =new Document();
          //创建域对象并放入文档对象中
          document.add(new TextField("id",String.valueOf(sku.getId()), Field.Store.YES));
          document.add(new TextField("action_name",sku.getAnswer(), Field.Store.YES));

          docList.add(document);
        }

        //3.创建分词器


        Analyzer analyzer =new JiebaAnalyzer();
        ((JiebaAnalyzer) analyzer).init("D:\\system-student\\Lucene\\LuceneDemo\\");
        Analyzer analyzer1 =new JiebaAnalyzer(((JiebaAnalyzer) analyzer).segMode, ((JiebaAnalyzer) analyzer).stopWords);

        Analyzer analyzer2 =new JiebaAnalyzer();
        //4.创建Directory对象，索引库的位置
        Analyzer analyzerIK =new IKAnalyzer();
        Directory dir= FSDirectory.open(Paths.get("D:\\dir\\dirIK"));

        //5.IndexWriterConfig对象
        IndexWriterConfig config = new IndexWriterConfig(analyzerIK);


        //6.IndexWriter输出流对象，指定的输出流位置和使用的config对象
        IndexWriter indexWriter= new IndexWriter(dir,config);

        //7.写入文档到索引库
        for (Document doc : docList) {
                      indexWriter.addDocument(doc);

        }
        //8.释放资源
        indexWriter.close();
    }
}
