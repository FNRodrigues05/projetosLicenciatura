import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GURU2P2 {

    private List<String> allLevels;
    private List<String> currentLevelWords;
    private Set<String> guessedWords;// Lista de palavras adivinhadas sem valores duplicados
    private Set<String> validExtraWords;
    private String currentLetters;
    private int currentLevelIndex;
    private int coins;
    private Map<String, Integer> hintsIndexMap; // Mapa para os índices de dicas para cada palavra

    private JFrame frame;
    private JPanel lettersPanel;
    private JPanel wordsPanel;
    private JLabel coinsLabel;
    private JTextField wordInputField;

    private Set<String> dictionary;

    // Construtor para inicializar o jogo
    public GURU2P2(String levelsFile, String dictionaryFile) throws IOException {
        this.allLevels = loadLevels(levelsFile);
        this.dictionary = loadDictionary(dictionaryFile);
        this.guessedWords = new HashSet<>();
        this.validExtraWords = new HashSet<>();
        this.currentLevelIndex = 0;
        this.coins = 0;
        loadLevelData(currentLevelIndex);
    }

    // Método para carregar os níveis do arquivo numa lista
    private List<String> loadLevels(String filename) throws IOException {
        List<String> levels = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder level = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (level.length() > 0) {
                        levels.add(level.toString().trim());
                        level.setLength(0);
                    }
                } else {
                    level.append(line).append("\n");
                }
            }
            if (level.length() > 0) {
                levels.add(level.toString().trim());
            }
        }
        return levels;
    }

    // Método para carregar o dicionário de palavras válidas
    private Set<String> loadDictionary(String filename) throws IOException {
        Set<String> words = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim().toUpperCase());
            }
        }
        return words;
    }

    // Método para carregar os dados do nível especificado pelo índice
    private void loadLevelData(int levelIndex) {
        if (levelIndex >= allLevels.size()) {
            JOptionPane.showMessageDialog(frame, "Acabaste todos os níveis!! Parabéns!");
            return;
        }

        String[] levelData = allLevels.get(levelIndex).split("\n");
        currentLetters = levelData[0].replaceAll("\\s+", "");
        currentLevelWords = Arrays.asList(Arrays.copyOfRange(levelData, 1, levelData.length));
        guessedWords.clear();
        validExtraWords.clear();

        // Inicializa o mapa de dicas para cada palavra
        hintsIndexMap = new HashMap<>();
        for (String word : currentLevelWords) {
            hintsIndexMap.put(word, 0);
        }
    }

    // Método para criar e exibir a interface gráfica do jogo
    private void createAndShowGUI() {
        frame = new JFrame("GURU2P2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel inicial com o nome do jogo e botões
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        startPanel.setBackground(new Color(135, 206, 235)); // Azul claro

        // Configurando o título com uma fonte personalizada
        JLabel titleLabel = new JLabel("GURU2P2", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        titleLabel.setForeground(new Color(255, 69, 0)); // Vermelho alaranjado

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 223, 186)); // Pastel

        JButton startButton = new JButton("Jogar Jogo");
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(50, 205, 50)); // Verde claro
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> startGame());

        JButton exitButton = new JButton("Sair");
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(220, 20, 60)); // Vermelho
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        startPanel.add(titleLabel, BorderLayout.CENTER);
        startPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(startPanel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    // Método para iniciar o jogo
    private void startGame() {
        frame.getContentPane().removeAll();
        frame.repaint();
        initializeGameGUI();
    }

    // Método para inicializar o jogo após clicar em "Jogar Jogo"
    private void initializeGameGUI() {
        // Painel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(250, 250, 210)); // Amarelo

        coinsLabel = new JLabel("Moedas: " + coins, SwingConstants.CENTER);
        coinsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        coinsLabel.setForeground(new Color(184, 134, 11)); // Dourado
        topPanel.add(coinsLabel, BorderLayout.WEST);

        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonGroup.setBackground(new Color(250, 250, 210));

        JButton saveButton = new JButton("Salvar");
        saveButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        saveButton.setBackground(new Color(144, 238, 144)); // Verde claro
        saveButton.setForeground(Color.BLACK);
        saveButton.addActionListener(e -> saveGame("savegame.txt"));

        JButton loadButton = new JButton("Carregar");
        loadButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        loadButton.setBackground(new Color(173, 216, 230)); // Azul claro
        loadButton.setForeground(Color.BLACK);
        loadButton.addActionListener(e -> loadGame("savegame.txt"));

        JButton hintButton = new JButton("Dica (100 Moedas)");
        hintButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        hintButton.setBackground(new Color(255, 182, 193)); // Rosa claro
        hintButton.setForeground(Color.BLACK);
        hintButton.addActionListener(e -> giveHint());

        JButton exitButton = new JButton("Sair");
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        exitButton.setBackground(new Color(255, 69, 0)); // Vermelho vibrante
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(saveButton);
        controlsPanel.add(loadButton);
        controlsPanel.add(hintButton);
        controlsPanel.add(exitButton);

        topPanel.add(coinsLabel, BorderLayout.WEST);
        topPanel.add(controlsPanel, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);

        // Painel central para as letras e palavras
        lettersPanel = new JPanel(new GridLayout(1, currentLetters.length(), 10, 10));
        lettersPanel.setBackground(new Color(230, 230, 250)); // Lavanda
        lettersPanel.setBorder(BorderFactory.createTitledBorder(null, "Letras", 0, 0,
                new Font("Comic Sans MS", Font.BOLD, 16), Color.BLUE));
        updateLettersPanel();

        wordsPanel = new JPanel(new GridLayout(0, 1));
        wordsPanel.setBackground(new Color(245, 245, 220)); // Bege
        wordsPanel.setBorder(BorderFactory.createTitledBorder(null, "Palavras", 0, 0,
                new Font("Comic Sans MS", Font.BOLD, 16), Color.GREEN));
        updateWordsPanel();

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBackground(new Color(245, 245, 245)); // Cinza claro
        centerPanel.add(lettersPanel);
        centerPanel.add(wordsPanel);

        frame.add(centerPanel, BorderLayout.CENTER);

        // Painel inferior para a caixa de texto
        wordInputField = new JTextField();
        wordInputField.setFont(new Font("Arial", Font.BOLD, 20));
        wordInputField.addActionListener(e -> handleWordInput());

        JPanel inputPanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Digite uma palavra: ");
        label.setForeground(new Color(50, 168, 160));
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        inputPanel.add(label, BorderLayout.WEST);
        inputPanel.add(wordInputField, BorderLayout.CENTER);

        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    // Método para atualizar o painel superior de letras
    private void updateLettersPanel() {
        lettersPanel.removeAll();
        for (char c : currentLetters.toCharArray()) {
            JLabel letterLabel = new JLabel(String.valueOf(c));
            letterLabel.setFont(new Font("Arial", Font.BOLD, 24));
            letterLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            lettersPanel.add(letterLabel);
            letterLabel.setHorizontalAlignment(SwingConstants.CENTER);
            letterLabel.setVerticalAlignment(SwingConstants.CENTER);
        }
        lettersPanel.revalidate();
        lettersPanel.repaint();
    }

    // Método para atualizar o painel de palavras
    private void updateWordsPanel() {
        wordsPanel.removeAll();
        for (String word : currentLevelWords) {
            JLabel wordLabel = new JLabel(guessedWords.contains(word) ? word : "_ ".repeat(word.length()));
            wordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            wordsPanel.add(wordLabel);
        }
        wordsPanel.revalidate();
        wordsPanel.repaint();
    }

    // Método para verificar se a palavra pode ser formada com as letras atuais
    private boolean canFormWord(String word, String letters) {
        Set<Character> letterSet = new HashSet<>();
        for (char c : letters.toCharArray()) {
            letterSet.add(c);
        }

        for (char c : word.toCharArray()) {
            if (!letterSet.contains(c)) {
                return false;
            }
        }
        return true;
    }

    // Método para lidar com a entrada de palavras do jogador
    private void handleWordInput() {
        String input = wordInputField.getText().toUpperCase().trim();
        wordInputField.setText("");
        if (!input.isEmpty()) {
            if (currentLevelWords.contains(input) && !guessedWords.contains(input)) {
                guessedWords.add(input);
                coins += 10;
                coinsLabel.setText("Moedas: " + coins);
                updateWordsPanel();

                // Remove a palavra do mapa de dicas
                hintsIndexMap.remove(input);

                // Verificar se todas as palavras foram adivinhadas
                if (guessedWords.size() == currentLevelWords.size()) {
                    JOptionPane.showMessageDialog(frame, "Nível Completo!");
                    currentLevelIndex++;
                    loadLevelData(currentLevelIndex);
                    updateLettersPanel();
                    updateWordsPanel();
                }
            } else if (dictionary.contains(input) && canFormWord(input, currentLetters)
                    && !guessedWords.contains(input)) {
                if (!validExtraWords.contains(input)) {
                    validExtraWords.add(input);
                    coins += 10;
                    coinsLabel.setText("Moedas: " + coins);
                    JOptionPane.showMessageDialog(frame,
                            "Palavra válida, mas não está na lista do nível. Você ganhou 10 moedas!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Você já usou essa palavra antes!");
                }
            } else if (guessedWords.contains(input)) {
                JOptionPane.showMessageDialog(frame, "Você já usou essa palavra antes!");
            } else {
                JOptionPane.showMessageDialog(frame, "Palavra incorreta ou inválida.");
            }
        }
    }

    // Método para salvar o jogo atual num arquivo
    private void saveGame(String saveFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(saveFile))) {
            writer.println(currentLevelIndex);
            writer.println(coins);
            writer.println(String.join(",", guessedWords));
            writer.println(String.join(",", validExtraWords));
            JOptionPane.showMessageDialog(frame, "Jogo salvo com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    // Método para carregar o jogo salvo do arquivo savegame.txt
    private void loadGame(String saveFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            currentLevelIndex = Integer.parseInt(reader.readLine());
            coins = Integer.parseInt(reader.readLine());
            String guessed = reader.readLine();
            String extra = reader.readLine();
            loadLevelData(currentLevelIndex);
            if (guessed != null && !guessed.isEmpty()) {
                guessedWords = new HashSet<>(Arrays.asList(guessed.split(",")));
            }
            if (extra != null && !extra.isEmpty()) {
                validExtraWords = new HashSet<>(Arrays.asList(extra.split(",")));
            }
            coinsLabel.setText("Moedas: " + coins);
            updateLettersPanel();
            updateWordsPanel();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao carregar o jogo: " + e.getMessage());
        }
    }

    // Método para dar uma dica ao jogador
    private void giveHint() {
        if (coins >= 100) {
            coins -= 100;
            coinsLabel.setText("Moedas: " + coins);

            for (String word : currentLevelWords) {
                if (!guessedWords.contains(word)) {

                    int hintIndex = hintsIndexMap.get(word);
                    // Mostra a dica ao jogador
                    JOptionPane.showMessageDialog(frame,
                            "Dica: A " + (hintIndex + 1) + "ª letra de uma palavra é '" + word.charAt(hintIndex) + "'");

                    // Atualiza o índice de dica no mapa
                    hintsIndexMap.put(word, (hintIndex + 1) % word.length());
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Todas as palavras já foram adivinhadas!");
        } else {
            JOptionPane.showMessageDialog(frame, "Moedas insuficientes para pedir uma dica.");
        }
    }

    public static void main(String[] args) {
        try {
            GURU2P2 game = new GURU2P2("ficheiro_niveis.txt", "portuguese-large.txt");
            game.createAndShowGUI();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro carregando os níveis: " + e.getMessage());
        }
    }
}
