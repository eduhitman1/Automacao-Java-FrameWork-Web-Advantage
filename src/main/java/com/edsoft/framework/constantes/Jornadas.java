package com.edsoft.framework.constantes;

public enum Jornadas {
	/**
	 * Enum de crição de Jornadas
	 */
  ADICIONARPRODUTO("AdicionarProduto"),
  CADASTRARUSUARIO("CadastrarUsuario");
    
  private String value;
  
  Jornadas(String value) {
    this.value = value;
  }
}
