# Maze Challenge

Solução para o Desafio do Labirinto Autômato Stone.

## Visualizando o problema

O labirinto a seguir está em seu estado inicial. Ele possui 3 linhas e 4 colunas.

O número em cada célula representa a quantidade de vizinhos verdes dela.

<img src="docs/simple-maze.png" alt= "Simple Maze" width="350" height="250">

Ele segue o modelo de propagação:

    - As células brancas com 2 ou 3 vizinhos viram verdes.
    - As células verdes com 4 ou 5 vizinhos permanecem verdes. Do contrário, viram brancas.

A seguir temos a evolução do labirinto para as 5 primeiras gerações:

<img src="docs/neighbors.gif" alt= "Neighbors" width="550" height="350">

Podemos representar os caminhos válidos no labirinto utilizando uma árvore:

    - Cada nó da árvore está associado a uma única célula no labirinto.
    - Cada célula do labirinto pode estar associada a mais de um nó da árvore.

Assim, a ideia principal da solução é:

`Iniciando em uma célula qualquer do labirinto, cada alteração de estado do automato gera um novo nível na árvore de caminhos.`

Isso escala rapidamente, mesmo para labirintos pequenos, pois para cada direção (U, R, D, L) possível, são gerados mais 4 direções.

No caso máximo, temos: 4 -> 16 -> 64 -> 256 -> 1.024 -> 4.096 -> 16.384 -> 65.536 -> 262.144 -> 1.048.576

Para o labirinto acima, segue a representação em árvore para as primeiras 5 gerações:

<img src="docs/maze_00_tree.png" alt= "Tree" width="600" height="500">

Assim, a cada gareção, os caminhos que se aproximam mais do final do labirinto são mantidos, enquanto os demais são descartados.

Uma das soluções pra esse é "DUDDURDRR";

---

## Pontos que levei em consideração para resolver o desafio

- Entender bem o problema proposto
- Modelagem com classes, estrutura de dados e diagramas
- Ter um jeito de visualizar a execução do programa ao longo do tempo
- Ter testes automatizados para possibilitar produtividade e refatoração
- Deixar o código genérico, a ponto de aceitar diferentes inputs e regras de propagação

## Como rodar o projeto?

- Instale a JDK 11
- Instale o Processing
- Escolha o GameMode
- Escolhar o MazeOption
- Inicie o programa

## Chutes pra fase 2

- Labirinto gigante
- Células inicial e final fora dos cantos do labirinto
- Player pode se mover nas diagonais tbm
- Células inicial e final (ou só a final) mudam de lugar a cada geração
