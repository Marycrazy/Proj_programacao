## Introdução

Neste  projecto, pretende-se a implementação  de uma aplicação completa  em linguagem Java, sem componente gráfica para interacção com o utilizador, recorrendo ao paradigma de Programação Orientada a Objectos.
A aplicação tem como objectivo auxiliar o funcionamento de uma loja de informática na gestão de encomendas de equipamentos de hardware

## Descrição global da aplicação:
- [X] [R1] Permitir os utilizadores registarem-se e autenticarem-se na aplicação.
- [x] [R2] Permitir o acesso à aplicação por 3 tipos de utilizadores: administradores, técnicos e clientes.
- [x] [R3] Os utilizadores são caracterizados pelos atributos login, password, nome, estado (activo/inactivo), email e tipo (administradores, técnicos ou clientes).
- [x] [R4] Cada utilizador apenas pode alterar a sua própria informação, não podendo alterar ou visualizar dados de outros utilizadores, ou criar utilizadores.
> [Note] mostrar ao utilizar a sua informação e pedir a informaçao que quer alterar.
- [x] [R5] O login e email devem ser únicos.
- [x] [R6] Os clientes e técnicos caracterizam-se adicionalmente por número de identificação fiscal (NIF), morada e
contacto telefónico.
> [NOTE] O NIF e contacto telefónico são únicos.
- [x] [R7]  Caso não existam utilizadores criados, a aplicação deve solicitar a criação de um utilizador (administrador).
- [x] [R8]  Após a autenticação, a aplicação deve apresentar a mensagem
`“Bem-vindo [nome utilizador]”`.
- [x] [R9] Quando a aplicação estiver a encerrar, deve apresentar a mensagem
`“Adeus [nome utilizador]”`.

### Funções
- [x] [R10] Os  administradores  aprovam  os  pedidos  de  registo  dos  utilizadores.  Todos  os  pedidos  devem  ser  aprovados antes de poderem ser usados para autenticação.
- [ ] [R11] Os administradores  aprovam e encerram os  pedidos de serviço  (encomenda),  bem como  associam um técnico responsável a cada serviço.
- [x] [R12] Os técnicos introduzem equipamentos, categorias e fornecedores.
>[NOTE] vou assumir que quando for para intruduzir os equipamentos, já existam categoria e fornecedores criados e sendo assim ele tera de associar
- [ ] [R13] Os clientes realizam pedidos de compras de equipamentos, que devem ser aprovadas pelos técnicos.
- [ ] [R14] Os administradores podem gerir todas as vendas realizadas.
- [ ] [R15] Os técnicos apenas podem gerir as suas vendas.
- [ ] [R16] Os clientes apenas podem gerir as suas compras

### Ações
- [ ] [R17] O serviço de compra inicia com o cliente a solicitar uma encomenda, indicando os produtos pretendidos.
- [ ] [R18] O administrador aprova o serviço e associa um técnico responsável.
- [ ] [R19] Após ser designado para um serviço, o técnico responsável regista a informação do processo,nomeadamente  pode  criar  sub-tarefas e associar  outros  técnicos  adicionais  às  sub-tarefas, e gere o processo até à sua conclusão.
- [ ] [R20] Um serviço de compra (encomenda), além de ter associado um técnico responsável, inclui a listagem dos produtos incluídos (equipamentos de hardware, podendo definir a quantidade), uma data de realização do pedido, uma descrição, o estado e o tempo que demorou a ser processado o valor total da venda.
- [ ] [R21] Adicionalmente, cada serviço possui um código identificador único e sequencial.
- [ ] [R22] O estado do serviço apresenta um dos seguintes valores:
> [NOTE] a) se o serviço está *submetido* (cliente realizou o pedido, mas ainda não foi aceite pelo administrador);
- > b) se a serviço foi *aceite* (administrador aceita o serviço);
- > c) ou se já foi *concluído* (técnico responsável termina o processo).
- [ ] [R23] Quando um técnico conclui um serviço deve indicar o tempo despendido no processamento do pedido.
O valor total da venda deve ser calculado automaticamente (somatório do custo dos vários equipamentos e respectivas quantidades).
- [x] [R24] A aplicação deve permitir introduzir e gerir equipamentos de hardware.
Os equipamentos de hardware são caracterizados por uma marca, modelo, código interno, série, versão,
voltagem, quantidade em stock, preço de venda, observações e se o equipamento é OEM (Original
Equipment Manufacturer).
- [x] [R25] O código de um equipamento deve ser único.
- [x] [R26] Cada  equipamento  deve  ter  associado  uma lista até 6 fornecedores, podendo ser introduzido um  novo fornecedor a qualquer momento.
- [x] [R27] Cada fornecedor é caracterizado por nome, morada e contacto telefónico.
- [x] [R28] Cada  equipamento  deve  apresentar  uma  lista  até  4  categorias,  podendo  ser  introduzida  uma  nova categoria a qualquer momento.
- [x] [R29] Cada categoria é caracterizada pela sua designação e família (atributos alfanuméricos).
- [x] [R30] Cada série possui uma geração e sequência (atributos numéricos).
- [x] [R31] Cada versão é caracterizada pela unidade, valor alfa e valor beta (atributos numéricos).
- [x] [R32] É possível alterar a quantidade em stock de um equipamento.
- [ ] [R33] Sempre que um equipamento é vendido,a quantidade em stock deve ser actualizada.

## Listagem de Pesquisas
- [x] [R36] Deve ser possível ordenar utilizadores por ordem alfabética do nome.
- [ ] [R37] Deve ser possível ordenar equipamentos por designação.
- [x] [R39] Deve ser possível listar todos os utilizadores.
- [x] [R40] Deve ser possível listar utilizadores por tipo.
- [ ] [R41] Deve ser possível listar todos os serviços.
- [ ] [R42] Deve ser possível listar todos os serviços associados a um cliente.
- [ ] [R43] Deve ser possível listar todos os serviços por estado.
- [ ] [R44] Deve ser possível listar serviços com tempo despendido superior a um determinado limite (introduzido pelo utilizador no momento de pesquisa).
- [ ] [R45] Deve ser possível listar todos os equipamentos.
- [ ] [R46] Deve ser possível listar equipamentos que sejam (ou não) OEM.
- [ ] [R47] Deve  ser  possível  listar  equipamentos  com  uma  quantidade  de  stock  abaixo  de  um  determinado  limite 
(introduzido pelo utilizador no momento de pesquisa).
- [x] [R49] Deve ser possível pesquisar utilizadores por login ou nome.
- [ ] [R50] Deve ser possível pesquisar serviços por código ou uma palavra que surja na descrição.
- [ ] [R51] Deve ser possível pesquisar equipamentos por marca ou código.
- [ ] [R52] Deve ser possível pesquisar equipamentos que possuem uma determinada categoria.
- [ ] [R53] Deve ser possível realizar pesquisas avançadas, ou seja, apresentar todos os registos que apresentem um termo de pesquisa, mesmo que parcialmente
- (e.g. termo de pesquisa “Ana” deve apresentar como resultado “Ana Sousa”, “Ana Silva” e “Anabela”).

- [ ] [R54] Os clientes podem listar e pesquisar os seus serviços que realizaram.
- [ ] [R55] Os técnicos podem listar e pesquisar os seus serviços que processaram.
- [ ] [R56] Os administradores podem listar e pesquisar todos os serviços.

## Manipulação e armazenamento de dados persistente
- [x] [R57] O  acesso  à  aplicação  deve  ser  restringido  com  credenciais  (login/password),  informação  que  deverá ser armazenada num ficheiro de texto “credenciais_acesso.txt”.
- [x] [R58] Durante  o encerramento da aplicação, os dados devem ser  automaticamente guardados num ficheiro de objectos “dados_apl.dat”.
- [x] [R59] A aplicação, no arranque, deve automaticamente ler os dados do ficheiro de objectos “dados_apl.dat”,  caso este exista, e informar o utilizador que os dados foram lidos com sucesso.
- Caso o ficheiro esteja indisponível deve surgir uma mensagem a informar a situação

## Gestão geral da aplicação
### Interacção com o utilizador
- [ ] [R61] Disponibilizar uma interface em modo texto onde o utilizador possa interagir e controlar a aplicação.

### Monitorização de acessos
- [ ] [R62] Todas as acções dos utilizadores deverão ser guardadas num ficheiro de texto denominado “log.txt”.
Este ficheiro deverá ser escrito de forma sequencial, apresentando as acções mais recentes no início do
ficheiro.
As entradas deverão ter o seguinte formato: <utilizador> <acção>.
- [ ] [R63] Deve ser possível consultar o log de acções através da aplicação.
- [ ] [R64] Deverá ser registado o número total (até ao momento) de execuções do sistema e o username do último
utilizador que acedeu à aplicação.
Esta informação deverá ser guardada num ficheiro de objectos e actualizada sempre que a aplicação é
executada, apresentando os dados ao utilizador.
Quando a aplicação é terminada esta informação deve ser novamente armazenada no ficheiro, sendo este
denominado “info_sistema.dat”.

### Programação Orientada a Objectos
- [ ] [R65] A aplicação deve estar correctamente  estruturada, tendo em conta o paradigma Orientado a Objectos e recorrendo à linguagem Java.
- [ ] [R66] Implemente as estruturas de armazenamento necessárias, procurando optimizar os recursos utilizados.

### Validação de dados e notificações
- [ ] [R67] Valide todas as leituras de dados do utilizador
- (e.g. verifique se os nomes são únicos).
- [ ] [R68] Sempre que necessário, apresentar ao utilizador mensagens informativas adequadas.
- Quando um utilizador realizar uma acção sobre a aplicação, esta deve informar se acção foi realizada com sucesso ou insucesso.

- utilizar a classe sistemas para guardar varios objetos do mesmo tipo dentro depois do ficheiro.
- Erro ao carregar os dados: src.Sistema; local class incompatible: stream classdesc serialVersionUID = -5152247744269242336, local class serialVersionUID = 3228776543520407799


### Referências
https://www.oracle.com/br/technical-resources/articles/java/serialversionuid.html
https://pt.stackoverflow.com/questions/408183/o-serialversionuid-de-tem-que-ser-o-mesmo-em-todas-as-classes-serializ%C3%A1veis
- // GraalVM 21 Documentation
- https://www.graalvm.org/jdk21/docs/introduction/
- https://www.graalvm.org/21.3/docs/introduction/

- // Java 21 API Specification
- https://docs.oracle.com/en/java/javase/21/docs/api/index.html

- // Why BigDecimals and Duration
- https://www.geeksforgeeks.org/bigdecimal-class-java/
- https://www.geeksforgeeks.org/java-time-duration-class-in-java/

- // Java I/O (why nio2 and not java.io)
- https://docs.oracle.com/javase/tutorial/essential/io/file.html
- https://dev.java/learn/java-io/reading-writing/buffered-text/
- https://medium.com/@vusal.guliyev.313/writing-files-with-nio-and-io-in-java-bc60b06a413a
- https://www.geeksforgeeks.org/read-and-write-files-using-the-new-i-o-nio-2-api-in-java/

Eumeration vs Stream
- https://medium.com/@rrmartins/understanding-the-difference-between-enum-and-stream-in-elixir-ebd8615e0879
- https://www.geeksforgeeks.org/stream-nonematch-method-java-examples/
- https://www.geeksforgeeks.org/stream-filter-java-examples/
- https://stackoverflow.com/questions/71888924/how-to-filter-element-from-a-list-using-java-8-streams
- https://www.oracle.com/br/technical-resources/articles/java/processing-streams-java-se-8.html
- https://stackoverflow.com/questions/39432699/what-is-the-difference-between-streams-and-collections-in-java-8/39432937
- https://piotrminkowski.com/2024/04/25/interesting-facts-about-java-streams-and-collections/
- https://mballem.com/post/java-8-filtrando-dados-com-stream/
- https://rinaldo.dev/java-8-streams-pare-de-usar-for-e-simplifique-seu-codigo/
- https://pt.linkedin.com/advice/1/how-do-you-choose-between-java-stream-collection-data-processing?lang=pt
- https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html
- https://mmarcosab.medium.com/usando-streams-no-java-b85d98d9cf17

count and size:
- https://rameshfadatare.medium.com/java-stream-count-method-with-examples-7e65a8edadc0
- https://www.geeksforgeeks.org/java-util-stream-collectors-counting-method-examples/
- https://howtodoinjava.com/java8/stream-count-elements-example/
- https://www.geeksforgeeks.org/stream-count-method-java/
- https://www.geeksforgeeks.org/list-size-method-in-java-with-examples/#:~:text=The%20size()%20method%20of,present%20in%20this%20list%20container.
- https://www.scaler.com/topics/java-list-size/
- https://www.javatpoint.com/java-list-size-method