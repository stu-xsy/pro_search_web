package cn.itheima.test;

import cn.itheima.jieba.JiebaAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;
//后期加中文分词器
public class TestSearch {
    @Test
    public void testIndexSearch() throws  Exception{
        //1.创建分词器(搜索的关键词搜索使用
        // 分词器和创建索引的时候一样
        Analyzer analyzer2 =new JiebaAnalyzer();
        Analyzer analyzer =new JiebaAnalyzer();
        ((JiebaAnalyzer) analyzer).init("D:\\system-student\\Lucene\\LuceneDemo\\");
        Analyzer analyzer1 =new JiebaAnalyzer(((JiebaAnalyzer) analyzer).segMode, ((JiebaAnalyzer) analyzer).stopWords);

        Analyzer analyzerIk =new IKAnalyzer();
        //2.创建查询对象
        QueryParser queryParser =new QueryParser("action_name",analyzer1);


        //3.设置搜索关键词
         Query query=queryParser.parse("计算机");

        //4.创建Directory目录对象，指定索引库位置
        Directory dir= FSDirectory.open(Paths.get("D:\\dir"));

        //5.创建输入流对象 创建搜索对象
        IndexReader indexReader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher =new IndexSearcher(indexReader);
        //6.搜索并返回结果
        TopDocs topDocs  =indexSearcher.search(query,10);
        System.out.println("===count==="+topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;


        //7.获取结果集，遍历结果集
        if(scoreDocs != null){
            for(ScoreDoc scoreDoc : scoreDocs){
                int docId  = scoreDoc.doc;
                Document doc =indexSearcher.doc(docId);
                System.out.println("==========================================");
                System.out.println("===action_id======"+doc.get("id"));
                System.out.println("===action_desc"+doc.get("action_name"));

            }

        }
        indexReader.close();

    }


}
