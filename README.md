# restassured-cucumber-junit-allure-gradle
> Resumo:
> Implementação de Testes Automatizados de API com o uso das tecnologias Java 8, REST-Assured, Gradle, Junit e Allure Framework;

#### Serviço usado:
> https://viacep.com.br
#### Caminhos testados:
> /ws/{CEP}/json
> /ws/{UF}/{CIDADE}/{LOGRADOURO}/json/

#### Pré requisitos:
| requisito        | página de download |
| ------           | ------ |
| Java 8           | https://www.oracle.com/br/java/technologies/javase-jdk8-downloads.html |
| Gradle 6.8.3     | https://gradle.org/releases/ |
| Allure Framework | https://docs.qameta.io/allure/#_installing_a_commandline
```sh
Ps: é necessário tanto a instalação quanto a configuração das variáveis de ambiente tanto do Java, a quanto do Gradle. 
A instalação do Allure Framework é necessária se decidir usa-lo por linha de comando e/ou não tiver a pasta .allure no projeto. 
```

### Dependências:
| Tecnologia usada     | página oficial |
| ------               | ------ |
| allure-cucumber5-jvm | https://docs.qameta.io/allure/ |
| cucumber-junit       | https://cucumber.io/docs/guides/ |
| cucumber-java        | https://cucumber.io/docs/guides/ |
| junit                | https://junit.org/junit4/ |
| rest-assured         | https://rest-assured.io/ |

#### Arquitetura usada:
O projeto usa a arquitetura AppObject e esta organizada da seguinte maneira:

![alt text](https://github.com/AdrianoAGuterres/restassured-cucumber-junit-allure-gradle/issues/1#issue-851955469)

#### Considerações:
Por default o Allure Framework cria pastas com o resultado do teste e o report dentro da pasta .build, porem a cada execução da task 'clean' estas pastas são removidas
então para manter um histórico das execuções foi configurado para serem criadas as pastas resultTests e report. A primeira pasta guardará os resultados da execução dos testes enquanto a segunda terá os arquivos gerados pelo Allure Framework na geração do report. O arquivo index.html é o arquivo principal do report e pode ser aberto pelo navegador através da ide sem a necessidade de 'subir' o Allure Server ou por linha de comando 'subindo' o Allure Server.  

#### Como executar o teste:
Os cenários contidos no arquivo 'consultaCep.feature' serão rodados a cada execução das tasks 'build-> build', 'verification -> check' ou 'verification -> test' na aba do Gradle, porem só será gerado o report executando a task 'other -> allureReport'.




