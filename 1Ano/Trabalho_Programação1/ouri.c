#include "ouri.h"

int main(int argc, char *argv[])
{
    // Inicializar o gerador de números aleatórios para o computador nao repetir jogadas
    srand(time(NULL));

    int tabuleiro[TAM_TABULEIRO];
    int pedrasPorBuraco = 4; // Definir pedras iniciais por buraco
    int modoDeJogo;

    if (argc == 2)
    {
        inicializarTabuleiro(tabuleiro, pedrasPorBuraco);
        inicializarTabuleiroFromFile(tabuleiro, argv[1]);
    }
    else
    {
        inicializarTabuleiro(tabuleiro, pedrasPorBuraco);
    }

    printf("Bem-vindo ao jogo de Ouri!\n");
    printf("Escolha o modo de jogo:\n");
    printf("1. Jogador vs Jogador\n");
    printf("2. Jogador vs Computador\n");
    printf("Opçao: ");
    scanf("%d", &modoDeJogo);

    if (modoDeJogo != 1 && modoDeJogo != 2)
    {
        printf("Opçao invalida. Escolha 1 ou 2.\n");
        return 1;
    }

    mostrarTabuleiro(tabuleiro);
    jogarJogo(tabuleiro, modoDeJogo - 1); // Ajustar para 0 ou 1 para representar o modo de jogo

    return 0;
}

int inicializarTabuleiroFromFile(int tabuleiro[], char *nomeFile)
{
    FILE *file = fopen(nomeFile, "r");

    if (file == NULL)
    {
        // Tentar abrir o ficheiro removendo a extensao .txt
        char nomeArquivoSemTxt[50];
        strcpy(nomeArquivoSemTxt, nomeFile);
        char *p = strrchr(nomeArquivoSemTxt, '.'); // Encontrar o último ponto na string

        if (p != NULL)
        {
            *p = '\0';                            // Remover a extensao .txt
            file = fopen(nomeArquivoSemTxt, "r"); // Tentar abrir o arquivo novamente sem a extensao
        }

        if (file == NULL)
        {
            perror("Erro ao abrir o ficheiro\n");
            return 0;
        }
    }

    fscanf(file, "%d", &tabuleiro[13]);
    for (int i = 12; i >= 7; --i)
    {
        fscanf(file, "%d", &tabuleiro[i]);
    }
    fscanf(file, "%d", &tabuleiro[6]);
    for (int i = 0; i < 6; ++i)
    {
        fscanf(file, "%d", &tabuleiro[i]);
    }

    fclose(file);
    return 1;
}

void inicializarTabuleiro(int tabuleiro[], int pedrasPorBuraco)
{
    for (int i = 0; i < TAM_TABULEIRO; ++i)
    {
        if (i != 6 && i != 13)
        { // Excluir os armazéns
            tabuleiro[i] = pedrasPorBuraco;
        }
        else
        {
            tabuleiro[i] = 0; // Os armazéns começam vazios
        }
    }
}

void mostrarTabuleiro(int tabuleiro[])
{
    printf("\n   Jogador B (6-1)\n");
    printf("|---|---|---|---|---|---|---|---|\n");
    printf("|   | %2d| %2d| %2d| %2d| %2d| %2d|   |\n", tabuleiro[12], tabuleiro[11], tabuleiro[10], tabuleiro[9], tabuleiro[8], tabuleiro[7]);

    printf("|%2d |-----------------------|%2d |\n", tabuleiro[13], tabuleiro[6]);

    printf("|   | %2d| %2d| %2d| %2d| %2d| %2d|   |\n", tabuleiro[0], tabuleiro[1], tabuleiro[2], tabuleiro[3], tabuleiro[4], tabuleiro[5]);
    printf("|---|---|---|---|---|---|---|---|\n");
    printf("   Jogador A (1-6)\n");
}

void jogarJogo(int tabuleiro[], int modoDeJogo)
{
    int jogadorAtual = PLAYER;
    int buracoSelecionado;

    while (1)
    {
        printf("\nVez do Jogador %c\n", (jogadorAtual == PLAYER) ? 'A' : 'B');

        if (modoDeJogo == COMPUTER && jogadorAtual == COMPUTER)
        {
            buracoSelecionado = obterMovimentoComputador(tabuleiro);
            printf("O computador escolheu o buraco %d.\n", buracoSelecionado + 1);
        }
        else
        {
            printf("Escolha um buraco (1-6) ou digite 0 para salvar o jogo: ");
            scanf("%d", &buracoSelecionado);

            if (buracoSelecionado == 0)
            {
                char nomeArquivo[50];
                printf("Digite o nome do arquivo para salvar: ");
                scanf("%s", nomeArquivo);

                FILE *arquivo = fopen(nomeArquivo, "w");

                fprintf(arquivo, "%d\n", tabuleiro[13]); // Número de pedras no depósito do player B
                for (int i = 12; i >= 7; --i)
                {
                    fprintf(arquivo, "%d ", tabuleiro[i]); // pedras em cada casa do player B
                }
                fprintf(arquivo, "\n%d\n", tabuleiro[6]); // Número de pedras no depósito do player A
                for (int i = 0; i < 6; ++i)
                {
                    fprintf(arquivo, "%d ", tabuleiro[i]); // pedras em cada casa do player A
                }

                fclose(arquivo);
                printf("Ficheiro salvo no seu sistema.\n");
                return;
            }

            buracoSelecionado--; // Ajustar para índice do array
        }

        if (buracoSelecionado < 0 || buracoSelecionado > 5 || tabuleiro[buracoSelecionado + (jogadorAtual * 7)] == 0)
        {
            printf("Buraco invalido ou vazio selecionado. Tente novamente.\n");
            continue;
        }

        fazerMovimento(tabuleiro, jogadorAtual, buracoSelecionado + (jogadorAtual * 7));
        mostrarTabuleiro(tabuleiro);

        jogadorAtual = (jogadorAtual == PLAYER) ? COMPUTER : PLAYER;

        // Verificar se o jogador atual ainda tem pedras
        int pedrasRestantes = 0;
        for (int i = jogadorAtual * 7; i < jogadorAtual * 7 + 6; ++i)
        {
            pedrasRestantes += tabuleiro[i];
        }

        if (pedrasRestantes == 0)
        {
            // Se o jogador atual nao tiver pedras restantes, o oponente deve introduzir pedras do seu lado
            printf("O Jogador %c nao tem mais pedras. O Jogador %c deve fazer um movimento.\n",
                   (jogadorAtual == PLAYER) ? 'A' : 'B', (jogadorAtual == PLAYER) ? 'B' : 'A');

            if (modoDeJogo == COMPUTER && jogadorAtual == PLAYER)
            {
                buracoSelecionado = obterMovimentoComputador(tabuleiro);
                printf("O computador escolheu o buraco %d.\n", buracoSelecionado + 1);
            }
            else
            {
                printf("Escolha um buraco (1-6) ou digite 0 para salvar o jogo: ");
                scanf("%d", &buracoSelecionado);

                if (buracoSelecionado == 0)
                {
                    char nomeArquivo[50];
                    printf("Digite o nome do arquivo para salvar: ");
                    scanf("%s", nomeArquivo);

                    FILE *arquivo = fopen(nomeArquivo, "w");

                    fprintf(arquivo, "%d\n", tabuleiro[13]); // Número de pedras no depósito do player B
                    for (int i = 12; i >= 7; --i)
                    {
                        fprintf(arquivo, "%d ", tabuleiro[i]); // pedras em cada casa do player B
                    }
                    fprintf(arquivo, "\n%d\n", tabuleiro[6]); // Número de pedras no depósito do player A
                    for (int i = 0; i < 6; ++i)
                    {
                        fprintf(arquivo, "%d ", tabuleiro[i]); // pedras em cada casa do player A
                    }

                    fclose(arquivo);
                    printf("Ficheiro salvo no seu sistema.\n");
                    return;
                }

                buracoSelecionado--; // Ajustar para índice do array
            }

            fazerMovimento(tabuleiro, jogadorAtual, buracoSelecionado + (jogadorAtual * 7));
            mostrarTabuleiro(tabuleiro);

            // Voltar ao jogador atual
            jogadorAtual = (jogadorAtual == PLAYER) ? COMPUTER : PLAYER;
        }

        int vencedor = verificarVencedor(tabuleiro, jogadorAtual);
        if (vencedor != -1)
        {
            printf("O Jogador %c venceu!\n", (vencedor == PLAYER) ? 'A' : 'B');
            break;
        }
    }
}

int fazerMovimento(int tabuleiro[], int jogadorAtual, int buracoSelecionado)
{
    int pedras = tabuleiro[buracoSelecionado];
    tabuleiro[buracoSelecionado] = 0;

    int posicao = buracoSelecionado;

    while (pedras > 0)
    {
        posicao = (posicao + 1) % TAM_TABULEIRO;

        if (jogadorAtual == PLAYER && posicao == 13)
        { // Saltar depósito do oponente
            continue;
        }
        else if (jogadorAtual == COMPUTER && posicao == 6)
        { // Saltar depósito do oponente
            continue;
        }

        if (posicao == jogadorAtual * 7 + 6)
        {
            // Se for o depósito do jogador atual, saltar para o próximo buraco
            posicao = (posicao + 1) % TAM_TABULEIRO;
        }

        tabuleiro[posicao]++;
        pedras--;

        if (pedras == 0)
        {
            // Verifica se a última pedra caiu numa casa do adversario
            int noLadoDoAdversario = (jogadorAtual == PLAYER && posicao >= 7 && posicao <= 12) ||
                                     (jogadorAtual == COMPUTER && posicao >= 0 && posicao <= 5);

            // Se caiu no lado do adversario e a casa ficou exatamente com 2 ou 3 pedras
            if (noLadoDoAdversario && (tabuleiro[posicao] == 2 || tabuleiro[posicao] == 3))
            {
                tabuleiro[jogadorAtual * 7 + 6] += tabuleiro[posicao]; // Adiciona as pedras ao próprio armazém
                tabuleiro[posicao] = 0;                                // Esvazia o buraco capturado
            }
        }
    }

    return posicao;
}

int obterBuracoOposto(int buraco)
{
    return 12 - buraco;
}

int verificarVencedor(int tabuleiro[], int jogadorAtual)
{
    if (tabuleiro[6] > 24)
    {
        printf("Parabéns! O Jogador A venceu com %d pedras no depósito.\n", tabuleiro[6]);
        return PLAYER; // Jogador A vence
    }
    else if (tabuleiro[13] > 24)
    {
        printf("Parabéns! O ");
        if (jogadorAtual == COMPUTER)
        {
            printf("computador");
        }
        else
        {
            printf("Jogador B");
        }
        printf(" venceu com %d pedras no depósito.\n", tabuleiro[13]);
        return COMPUTER;
    }
    return -1; // Sem vencedor ainda
}

int obterMovimentoComputador(int tabuleiro[])
{
    int attempt = 0;

    while (attempt < MAX_ATTEMPTS)
    {
        int movimento = rand() % 6; // Escolha aleatória entre 0 e 5

        if (tabuleiro[movimento + 7] == 0)
        {
            attempt++;
            continue; // Evitar casas vazias
        }

        int simulacaoTabuleiro[TAM_TABULEIRO];
        for (int j = 0; j < TAM_TABULEIRO; ++j)
        {
            simulacaoTabuleiro[j] = tabuleiro[j];
        }

        int posicao = fazerMovimento(simulacaoTabuleiro, COMPUTER, movimento + 7);

        // Verificar se o movimento é invalido
        if (simulacaoTabuleiro[posicao] == 1 && ((posicao >= 0 && posicao <= 5 && COMPUTER == PLAYER) || (posicao >= 7 && posicao <= 12 && COMPUTER == COMPUTER)))
        {
            attempt++;
            continue; // Tentar outro movimento
        }

        return movimento;
    }

    // Se o loop exceder o nº maximo de tentativas, escolhe um nº random valido
    int validMove = rand() % 6;
    while (tabuleiro[validMove + 7] == 0)
    {
        validMove = rand() % 6;
    }

    return validMove;
}