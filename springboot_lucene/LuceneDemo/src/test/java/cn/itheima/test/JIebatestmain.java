package cn.itheima.test;


import cn.itheima.jieba.keyword.Keyword;
import cn.itheima.jieba.keyword.TFIDFAnalyzer;

import org.junit.Test;

import java.util.List;

public class JIebatestmain {

    TFIDFAnalyzer tfidfAnalyzer = new TFIDFAnalyzer();
    @Test
    public void testJieBamain()
    {
        String content="如何学习Python";
        int topN=5;

        List<Keyword> list=tfidfAnalyzer.analyze(content,topN);
        for(Keyword word:list)
            System.out.println(word.getName()+":"+word.getTfidfvalue()+",");
    }
}
