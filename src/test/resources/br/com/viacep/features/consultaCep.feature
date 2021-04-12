# language: pt
#encoding: UTF-8
#@allure.label.layer:rest

Funcionalidade: Serviço de consulta de CEPs

  @cenariosPositivos
  Esquema do Cenario: Consulta de CEP válido
    Dado que o usuario insere o CEP "<CEP>" e é válido
    Quando o servico é consultado e retorna os dados em formato JSON
    Então contém os dados "<CEP>", "<logradouro>", "<complemento>", "<bairro>", "<localidade>", "<uf>" e "<ibge>"

    Exemplos:
      | CEP       | logradouro                | complemento  | bairro        | localidade   | uf | ibge    |
      | 01001-000 | Praça da Sé               | lado ímpar   | Sé            | São Paulo    | SP | 3550308 |
      | 94814-230 | Rua Guararapes            |              | Maringá       | Alvorada     | RS | 4300604 |
      | 91060-900 | Avenida Assis Brasil 3940 |              | São Sebastião | Porto Alegre | RS | 4314902 |
      | 92200-010 | Rua Boa Esperança         | até 999/1000 | Rio Branco    | Canoas       | RS | 4304606 |

  @cenariosNegativos
  Cenário: Consulta CEP inexistente
    Dado que o usuario iserir um CEP que não exista na base dos Correios
    Quando o servico é consultado e retorna os dados em formato JSON
    Então é retornado um atributo de erro

  @cenariosNegativos
  Cenário: Consulta CEP com formato inválido
    Dado que o usuario iserir um CEP com formato inválido
    Quando o servico é consultado e retorna em formato HTML
    Então é retornado uma mensagem de erro

  @cenariosPositivos
  Cenário: Pesquisa de CEP por endereço
    Dado que o usuario iserir RS, Gravatai e Barroso
    Quando o servico é consultado e retorna os dados em formato JSON
    Então é retornado uma lista com 2 resultados
    E o primeiro resultado contém os dados 94085-170, Rua Ari Barroso, Morada do Vale I, Gravataí, RS e 4309209
    E o segundo resultado contém os dados 94175-000, Rua Almirante Barroso, Recanto Corcunda, Gravataí, RS e 4309209


