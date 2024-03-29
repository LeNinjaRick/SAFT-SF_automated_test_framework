# SAFT-SF_automated_test_framework
Projeto base de framework para desenvolvimento de automação de testes WEB(Salesforce lightning) e APIs

![Badge](https://img.shields.io/badge/license-MIT-green)
![Badge](https://img.shields.io/badge/version-1.0-brightgreen)

<h4 align="center"> 
	 Status do projeto 🚀 Concluido  
</h4>



Tabela de conteúdos
=================
<!--ts-->
   * [Sobre](#Sobre)
   * [Tabela de Conteudo](#tabela-de-conteudo)
   * [Funcionalidades](#funcionalidades)
   * [Como usar](#como-usar)
      * [Pre Requisitos](#pré-requisitos)
      * [Rodando os testes](#-rodando-os-testes)
      * [Funções](#funções)
      * [Funções pré definidas](#funções-pré-definidas-do-gherkin)
      * [Funções personalizadas](#funções-simplificadas-e-personalizadas)
      * [Funções para testes de APIs](#funções-para-testes-de-apis)
      * [Personalizar Codigo](#personalizar-codigo-para-teste)
      * [Geração de Relatorios no ExtentReports](#geração-de-relatorios-no-extentreports)
   * [Tecnologias](#-tecnologias)
   * [Autoria](#autor)
<!--te-->


### Funcionalidades

- [x] Escrever testes usando apenas o Gherkin
- [x] Permite personalização de steps
- [x] Login com diferentes usuarios
- [x] Validação de mensagens de erro ou sucesso
- [x] Tratamento de erros padrão da plataforma, impedindo a falha precoce do teste
- [x] Navegação de registros
- [x] Navegação e objetos e Aplicativos Lightning
- [x] Criação de qualquer registro ou objeto
- [x] Edição de qualquer registro ou objeto
- [x] Percorrer fluxos funcionais de atendimento, logistica, gestão de estoque, etc...
- [x] Validação de qualquer valor de campo, usando regex ou valor assertivo
- [x] Simplifica o teste criado em 1 linha se necessario criar sub-fluxos ou pre-condições
- [X] Automapeamento de telas criação e edição de registros, retornando o codigo necessario no LOG
- [X] Permite testar APIs usando apenas palavras chaves do gherkin e arquivos .properties
- [X] Geração de relatórios personalizados

## Como Usar:

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com)
- [Java 11 ou superior](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)
- Gerenciador de dependencias Maven: [Maven](https://maven.apache.org/)
- Para configurar o maven e o java, siga os passos do tutorial: [Tutorial Configuração](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/)
- Além disto é bom ter uma IDE para trabalhar com o código como [Intellij](https://www.jetbrains.com/pt-br/idea/)

### 🎲 Rodando os testes:

- Baixe o conteudo desse repositorio para uma Workspace;
- Após criado seus testes no arquivo .feature, vá ate a classe RunTest.java e adicione a tag do seu teste e execute.

<h4 align="left"> Exemplo de tag:
  <img alt="ExemploTag" title="#ExemploTag" src="./assets/exemploTag.png" />
</h4>
<h4 align="left"> Runtest com a tag para executar:
  <img alt="runtest" title="#runTest" src="./assets/Runtest.png" />
</h4>

## Funções

### Funções pré-definidas do Gherkin:

- Existem funções criadas previamente a partir do Gherkin para facilitar a criação de testes variados no Salesforce Lightning, listadas abaixo:

### Funções de Wait e gravar variavel:

- <b>Given que o tempo de espera medio sera de 15 segundos</b> -> Define o tempo de espera padrão para encontrar cada elemento do teste, seja no background ou no teste especificado;
- <b>Given que a variavel "varNomeOportunidade" tenha o valor "oportunidade Random"</b> -> Cria uma variavel a partir do valor desejado, qualquer valor que contenha a palavra "Random" vai ser randomizado em um hexadecimal de 5 caracteres;

### Função de login:

- <b> And que esteja logado no SalesForce com sucesso com o usuario "Gerente"</b> -> Faz o login no salesforce que foi definido no arquivo salesforce.properties junto com as credenciais, devendo elas serem nomeadas da seguinte forma seguindo o exemplo de "Gerente": usernameGerente=valor, passwordGerente=valor
<h4 align="left"> Arquivo properties:
  <p>
  <img alt="properties" title="#properties" src="./assets/salesforceProperties.png" />
    </p>
</h4>

### Funções de navegação:

- <b> And acessar o registro hexadecimal "0WO8b000000fQEFGA2" pela url </b> -> Caso tenha algum Registro fixo que precise acessar em todo teste, coloque nesse metodo e ele ira direcionar a URL diretamente para o registro.(tambem aceita variaveis previamente definidas no gherkin com o valor de hexadecimal)

- <b>When Accesar o objeto "Casos" e mudar o modo de exibicao da lista para "Meus casos"</b> -> Acessa um app e muda a forma de visualização de Lista atraves do texto presente em tela;
<h4 align="center"> Exemplo de lista de visualização:
  <p>
  <img alt="viewList" title="#viewList" src="./assets/viewList.png" />
  </p>
</h4>

- <b> When Accesar o objeto "Contas" e criar um novo registro </b> -> Acessa um app e clica no botão de criação de registro, esperando o input dos dados;

- <b> When acessar o registro "Andy Young" </b> -> Busca e entra em qualquer registro ja criado no Salesforce;
<h4 align="center"> Exemplo de busca de registro:
  <p>
  <img alt="buscaRegistro" title="#buscaRegistro" src="./assets/buscaRegistro.png" /></p>
</h4>



### Funções de preenchimento de inputs e checkbox:

- <b> And clicar no checkbox "Particular" </b> -> clica em um checkbox presente na tela atraves de seu texto;
- <b> And preencher o campo "Nome da oportunidade" com o valor "teste Random" </b> -> Identifica o tipo de campo de input (Texto, comboBox, Data, etc...) e preenche de acordo com o valor colocado. Qualquer valor que contenha a palavra "Random" vai ser randomizado em um hexadecimal de 5 caracteres;
<h4 align="center"> Exemplo de inputs:
  <img alt="inputs" title="#inputs" src="./assets/inputsExemplo.png" />
</h4>

- <b> And preencher o campo "Nome da oportunidade" com o valor da variavel "varNomeOportunidade" </b> -> Identifica o tipo de campo de input (Texto, comboBox, Data, etc...) e preenche de acordo com o valor de uma variavel coletada anteriormente, passando o nome da variavel. Qualquer valor que contenha a palavra "Random" vai ser randomizado em um hexadecimal de 5 caracteres;

- <b> And edite o campo "Departamento" com o valor "testeValor" </b> ->  clica para editar o campo atraves de seu texto em tela, depois identifica o tipo de input(Texto, comboBox, Data, etc...)  e preenche de acordo com o valor colocado. Qualquer valor que contenha a palavra "Random" vai ser randomizado em um hexadecimal de 5 caracteres;
- <b> And edite o campo "Telefone residencial" com o valor da variavel "varCelular" </b> ->  clica para editar o campo atraves de seu texto em tela, depois identifica o tipo de input(Texto, comboBox, Data, etc...) e preenche de acordo com o valor de uma variavel coletada anteriormente, passando o nome da variavel.

<h4 align="center"> Exemplo de edição:
  <img alt="edicao" title="#edicao" src="./assets/edicao.png" />
</h4>
    
- <b> And clicar no botao pelo texto "Marcar Fase como concluído(a)" </b> -> clica em qualquer botão atraves do seu texto presente na tela;
    

### Funções de salvamento de registros:


- <b> And Salvar a criacao do registro com sucesso </b> -> Salva o registro que estava em preenchimento e valida que o mesmo foi criado sem erros;
- <b> And Salvar a criacao do registro com falha </b> -> Salva o registro que estava em preenchimento e valida que um erro impediu a criação do registro;
- <b> And salve a edicao do registro </b> -> Salva o registro que estava em edição;

### Funções de validação de campos, valores e erros:

- <b>And busca pela mensagem de erro "Revise os seguintes campos"</b> -> Verifica se uma mensagem de erro foi apresentada na tela;
<h4 align="left"> Exemplo de mensagem de falha:
  <p>
  <img alt="exemploFalha" title="#exemploFalha" src="./assets/exemploFalha.png" />
    </p>
</h4>
- <b> Then espero que o campo "Nome completo" esteja com o valor "Mr Andy Young" | ou | Then espero que o campo "Celular" esteja com o valor "^(.*)$" </b>-> Valida que um campo do registro esteja preenchido com o valor definido, sendo esse valor podendo ser um regex ou valor completo;
<h4 align="left"> Exemplo de campos a ser validados:
  <p>
  <img alt="ExemploValidaCampos" title="#ExemploValidaCampos" src="./assets/ExemploValidaCampos.png" />
    </p>
</h4>


### Funções de captura de dados em variaveis:

 - <b> And capture o valor do campo "Celular" e armazene na variavel "varCelular" </b> -> Usa o valor preenchido de um campo e armazena em uma variavel para uso posterior;


### Função de Auto Mapeamento de campos:

- <b> And mapeei e retorne os comandos para preencher os inputs </b> -> Mapeeia toda a pagina atual em que o teste parou e retorna todos os comandos necessarios para preencher os campos da pagina, sejam eles inputs, checkbox, combobox, Datas e etc.(Não mapeia o campo padrão de pesquisa do Salesforce para evitar comandos errados na hora de editar ou criar um registro).


<h4 align="left"> Pagina usada para auto mapear:
  <p>
  <img alt="exemplopage" title="#exemplopage" src="./assets/exemplopage.png"/>
    </p>
</h4>


<h4 align="left"> Exemplo de codigo gerado no log:
  <p>
  <img alt="AutoMap" title="#AutoMap" src="./assets/AutoMap.png"/>
    </p>
</h4>


### Funções Simplificadas e Personalizadas:

- <h4> Caso seu teste esteja ficando grande demais no gherkin, é possivel diminuir ele em apenas 1 step, usando as palavrar reservadas do gherkin a seguir:</h4>

- <b>And simplifique o teste de preenchimento </b>-> Simplifica o teste de preenchimento, pegando o objeto criado, os campos e valores preenchidos, e valida se no final é um teste negativo ou positivo de criação. Gera o codigo inteiro do teste no log, assim é possivel coleta-lo e criar um step personalizado.
<h4 align="left"> Exemplo de codigo gerado no log:
  <p>
  <img alt="codigoGerado" title="#codigoGerado" src="./assets/codigoGerado.png" />
    </p>
</h4>

- <b>And simplifique o teste de validacao </b>-> Simplifica o teste de validação, pegando o registro acessado, os campos e valores validados. Gera o codigo inteiro do teste no log, assim é possivel coleta-lo e criar um step personalizado.


- <b> após pegar seu codigo no log, vc pode criar um step personalizado com passos adicionais que não foi possivel fazer usando os steps pré definidos: </b>
<h4 align="left"> Exemplo de teste sem simplificar e simplificado:
  <p>
  <img alt="exemploGherkinSimples" title="#exemploGherkinSimples" src="./assets/exemploGherkinSimples.png" />
    </p>
</h4>

<h4 align="left"> Exemplo de step personalizado:
  <p>
  <img alt="exemploGherkinStepSimples" title="#exemploGherkinStepSimples" src="./assets/exemploGherkinStepSimples.png" />
    </p>
</h4>

<h4 align="left"> Codigo no step definition personalizado:
  <p>
  <img alt="stepSimplificado" title="#stepSimplificado" src="./assets/stepSimplificado.png" />
    </p>
</h4>

### Funções para testes de APIs:

<p> Da mesma forma que nos testes WEB, para rodar os testes de API é necessario colocar a tag do teste na classe runner: RunTest</p>
<p>É possivel mesclar testes WEB com os testes de API, para ganhos de desempenho na criação de registros ou validações do banco de dados do Salesforce.</p>

#### pré condições (Given):

- <b> Given que seja definido o header "Accept" com o valor "application/json" </b> -> Define os headers e valores que devem receber para configurar a requisição, tambem aceita variaveis previamente definidas no gherkin;
- <b> And que seja adicionado os parametros "?api_key=teste&exemplo=teste" no endpoint "endpointHunter" </b> -> Define novos parametros para serem adicionados na URI armazenada no api.properties;

- <b> And que seja definido o endpoint como "endpointHunter" </b> -> Define a URI da requisição, devendo ser configurada no arquivo api.properties, e passado aqui apenas o nome da variavel usada no arquivo .properties;

<h4 align="left"> Arquivo api.properties:
  <p>
Valores como client_id, secret e URIs devem ser colocados nesse arquivo.
  <img alt="apiProperties" title="#apiProperties" src="./assets/apiProperties.png" />
    </p>
</h4>

- <b> And que seja definido o payload "testeHunter" </b> -> Define o body request da requisição, sendo ele definido no arquivo payloads.properties e passado aqui apenas o nome usado para guardar o body;

<h4 align="left"> Arquivo payloads.properties:
  <p>
Valores como body request devem ser armazenados aqui(em formato JSON para APIs REST ou XML para APIs soap).
  <img alt="payloads" title="#payloads" src="./assets/payloads.png" />
    </p>
</h4>



#### Execução da requisição (When):

- <b> When executar uma requisicao GET </b> -> Executa uma requisição do tipo GET;
- <b> When executar uma requisicao POST </b> -> Executa uma requisição do tipo POST;
- <b> When executar uma requisicao PUT </b> -> Executa uma requisição do tipo PUT;
- <b> When executar uma requisicao PATCH </b> -> Executa uma requisição do tipo PATCH;
- <b> When executar uma requisicao DELETE </b> -> Executa uma requisição do tipo DELETE;


#### Validação da Resposta e teste (Then):

- <b> Then espero receber um response code "200" </b> -> Valida o response code recebido após o envio da requisição;
- <b> And armazene o valor do campo de response body "data.leads[0].id" na variavel "varNameTeste" </b> -> Armazena qualquer valor do RESPONSE BODY em uma variavel nova.
- <b> And espero que o campo "data.leads[0].id" do response body esteja com o valor de "(\d*)" </b> -> Valida qualquer valor do RESPONSE BODY baseado no campo mapeado, usa uma validação por REGEX, para validar campos que retornem valores fixos ou dinamicos, como datas, por exemplo.
- <b> And espero que nao exista o valor "teste" no body response </b> -> Valida que o valor não exista no RESPONSE BODY;


#### Função de simplificar o teste de API:

- <h4> Caso seu teste de API esteja ficando grande demais, é possivel diminuir ele em apenas 1 step assim como no teste WEB:</h4>

- <b>And simplifique o teste de API </b>-> Simplifica o teste de API, pegando todos os steps e gerando o codigo necessario para testa-lo em apenas um metodo ou step;
  <h4 align="left"> Exemplo de codigo gerado no log:
  <p>
  <img alt="apiSimplificado" title="#apiSimplificado" src="./assets/apiSimplificado.png" />
    </p>
</h4>

### Personalizar Codigo para teste:
 
 <p><b> Caso precise personalizar codigo para testar outras aplicações ou funções não cobertas pelo Framework, siga os passos abaixo para melhorar a eficacia dos seus testes personalizados: </b></p>
 
- <b> De acordo com a versão atual do seu Google Chrome, coloque o driver atualizado no diretorio: src/test/resources/drivers </b>
- <b> Assim que iniciado o driver, ele sera instanciado na classe "ConfigFramework" e será possivel acessa-lo em qualquer parte do codigo atraves do get getBrowser() , para isso, na classe voce precisa colocar extends ConfigFramework, assim como no exemplo abaixo: </b>
<h4 align="left"> Exemplo de uso do driver getBrowser:
  <p>
  <img alt="getBrowserExemplo" title="#getBrowserExemplo" src="./assets/getBrowserExemplo.png" />
    </p>
</h4>


- Crie seu arquivo <b>Page Objets</b> dentro do diretorio src/main/java/pom;
- Crie seu arquivo de <b>Actions</b> dentro do diretorio src/main/java/web;
- Crie seu arquivo de <b>Feature</b> dentro do diretorio src/test/resources/features;
- Crie seu arquivo de <b>StepDefinition</b> dentro do diretorio src/test/java/stepDefinitions;
- Para facilitar na navegação do Salesforce, voce pode usar os metodos estaticos de interação padrão do Selenium, colocados na classe: src/main/java/web/ActionUtils.java

<h4 align="left"> Hierarquia de diretorios da aba tests:
  <p>
  <img alt="pastas" title="#pastas" src="./assets/pastas.png" />
    </p>
</h4>

### Geração de Relatorios no ExtentReports:

 <p><b> Os Relatorios são gerados a cada teste com sua data e capturas de tela, afim de manter um historico de execuções, podendo ser personalizado no arquivo extent.properties </b></p>

- <b> captureScreenshotAndAddToReport("mensagem de log");</b> -> utilize esse comando de qualquer lugar nas pastas de stepDefinition para criar novos prints e serem adicionados ao relatorio final;


<h4 align="left"> Local de geração dos relatorios:
  <p>
  <img alt="sparkReport" title="#sparkReport" src="./assets/sparkReport.png" />
    </p>
</h4>

<h4 align="left"> Exemplo de relatorio gerado:
  <p>
  <img alt="reportExemplo" title="#reportExemplo" src="./assets/reportExemplo.png" />
    </p>
</h4>

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [java](https://dev.java/)
- [Rest-Assured](https://rest-assured.io/)
- [OKHTTP](https://square.github.io/okhttp/)
- [Selenium](https://www.selenium.dev/)
- [Cucumber](https://cucumber.io/)
- [Extent-Reports](https://www.extentreports.com/)




## Autor
---

<h4 align="left">
  <p>
  <img alt="ricardoVaz" title="#ricardoVaz" src="./assets/ricardoVaz.jpg" />
    </p>
</h4>
Feito com ❤️ por Ricardo Vaz 👋🏽 Entre em contato!

[![Linkedin Badge](https://img.shields.io/badge/-Ricardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/ricardo-junior-b44648197/)](https://www.linkedin.com/in/ricardo-junior-b44648197/)
