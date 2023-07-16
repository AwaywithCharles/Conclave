// main.cpp
#include "document.h"
#include <iostream>

int main() {
    Document doc;
    std::string command;
    while (true) {
        std::cout << "Enter command (edit, append, print, quit): ";
        std::cin >> command;
        if (command == "edit") {
            int line;
            std::string text;
            std::cout << "Enter line number: ";
            std::cin >> line;
            std::cout << "Enter new text: ";
            std::cin.ignore();
            std::getline(std::cin, text);
            doc.edit(line, text);
        } else if (command == "append") {
            std::string text;
            std::cout << "Enter new text: ";
            std::cin.ignore();
            std::getline(std::cin, text);
            doc.append(text);
        } else if (command == "print") {
            doc.print();
        } else if (command == "quit") {
            break;
        }
    }
    return 0;
}

