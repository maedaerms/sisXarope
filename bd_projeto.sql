create table funcionario(
	id int not null auto_increment,
	nome varchar(50) not null,
    rg varchar(9),
    cpf varchar(11) not null,
    endereco varchar(50),
    sexo enum('M','F'),
    cargo varchar(30),
    salario double,
    nascimento date,
    admissao date,
    usuario varchar(30),
    senha varchar(30),
    primary key (id)
) default charset = utf8;

/*RF_B01: Manter Funcionários. Esta função permite incluir, excluir, alterar e pesquisar Funcionários.
Itens de Dados: Nome*, RG*, CPF*, Endereço*, Sexo*, Cargo, Salário, e Data de Nascimento*, Data de admissão, Nome de usuário e Senha.
*: itens obrigatórios.
*/

create table cliente(
	id int not null auto_increment,
	nome varchar(50) not null,
    rg varchar(9),
    cpf varchar(11) not null,
    endereco varchar(50),
    sexo enum('M','F'),
    nascimento date,
    primary key (id)
) default charset = utf8;

/*
RF_B02: Manter Cliente. Esta função permite incluir, alterar, pesquisar cliente e não permitir a exclusão do mesmo. 
Itens de dados: Nome*, RG*, CPF*, Sexo*, Data nascimento*, Saldo gasto*e Saldo devedor*.
*: itens obrigatórios.
*/

create table produto(
	id int not null auto_increment,
	nome varchar(50) not null,
    custo double not null,
    venda double not null,
    fornecedor int,
    qtd int,
    fabricacao date,
    categoria int,
    validade date,
    primary key (id)
) default charset = utf8;

/*
RF_B03: Manter Produtos. Esta função permite incluir, excluir, alterar e pesquisar produtos.
Itens de dados: Nome do produto*, Preço de custo*, Preço de venda, Fornecedor, Quantidade*, Data de Fabricação*, Categoria  e Data de validade*.
*: itens obrigatórios.
*/

create table fornecedor(
	id int not null auto_increment,
	nome varchar(50) not null,
    cnpj varchar(14) not null,
    ie varchar(12),
    endereco varchar(50),
    telefone varchar(11),
    primary key (id)
) default charset = utf8;

/*
RF_B05: Manter Fornecedor. Esta função permite incluir, excluir, alterar e pesquisar fornecedor.
 Itens de dados: Nome do fornecedor*, CNPJ*, Inscrição Estadual, Endereço*, Telefone*.
*: itens obrigatórios.
*/

create table usuario(
	id int not null auto_increment,
    usuario varchar(30) not null,
    senha varchar(8),
    primary key (id)
) default charset = utf8;

/*
RF_B06: Sistema de login. O sistema deverá ter um sistema de login e senha, em que cada funcionário terá acesso às funcionalidades
e aos dados conforme seu cargo. Itens de Entrada: Usuário ou CPF.
*/

create table categoria(
	id int not null auto_increment,
    nome varchar(30) not null,
    primary key (id)
) default charset = utf8;


