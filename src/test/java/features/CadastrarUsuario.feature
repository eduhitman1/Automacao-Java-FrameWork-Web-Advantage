#language: pt
@Cadastar_Usuario
Funcionalidade: Cadastrar Usuario
  
  Cenario: Cadastrar um usuario
  Dado que estou com a aplicacao aberta 
  Quando eu clico no link do login 
  E clico em 'CREATE NEW ACCOUNT'
  E informo os dados account details
  
  |    username    |  password   | confirmPassword |         email            |
  |eduardotest     |Rathitman1** | Rathitman1**    |rat.hitman01.eh@gmail.com |
  |Iameduardoneill |teste1**     | teste1**        |rat.hitman01.eh@gmail.com |
  
  E informo os dados personal details
  
  | firstName | lastName | phoneNumber |
  |  Felipe   |  Matias  |   93434394  |
  |  Geovana  | Ferreira |   76553445  |
  
  E informo o address

  | county |   city   |     address           | region | postalCode |
  | Brazil | Sao Paulo| Rua Francisco Alvares |   SP   |  02368040  |
  