#language: en
@testeFrame
Feature: teste framework

  Background: tempo de espera medio
    Given que o tempo de espera medio sera de 15 segundos
    And que a variavel "varNomeOportunidade" tenha o valor "oportunidade Random"

  @webteste
  Scenario: teste em ingles de criação e validacao de contas
    Given que o tempo de espera medio sera de 20 segundos
    And que esteja logado no SalesForce com sucesso com o usuario "Gerente"
    When Accesar o objeto "Accounts" e criar um novo registro
    And preencher o campo "Account Name" com o valor "testeConta Random"
    And preencher o campo "Rating" com o valor "Hot"
    And preencher o campo "Parent Account" com o valor "Postman"
    Then Salvar a criacao do registro com sucesso


  @webteste2
  Scenario: teste em portugues de criação e validacao de contas
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    #When Accesar o objeto "Casos" e mudar o modo de exibicao da lista para "Meus casos"
    When Accesar o objeto "Contas" e criar um novo registro
    And preencher o campo "Nome da conta" com o valor "testeConta Random"
    And preencher o campo "Classificação" com o valor "Hot"
    And preencher o campo "Conta pai" com o valor "Postman"
    And preencher o campo "SLA" com o valor "Gold"
    And preencher o campo "SLA Serial Number" com o valor "1234Random"
    Then Salvar a criacao do registro com sucesso
    And simplifique o teste de preenchimento

  @webteste2
  Scenario: teste simplificado
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    Given que seja criado o objeto conta predefinido


  @webtesteErro
  Scenario: teste em portugues de criação de contas com erro
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When Accesar o objeto "Contas" e criar um novo registro
    And preencher o campo "Classificação" com o valor "Hot"
    And preencher o campo "Conta pai" com o valor "Postman"
    And preencher o campo "SLA" com o valor "Gold"
    And preencher o campo "SLA Serial Number" com o valor "1234Random"
    Then Salvar a criacao do registro com falha
    And busca pela mensagem de erro "Revise os seguintes campos"

  @webtesteTarefas
  Scenario: teste em portugues de criação de contas com sucesso
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When Accesar o objeto "Tarefas" e criar um novo registro
    And preencher o campo "Assunto" com o valor "Call"
    And preencher o campo "Prioridade" com o valor "Low"
    And preencher o campo "Comentários" com o valor "teste comentario"
    Then Salvar a criacao do registro com sucesso


  @webtesteValidaRegistro
  Scenario: pesquisar e validar um registro
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When acessar o registro "Andy Young"
    Then espero que o campo "Nome completo" esteja com o valor "Mr Andy Young"
    And espero que o campo "Celular" esteja com o valor "^(.*)$"
    And capture o valor do campo "Celular" e armazene na variavel "varCelular"
    And capture o valor do campo "departamento" e armazene na variavel "varDepartamento"
    And espero que o campo "Level" esteja com o valor "Primary"
    And edite o campo "Telefone residencial" com o valor da variavel "varCelular"
    And edite o campo "Departamento" com o valor "testeValor"
    And simplifique o teste de validacao

    #exemplo de teste completo longo, passando por todas as etapas sem simplificar
  @webtesteFluxoCompleto
  Scenario: teste em portugues um fluxo de oportunidade de contrato
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When Accesar o objeto "Oportunidades" e criar um novo registro
    And clicar no checkbox "Particular"
    And preencher o campo "Nome da oportunidade" com o valor da variavel "varNomeOportunidade"
    And preencher o campo "Data de fechamento" com o valor "27/04/2023"
    And preencher o campo "Fase" com o valor "Qualification"
    And preencher o campo "Nome da conta" com o valor "testeConta Bdyxj"
    And preencher o campo "Delivery/Installation Status" com o valor "In progress"
    Then Salvar a criacao do registro com sucesso
    And simplifique o teste de preenchimento
    And clicar na acao de criar um "Novo caso"
    And preencher o campo "Nome do contato" com o valor "Andy Young"
    And preencher o campo "Assunto" com o valor "teste de caso"
    And preencher o campo "Status" com o valor "Working"
    And Salvar a criacao do registro com sucesso
    And simplifique o teste de preenchimento


    #exemplo de teste completo simplificado a partir de outro teste longo
  @testeCompletoSimplificado
  Scenario: teste simplificado em portugues um fluxo de oportunidade de contrato
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When criar uma oportunidade em progresso
    And criar um novo caso para a oportunidade
    Then clicar no botao pelo texto "Marcar Fase como concluído(a)"


  @criaRegistroSimplificado
  Scenario: teste em portugues de criação e validacao de contas
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When criar uma conta de massa de testes
    When criar uma massa de oportunidade

  @validaRegistroSimplificado
  Scenario: pesquisar e validar um registro
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    When valida a conta do Andy


  @testeWorkOrder
  Scenario: pesquisar e validar um registro
    Given que esteja logado no SalesForce com sucesso com o usuario "CEO"
    #And entrar no frame pelo xpath "//iframe"
    #And voltar para o conteudo fora do frame
   # When acessar o registro hexadecimal "varRegistroWorkOrder" pela url
    When acessar o registro hexadecimal "0WO8b000000fQEFGA2" pela url
    And que esteja logado no SalesForce com sucesso com o usuario "Gerente"




    #-----------------------------------------#
  @Teste1
  Scenario: consulta leads hunter
    Given que seja definido o header "Accept" com o valor "application/json"
    And que seja definido o endpoint como "endpointHunter"
    When executar uma requisicao GET
    Then espero receber um response code "200"
    And armazene o valor do campo de response body "data.leads[0].id" na variavel "varNameTeste"
    And espero que o campo "data.leads[0].id" do response body esteja com o valor de "(\d*)"
    And simplifique o teste de API


  @Teste2
  Scenario: cria leads hunter
    Given que seja definido o header "Accept" com o valor "application/json"
    And que seja definido o header "Content-Type" com o valor "application/json"
    And que seja definido o endpoint como "endpointHunter"
    And que seja definido o payload "testeHunter"
    When executar uma requisicao POST
    Then espero receber um response code "201"
    And armazene o valor do campo de response body "data.id" na variavel "varNameTeste"
    And espero que o campo "data.first_name" do response body esteja com o valor de "(.+)"
    And simplifique o teste de API
