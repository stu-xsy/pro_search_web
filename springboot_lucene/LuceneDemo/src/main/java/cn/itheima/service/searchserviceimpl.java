package cn.itheima.service;

import cn.itheima.jieba.JiebaAnalyzer;
import cn.itheima.pojo.ResultModel;
import cn.itheima.pojo.sku;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class searchserviceimpl implements searchservice {

    public final static Integer PAGE_SIZE=10;
    @Override
    public ResultModel query(String queryString, Integer page) throws  Exception {
        ResultModel resultModel =new ResultModel();
        //从第几条开始查
        int start = (page-1)*PAGE_SIZE;

        Integer end = page * PAGE_SIZE;

        Analyzer analyzer =new JiebaAnalyzer();


        ((JiebaAnalyzer) analyzer).init("D:\\system-student\\Lucene\\LuceneDemo\\");
        //组合查询对象


        QueryParser queryParser =new QueryParser("action_name",analyzer);
        Query query1=null;
        if(StringUtils.isEmpty(queryString)){
            try {
                query1 = queryParser.parse("*:*");
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else {
            try {
                query1 = queryParser.parse(queryString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        Directory directory = FSDirectory.open(Paths.get("dir"));

        IndexReader reader =DirectoryReader.open(directory);

        IndexSearcher indexSearcher = new IndexSearcher(reader);


        TopDocs topdocs = indexSearcher.search(query1,end);

        resultModel.setRecordcount(topdocs.totalHits);

        ScoreDoc[] scoreDocs = topdocs.scoreDocs;
        List<sku> skuList =new ArrayList<>();
        if(scoreDocs !=null){
            for(int i =start;i<end;i++){
                if(i > scoreDocs.length-1)
                    break;
                Document document =reader.document(scoreDocs[i].doc);
                //
                //封装sku
                sku sku =new sku();
                sku.setId(Integer.parseInt(document.get("id")));
                sku.setAnswer(document.get("action_name"));
                skuList.add(sku);

            }
        }
        resultModel.setSkulist(skuList);
        resultModel.setCurPage(page);
        Long pagecount = topdocs.totalHits%PAGE_SIZE>0?(topdocs.totalHits/PAGE_SIZE)+1 : topdocs.totalHits/PAGE_SIZE;
        resultModel.setPageCount(pagecount);
        return resultModel;

    }
}
