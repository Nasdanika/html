package org.nasdanika.html.impl;

public class RouterApplicationRenderer
{
  protected static String nl;
  public static synchronized RouterApplicationRenderer create(String lineSeparator)
  {
    nl = lineSeparator;
    RouterApplicationRenderer result = new RouterApplicationRenderer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "<!DOCTYPE html>" + NL + "<html lang=\"en\">" + NL + "<head>" + NL + "<meta charset=\"utf-8\">";
  protected final String TEXT_3 = NL + "\t<title>";
  protected final String TEXT_4 = "</title>";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "\t<link rel=\"stylesheet\" href=\"";
  protected final String TEXT_7 = "\">";
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL + "\t<script src=\"";
  protected final String TEXT_10 = "\"></script>";
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + NL + "<script>" + NL + "" + NL + "\t// Loads url to element specified by selector. Dims target and displays \"Loaging...\", reports errors.   \t" + NL + "\tfunction nsdLoad(selector, url, data) {" + NL + "\t\tvar target = $(selector);" + NL + "\t\tif (target.length) {" + NL + "\t\t\tvar loadingImage = \"<table style='width: 100%; height:\"+target.height()+\"px; background:#EEE'>\"" + NL + "\t\t\t\t+ \"<tr valign='middle'>\" + \"<td align='center'>\"" + NL + "\t\t\t\t+ '<div class=\"progress progress-striped active\" style=\"width: 120px\">'" + NL + "\t\t\t\t+ '<div class=\"progress-bar\"  role=\"progressbar\" style=\"width: 100%\">'" + NL + "\t\t\t\t+ 'Loading...'" + NL + "\t\t\t\t+ '</div>'" + NL + "\t\t\t\t+ '</div>'" + NL + "\t\t\t\t+ \"</td>\" + \"</tr>\" + \"</table>\";" + NL + "\t" + NL + "\t\t\ttarget.html(loadingImage);" + NL + "\t\t\ttarget.load(url, data, function(responseText, textStatus, req) {\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\tif (textStatus == \"error\") {" + NL + "\t\t\t\t\tif (responseText) {" + NL + "\t\t\t\t\t\tvar errorMessage = '<div class=\"panel panel-danger\">'" + NL + "\t\t\t\t\t\t+ '<div class=\"panel-heading\">'" + NL + "\t\t\t\t\t\t+ '<h3 class=\"panel-title\">Error loading <b>' + url" + NL + "\t\t\t\t\t\t+ '</b></h3>' + '</div>' + '<div class=\"panel-body\"><b>' + responseText" + NL + "\t\t\t\t\t\t+ '</div>' + '</div>'" + NL + "\t\t\t\t\t\ttarget.html(errorMessage);" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\ttarget.html(\"<div class=\\\"alert alert-danger\\\">Error loading <b>\"+url+\"</b></div>\");" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t} else {" + NL + "\t\t\talert(\"Element not found: \" + selector);" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\t// Replaces element(s) specified by selector with content loaded from url. Dims target and displays \"Loaging...\", reports errors.   \t" + NL + "\tfunction nsdReplaceWith(selector, url, data) {" + NL + "\t\tvar target = $(selector);" + NL + "\t\tif (target.length) {" + NL + "\t\t\tvar loadingImage = \"<table style='width: 100%; height:\"+target.height()+\"px; background:#EEE'>\"" + NL + "\t\t\t\t+ \"<tr valign='middle'>\" + \"<td align='center'>\"" + NL + "\t\t\t\t+ '<div class=\"progress progress-striped active\" style=\"width: 120px\">'" + NL + "\t\t\t\t+ '<div class=\"progress-bar\"  role=\"progressbar\" style=\"width: 100%\">'" + NL + "\t\t\t\t+ 'Loading...'" + NL + "\t\t\t\t+ '</div>'" + NL + "\t\t\t\t+ '</div>'" + NL + "\t\t\t\t+ \"</td>\" + \"</tr>\" + \"</table>\";" + NL + "\t" + NL + "\t\t\ttarget.html(loadingImage);" + NL + "\t\t\t" + NL + "\t\t\tjQuery.get(url, data, function(data, textStatus, req) {\t\t\t\t\t\t\t\t\t" + NL + "\t\t\t\tif (textStatus == \"error\") {" + NL + "\t\t\t\t\tif (data) {" + NL + "\t\t\t\t\t\tvar errorMessage = '<div class=\"panel panel-danger\">'" + NL + "\t\t\t\t\t\t+ '<div class=\"panel-heading\">'" + NL + "\t\t\t\t\t\t+ '<h3 class=\"panel-title\">Error loading <b>' + url" + NL + "\t\t\t\t\t\t+ '</b></h3>' + '</div>' + '<div class=\"panel-body\"><b>' + data" + NL + "\t\t\t\t\t\t+ '</div>' + '</div>'" + NL + "\t\t\t\t\t\ttarget.html(errorMessage);" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\ttarget.html(\"<div class=\\\"alert alert-danger\\\">Error loading <b>\"+url+\"</b></div>\");" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\ttarget.replaceWith(data);" + NL + "\t\t\t\t}" + NL + "\t\t\t});" + NL + "\t\t} else {" + NL + "\t\t\talert(\"Element not found: \" + selector);" + NL + "\t\t}" + NL + "\t}\t" + NL + "" + NL + "\t$(document).ready(" + NL + "\t\t\tfunction() {" + NL + "" + NL + "\t\t\t\tvar Workspace = Backbone.Router.extend({" + NL + "" + NL + "\t\t\t\t\t// This router matches router servlet path and" + NL + "\t\t\t\t\t// loads path to specified container" + NL + "\t\t\t\t\t// e.g. router/main/mypage.html will load router/mypage.html to " + NL + "\t\t\t\t\t// element with id main" + NL + "\t\t\t\t\troutes : {" + NL + "\t\t\t\t\t\t\"router/:targetId/*path\" : function(targetId, path) {" + NL + "\t\t\t\t\t\t\tnsdLoad(\"#\"+targetId, path); // Relative path" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "" + NL + "\t\t\t\t});" + NL + "" + NL + "\t\t\t\tworkspace = new Workspace();" + NL + "\t\t\t\tBackbone.history.start();" + NL + "" + NL + "\t\t\t\t";
  protected final String TEXT_14 = NL + "\t\t\t\t\tif (!document.location.hash) {" + NL + "\t\t\t\t\t\tworkspace.navigate('router/";
  protected final String TEXT_15 = "', {" + NL + "\t\t\t\t\t\t\ttrigger : true" + NL + "\t\t\t\t\t\t});" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t";
  protected final String TEXT_16 = NL + "\t\t\t});" + NL + "</script>" + NL + "</head>" + NL + "" + NL + "<body>" + NL + "\t";
  protected final String TEXT_17 = NL + "</body>" + NL + "</html>";
  protected final String TEXT_18 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	RouterApplicationConfig config = (RouterApplicationConfig) argument;

    stringBuffer.append(TEXT_2);
     if (config.getTitle()!=null) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(config.getTitle());
    stringBuffer.append(TEXT_4);
     } 
    stringBuffer.append(TEXT_5);
     for (String s: config.getStylesheets()) { 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(s.trim());
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
     for (String s: config.getScripts()) { 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(s.trim());
    stringBuffer.append(TEXT_10);
     } 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(config.getHead());
    stringBuffer.append(TEXT_13);
     if (config.getInitialRoute() != null) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(config.getInitialRoute());
    stringBuffer.append(TEXT_15);
     } 
    stringBuffer.append(TEXT_16);
    stringBuffer.append(config.getBody());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
