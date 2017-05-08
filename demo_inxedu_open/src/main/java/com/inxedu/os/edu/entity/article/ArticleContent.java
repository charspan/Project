package com.inxedu.os.edu.entity.article;

import java.io.Serializable;

import lombok.Data;

/**
 * 文章内容
 * 
 * @author www.inxedu.com
 */
@Data
public class ArticleContent implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 文章ID */
	private int articleId;
	/** 文章对应的内容 */
	private String content;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
