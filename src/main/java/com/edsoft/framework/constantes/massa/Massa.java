package com.edsoft.framework.constantes.massa;

public enum Massa {
	/**
	 * Enum de criação de massa Chave
	 */
  USERNAME("username"),
  PASSWORD("password"),
  CATEGORIA("categoria"),
  PRODUTO("produto"),
  QUANTIDADE("quantidade"),
  
  EMAIL("email"),
  CONFIRMPASSWORD("confirmPassword"),
  FIRSTNAME("firstname"),
  LASTNAME("lastname"),
  PHONENUMBER("phonenumber"),
  COUNTRY("country"),
  CITY("city"),
  ADDRESS("address"),
  STATE("state"),
  POSTALCODE("postalcode");
  
  private String value;
  
  Massa(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return this.value;
  }
}
