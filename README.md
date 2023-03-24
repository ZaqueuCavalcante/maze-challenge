# Maze Challenge

Solution for Stone Automata Maze Challenge.

## Pontos que levei em consideracao para resolver o desafio

- Entender bem o problema proposto
- Modelagem com classes, estrutura de dados e diagramas
- Ter um jeito de visualizar a execucao do programa ao longo do tempo
- Ter testes automatizados para possibilitar produtividade e refatoracao
- Deixar o codigo generico, a ponto de aceitar diferentes inputs e regras de propagacao

## Ideia principal da solucao

`Iniciando em uma Celula qualquer do Labirinto, cada alteracao de estado do Automato gera um novo Nivel na Arvore.`

Isso escala rapidamente, mesmo para labirintos pequenos, pois para cada direcao (U, R, D, L) possivel, sao gerados mais 4 direcoes.

No caso maximo, temos: 4 -> 16 -> 64 -> 256 -> 1.024 -> 4.096 -> 16.384 -> 65.536 -> 262.144 -> 1.048.576 -> 4.194.304

## Como rodar o projeto?

- Instale o Processing?
- Instale a JDK na versao tal?

- Escolha o GameMode
- Escolhar o MazeOption
-

## Chutes pra fase 2

- Labirinto gigante
- Células inicial e final fora dos cantos do labirinto
- Player pode se mover nas diagonais tbm
- Células inicial e final (ou só a final) mudam de lugar a cada geração

## Melhorias

- Desenhar todos os caminhos se propagando no Labirinto
- Desenhar ao mesmo tempo a Arvore sendo criada
