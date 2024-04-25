// words
const words = [
    "algorithm", "function", "variable", "loop", "array", "object",
    "class", "inheritance", "encapsulation", "polymorphism", "interface",
    "module", "package", "exception", "thread", "process", "synchronization",
    "memory"
];

// definitions
const wordDefinitions = {
    "algorithm": "A step-by-step procedure for solving a problem.",
};

// game state var.
let selectedWord, selectedDefinition;
let guessedLetters, wrongGuesses, attempts;

// max allowed incorrect guesses.
const maxAttempts = 6;

// paths to images corresponding to different stages of hangman.
const hangmanImages = [
    'images/hangman/HangmanPole.png',
    'images/hangman/HangmanPoleWithHead.png',
    'images/hangman/HangmanPoleWithHeadandBody.png',
    'images/hangman/HangmanPoleWithHeadandBodyAndLeftHand.png',
    'images/hangman/HangmanPoleWithHeadandBodyAndBothHands.png',
    'images/hangman/HangmanPoleWithHeadandBodyAndBothHandsandLeftLeg.png',
    'images/hangman/HangmanPoleWithHangman.png'
];

// init game once the HTML document has fully loaded.
document.addEventListener('DOMContentLoaded', initializeGame);

// init game by setting game state and updating UI components.
function initializeGame() {
    // select random word from list.
    const randomIndex = Math.floor(Math.random() * words.length);
    selectedWord = words[randomIndex];
    selectedDefinition = wordDefinitions[selectedWord];

    // reset game state.
    guessedLetters = [];
    wrongGuesses = [];
    attempts = 0;

    // init UI components.
    createLetterButtons();
    updateWordDisplay();
    updateHangmanImage();
    updateWrongGuesses();
}

// creates/displays letter buttons for user interaction.
function createLetterButtons() {
    // define alphabet for buttons 
    const letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const letterContainer = document.getElementById('letterButtons');
    letterContainer.innerHTML = '';

    // create button for each letter in alph
    letters.split('').forEach(letter => {
        const button = document.createElement('button');
        button.textContent = letter;
        button.id = 'letter-' + letter;
        button.onclick = function() { handleGuess(letter); };
        letterContainer.appendChild(button);
    });
}

// handles logic whenletter guessed.
function handleGuess(letter) {
    const button = document.getElementById('letter-' + letter);
    button.disabled = true; // disable button after click.

    if (selectedWord.toUpperCase().includes(letter)) {
        // if letter in word, update display.
        guessedLetters.push(letter);
        button.style.backgroundColor = '#D3D3D3';
        updateWordDisplay();
    } else {
        // if letter not in word, handle incorrect guess.
        if (!wrongGuesses.includes(letter)) {
            wrongGuesses.push(letter);
            attempts++;
            button.style.backgroundColor = 'red';
            updateHangmanImage();
            updateWrongGuesses();
        }
    }
    // check if game is over after each guess.
    checkGameOver();
}

// updates display of word with correctly guessed letters.
function updateWordDisplay() {
    const wordDisplay = document.getElementById('wordDisplay');
    wordDisplay.innerText = selectedWord.split('').map(letter => 
        guessedLetters.includes(letter.toUpperCase()) ? letter : '_'
    ).join(' ');
}

// checks if game has ended by winning or losing.
function checkGameOver() {
    const wordDisplay = document.getElementById('wordDisplay').innerText.replace(/\s+/g, '');
    if (!wordDisplay.includes('_')) {
        // if no blanks, player wins.
        alert(`Congratulations! You won! The word was "${selectedWord}". Definition: ${selectedDefinition}`);
        initializeGame();
    } else if (attempts > maxAttempts) {
        // If max number of attempts is exceeded, player loses.
        alert(`Game Over! The correct word was "${selectedWord}". Definition: ${selectedDefinition}`);
        initializeGame();
    }
}

// updates hangman image to reflect current number of incorrect guesses.
function updateHangmanImage() {
    const hangmanImage = document.querySelector('.hangman-images');
    if (attempts <= maxAttempts) {
        hangmanImage.src = hangmanImages[attempts];
    }
}

// handles logic when player submits fullword guess.
function submitFullWordGuess() {
    const fullWordGuess = document.getElementById('fullWordGuess');
    const guess = fullWordGuess.value.toLowerCase();
    fullWordGuess.value = '';

    if (guess === selectedWord) {
        // If the guess is correct, the player wins.
        guessedLetters = selectedWord.split('');
        updateWordDisplay();
        alert(`Congratulations! You guessed the word: ${selectedWord}. Definition: ${selectedDefinition}`);
        initializeGame();
    } else {
        // uncorrect fullword guesses count as a failed attempt(s).
        attempts++;
        updateHangmanImage();
        checkGameOver();
    }
}
