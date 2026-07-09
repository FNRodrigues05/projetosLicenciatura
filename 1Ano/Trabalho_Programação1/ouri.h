// 59111_59115_59119
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define TAM_TABULEIRO 14 // Tamanho do tabuleiro (12 buracos para as pedras por jogador + 2 depósitos)
#define PLAYER 0
#define COMPUTER 1
#define MAX_ATTEMPTS 3

void inicializarTabuleiro(int tabuleiro[], int pedrasPorBuraco);
void mostrarTabuleiro(int tabuleiro[]);
void jogarJogo(int tabuleiro[], int modoDeJogo);
int fazerMovimento(int tabuleiro[], int jogadorAtual, int buracoSelecionado);
int verificarVencedor(int tabuleiro[], int jogadorAtual);
int obterBuracoOposto(int buraco);
int obterMovimentoComputador(int tabuleiro[]);
int inicializarTabuleiroFromFile(int tabuleiro[], char *nomeFile);