package org.nasdanika.html.impl;

public class PillAjaxDataToggleScriptRenderer
{
  protected static String nl;
  public static synchronized PillAjaxDataToggleScriptRenderer create(String lineSeparator)
  {
    nl = lineSeparator;
    PillAjaxDataToggleScriptRenderer result = new PillAjaxDataToggleScriptRenderer();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "<script>" + NL + "\t$('[data-toggle=\"pillajax\"]').click(function(e) {" + NL + "\t    var $this = $(this)" + NL + "\t    nsdLoad($this.attr('data-target'), $this.attr('href'));\t" + NL + "\t    $this.tab('show');" + NL + "\t    return false;" + NL + "\t});" + NL + "</script>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
