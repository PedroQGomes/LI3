# MIEI - 2ºAno - Laboratórios de Informática 3

O trabalho pratico proposto consiste em desenvolver um sistema de vendas de uma cadeia de Distribuição. O trabalho pratico está dividido em dois projetos, numa primeira fase o sistema de vendas é desenvolvido em C e numa segunda fase em Java.

## Projeto C

O projeto de C tinha o objetivo de desenvolver o sistema de vendas com os seguintes objetivos em mente:

* Modularidade e encapsulamento de dados usando construções da linguagem;
* Criação de código reutilizável;
* Escolha optimizada das estruturas de dados e reutilização;
* Testes de performance e até profiling


### Compilação e Testes

Para compilar é usado o seguinte comando

```
make program
```

Para executar o programa é usado o seguinte comando, é aconselhado ler os dados de "1M" durante a execução.

```
./program
```

Para limpar a diretoria é usado o comando

```
make clean
```

## Projeto Java

O projeto de Java consiste desenvolver uma aplicação desktop Java que seja capaz de ler e armazenar em estruturas de dados adequadas as
informações dos vários ficheiros, para que, posteriormente, possam ser realizadas diversas consultas (queries), algumas estatísticas e alguns testes de performance.

### Compilação e Testes
 
É aconselhável o uso de um **IDE** para a a compilação e execução do projeto. No caso de escolher o **intellij** é simplesmente criar um projeto com a opção de "from existing sources" e escolher a pasta Java.
A classe que contem o método main e que por isso inicializa o programa é a **GereVendasAppMVC**. 

Para mudar qualquer configuração de inicialização do sistema de vendas basta mudar o ficheiro "config.txt".
