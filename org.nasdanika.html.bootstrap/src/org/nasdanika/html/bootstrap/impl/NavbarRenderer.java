package org.nasdanika.html.bootstrap.impl;

public class NavbarRenderer
{
  protected static String nl;
  public static synchronized NavbarRenderer create(String lineSeparator)
  {
    nl = lineSeparator;
    NavbarRenderer result = new NavbarRenderer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "<nav class=\"navbar navbar-default\" role=\"navigation\"";
  protected final String TEXT_3 = ">" + NL + "  <div class=\"container-fluid\">" + NL + "    <!-- Brand and toggle get grouped for better mobile display -->" + NL + "    <div class=\"navbar-header\">" + NL + "      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#";
  protected final String TEXT_4 = "\">" + NL + "        <span class=\"sr-only\">Toggle navigation</span>" + NL + "        <span class=\"icon-bar\"></span>" + NL + "        <span class=\"icon-bar\"></span>" + NL + "        <span class=\"icon-bar\"></span>" + NL + "      </button>" + NL + "      ";
  protected final String TEXT_5 = "      " + NL + "      \t<a class=\"navbar-brand\" href=\"";
  protected final String TEXT_6 = "\">";
  protected final String TEXT_7 = "</a>";
  protected final String TEXT_8 = NL + "    </div>" + NL + "" + NL + "    <!-- Collect the nav links, forms, and other content for toggling -->" + NL + "    <div class=\"collapse navbar-collapse\" id=\"";
  protected final String TEXT_9 = "\">" + NL + "     \t";
  protected final String TEXT_10 = NL + "\t      <ul class=\"nav navbar-nav\">" + NL + "\t    \t";
  protected final String TEXT_11 = NL + "\t      </ul>" + NL + "\t    ";
  protected final String TEXT_12 = NL + "\t    " + NL + "\t    ";
  protected final String TEXT_13 = NL + "      " + NL + "     \t";
  protected final String TEXT_14 = NL + "\t      <ul class=\"nav navbar-nav navbar-right\">" + NL + "\t    \t";
  protected final String TEXT_15 = NL + "      " + NL + "    </div><!-- /.navbar-collapse -->" + NL + "  </div><!-- /.container-fluid -->" + NL + "</nav>";
  protected final String TEXT_16 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	NavbarConfig config = (NavbarConfig) argument;

    stringBuffer.append(TEXT_2);
    stringBuffer.append(config.getAttributes());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(config.getCollapseTargetId());
    stringBuffer.append(TEXT_4);
     if (config.getBrand()!=null) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(config.getBrandRef());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(config.getBrand());
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(config.getCollapseTargetId());
    stringBuffer.append(TEXT_9);
     if (config.getLeftItems()!=null) { 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(config.getLeftItems());
    stringBuffer.append(TEXT_11);
     } 
    stringBuffer.append(TEXT_12);
    stringBuffer.append(config.getForm());
    stringBuffer.append(TEXT_13);
     if (config.getRightItems()!=null) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(config.getRightItems());
    stringBuffer.append(TEXT_11);
     } 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
