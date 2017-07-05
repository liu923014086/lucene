package com.itheima.lucene;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.WildcardQuery;

import com.itheima.utils.LuceneUtils;

/**
 * 
 * indexSearcher.searcher(Query )
 * 
 * Query 是一个查询，条件，不同的子类相当于不同的查询规则
 * 
 * 我们可以扩展..
 * 
 * @author Administrator
 *
 */
public class TestQuery {
		public static void main(String[] args) throws Exception {
			
			//testQuery();
			//第一种查询，TermQuery 
//			Query query=new TermQuery(new Term("author","毕加索"));
			
			
			
			//第二种查询：字符串搜索..
//			String fields []={"author"};
//			
//			QueryParser queryParser=new MultiFieldQueryParser(LuceneUtils.getMatchVersion(),fields,LuceneUtils.getAnalyzer());
//			Query query=queryParser.parse("毕加索");
//			
//			//author:毕  author：加
			
			
			//第三种查询：查询所有..
//			Query query=new MatchAllDocsQuery();
			
			//第四种查询：范围查询，可以使用此查询来替代过滤器...
			//我们完成一种需求有两种方式，我们推荐用这种...性能比filter要高
			
//			Query query=NumericRangeQuery.newIntRange("id", 1, 10, true, true);
			
			
			//第五种查询：通配符。。。
			//?代表单个任意字符，* 代表多个任意字符...
//			Query query=new WildcardQuery(new Term("title", "luce*"));
			
			
			//第六种查询：模糊查询..。。。
			//author String 
			/**
			 * 1:需要根据查询的条件
			 * 
			 * 
			 * 2:最大可编辑数  取值范围0，1 ,2
			 * 允许我的查询条件的值，可以错误几个字符...
			 * 
			 */
			Query query=new FuzzyQuery(new Term("author", "爱新觉罗杜小"),1);
			testQuery(query);
		}
	
	
		public static void testQuery(Query query) throws Exception{
			IndexSearcher indexSearcher=LuceneUtils.getIndexSearcher();
			TopDocs topDocs=indexSearcher.search(query,100);
			for(ScoreDoc scoreDoc:topDocs.scoreDocs){
				Document document=indexSearcher.doc(scoreDoc.doc);
				System.out.println(document.get("id"));
				System.out.println(document.get("title"));
				System.out.println(document.get("content"));
				System.out.println(document.get("author"));
				System.out.println(document.get("link"));
			}
			
			
		}
	
}
