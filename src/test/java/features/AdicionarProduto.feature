#language: pt
@Login_adicionar_carrinho
Funcionalidade: Adicionar Produto

 Cenario: Realizar login e adicionar carinho
  Dado que estou com a aplicacao aberta
  Quando eu clico no link do login 
  Entao eu insiro o userName e Password
  E clico no botao logar 
  E valido na barra superior username
  Dado estou selecionando a categoria do produto  
 
 |categoria |
 |SPEAKERS  |
 |TABLETS   |
 |LAPTOPS   |
 |MICE      |
 |HEADPHONES|
  
 E clicar no botao da primeira oferta de produto 
 Entao seleciono a quantidade desejada
 
 |quantidade|
 | 7        |
 