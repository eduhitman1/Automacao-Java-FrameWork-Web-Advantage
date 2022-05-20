package com.edsoft.framework.tools.evidencia;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

public class NextEvidencia {
	
	/**
	 * NextEvidencia class de mensageria de evidencia
	 */
	
  private String message;
  
  private String imageString;
  
  private BufferedImage image;
  
  public NextEvidencia(String message, String imageString) throws Exception {
    setMessage(message);
    setImageString(imageString);
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public BufferedImage getImage() {
    return this.image;
  }
  
  public void setImage(BufferedImage image) {
    this.image = image;
  }
  
  public String getImageString() {
    return this.imageString;
  }
  
  public void setImageString(String imageString) throws Exception {
    this.imageString = imageString;
    setImage(ImageIO.read(new ByteArrayInputStream(toImage(imageString))));
  }
  
  public static byte[] toImage(String string) throws Exception {
    return Base64.decodeBase64(string);
  }
}
