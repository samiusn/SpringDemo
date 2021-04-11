package com.example.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

@Service
public class NLPService {
	
	public List<Integer> getSentiment(String text) {
		List<Integer> returnList = new ArrayList<Integer>();
		
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		props.setProperty("coref.algorithm", "neural");
		
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		CoreDocument document = new CoreDocument(text);
		pipeline.annotate(document);

		Annotation annotation = pipeline.process(text);

		for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
			Tree tree = sentence.get(SentimentAnnotatedTree.class);

			returnList.add(RNNCoreAnnotations.getPredictedClass(tree));
			System.out.println(returnList);

		}
		return returnList;
	}
}
