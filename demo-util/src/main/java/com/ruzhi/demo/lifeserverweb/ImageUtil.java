package com.ruzhi.demo.lifeserverweb;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

public class ImageUtil{
  private static final Logger log = LoggerFactory.getLogger(ImageUtil.class);
  public static BufferedImage printImage(BufferedImage image, int realW, int realH)
          throws Exception
  {
    Image imageInstance = image.getScaledInstance(realW, realH, 1);
    BufferedImage tag = new BufferedImage(realW, realH, 1);
    Graphics tagGraphics = tag.getGraphics();
    tagGraphics.drawImage(imageInstance, 0, 0, null);
    tagGraphics.dispose();
    return tag;

  }

  public static void addImage2BGImage(BufferedImage bgImage, int x, int y, BufferedImage image)
  {
    if ((x < 0) || (y < 0)) {
      return;
    }

    Graphics2D g = bgImage.createGraphics();

    g.drawImage(image, x, y, null);

    g.dispose();
  }
  //x是总宽度
  public static void addText2Image(BufferedImage bgImage, int x, int width, int high, int textSize, String text, long maxTextW,String fontName, int leftOrRight, Color c) {
    if ((width < 0) || (high < 0) || StringUtil.isBlank(text)) {
      return;
    }
    GraphicsEnvironment eq =GraphicsEnvironment.getLocalGraphicsEnvironment();
//    String[] fontNames = eq.getAvailableFontFamilyNames();
//    if(fontNames !=null){
//    	log.error("支持的字体有：");
//	    for(int i=0;i<fontNames.length;i++){
//	    	log.error(fontNames[i]+",");
//	    }
//    }else{
//    	log.error("不支持字体");
//    }
    Graphics2D g = bgImage.createGraphics();

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    Font storeFont = new Font(fontName, 1, textSize);
//    log.error("需要的字体："+fontName);
    FontMetrics fm = g.getFontMetrics(storeFont);
    int storeW = fm.stringWidth(text);
    while(storeW >maxTextW){
      storeFont = new Font(fontName, 1, --textSize);
      fm = g.getFontMetrics(storeFont);
      storeW = fm.stringWidth(text);
    }
    int startX=x;
    if(leftOrRight == 0){
      startX=(width-storeW)/2+x;
    }else if(leftOrRight == 2){
      startX=width-storeW;
    }
    String ecodeText=null;
    try {
      ecodeText = new String(text.getBytes("gbk"));
    } catch (UnsupportedEncodingException e) {
      log.error("addText2Image", e) ;
    }

    g.setColor(c);
    g.setFont(storeFont);
    g.drawString(ecodeText, startX, high);

    g.dispose();
  }
  public static void addText2Image(BufferedImage bgImage, int x, int y, int textSize, String text, String fontName, int leftOrRight, Color c) {
    if ((x < 0) || (y < 0)) {
      return;
    }

    Graphics2D g = bgImage.createGraphics();

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    Font storeFont = new Font(fontName, 1, textSize);
    FontMetrics fm = g.getFontMetrics(storeFont);
    int storeW = fm.stringWidth(text);
    while(storeW >x-20){
      storeFont = new Font(fontName, 1, --textSize);
      fm = g.getFontMetrics(storeFont);
      storeW = fm.stringWidth(text);
    }
    if (leftOrRight == 0)
      x = (x-storeW)/2;
    else if (leftOrRight == 2) {
      x += storeW;
    }

    g.setColor(c);
    g.setFont(storeFont);
    g.drawString(text, x, y);

    g.dispose();
  }
  /*public static BufferedImage cretaeQRByStream(String str, int rqSize, String imageFormat, float scaledSize)
    throws Exception
  {
    Map hints = new HashMap();
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
    BitMatrix matrix = new MultiFormatWriter().encode(str,
      BarcodeFormat.QR_CODE, rqSize, rqSize, hints);

    int width = matrix.getWidth();
    int height = matrix.getHeight();

    BufferedImage image = new BufferedImage(width, height,
      1);
    image.createGraphics();
    Graphics2D graphics = (Graphics2D)image.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, width, height);

    for (int x = 0; x < width; ++x) {
      for (int y = 0; y < height; ++y) {
        if (matrix.get(x, y)) {
          graphics.setColor(Color.BLACK);
          graphics.fillRect(x, y, 1, 1);
        } else {
          graphics.setColor(Color.white);
          graphics.fillRect(x, y, 1, 1);
        }
      }
    }
    graphics.dispose();

    return reduce(image, scaledSize);
  }*/

  private static BufferedImage reduce(BufferedImage original, double scale) {
    int w = new Double(original.getWidth() * scale).intValue();
    int h = new Double(original.getHeight() * scale).intValue();
    Image rescaled = original.getScaledInstance(w, h,
            1);
    BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(),
            1);
    Graphics2D g = result.createGraphics();
    g.drawImage(rescaled, 0, 0, original.getWidth(), original.getHeight(),
            (w - original.getWidth()) / 2, (h - original.getHeight()) / 2, (w - original.getWidth()) / 2 + original.getWidth(), (h - original.getHeight()) / 2 + original.getHeight(), null);
    g.dispose();
    return result;
  }


  public static void addText2Image(BufferedImage bgImage,int width,
                                   int textSize, String text,String fontName, Color c,
                                   int leftOrRight,int y,int startX,int endX){

    if ((width < 0)  || StringUtils.isBlank(text)) {
      return;
    }
    Graphics2D g = bgImage.createGraphics();

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    Font storeFont = new Font(fontName, 1, textSize);
//	    log.error("需要的字体："+fontName);
    FontMetrics fm = g.getFontMetrics(storeFont);
    int storeW = fm.stringWidth(text);
    int maxTextW=	endX == 0? ( width-startX) :(endX-startX);   //最大字体范围
    //如果字体宽度大于最大字体范围，则缩小字体
    while(storeW >maxTextW){
      storeFont = new Font(fontName, 1, --textSize);
      fm = g.getFontMetrics(storeFont);
      storeW = fm.stringWidth(text);
    }
    if(leftOrRight == 0){   //居中
      startX=startX + (endX -startX- storeW )/2;
    }else if(leftOrRight == 2){   //居右
      startX=width-endX -storeW;
    }
    String ecodeText=null;
    try {
      ecodeText = new String(text.getBytes("gbk"));
    } catch (UnsupportedEncodingException e) {
      log.error("addText2Image", e) ;
    }

    g.setColor(c);
    g.setFont(storeFont);
    g.drawString(ecodeText, startX, y);

    g.dispose();

  }
 /* public static void main(String[] args) {
	System.out.println(StringEscapeUtil.escapeHtml("\1"));
}*/
}
