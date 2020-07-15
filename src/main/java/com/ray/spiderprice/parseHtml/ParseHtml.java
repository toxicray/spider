package com.ray.spiderprice.parseHtml;

import us.codecraft.webmagic.Page;

/**
 * Package:com.ray.spiderprice
 * *Author:ray
 * *version:...
 * *Created in 2020/1/25  19:02
 **/

public class ParseHtml {

	private ParseMethod parseMethod;


	public ParseHtml(ParseMethod parseMethod) {
		this.parseMethod = parseMethod;
	}

	public void doExecute(Page page){
		parseMethod.doParseHtml(page);
	}

	public void setParseMethod(ParseMethod parseMethod) {
		this.parseMethod = parseMethod;
	}
}
