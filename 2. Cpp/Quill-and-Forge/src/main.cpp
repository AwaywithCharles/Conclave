// main.cpp
#include "document.h"
#include "file_manager.h"
#include <iostream>

int main() {
    Document doc;
    std::string command;
    while (true) {
        std::cout << "Enter command (edit, append, print, save, load, quit): ";
        std::cin >> command;
        if (command == "edit") {
            // as before...
        } else if (command == "append") {
            // as before...
        } else if (command == "print") {
            doc.print(std::cout);
        } else if (command == "save") {
            std::string filename;
            std::cout << "Enter filename: ";
            std::cin >> filename;
            if (FileManager::save(filename, doc)) {
                std::cout << "Document saved." << std::endl;
            } else {
                std::cout << "Save failed." << std::endl;
            }
        } else if (command == "load") {
            std::string filename;
            std::cout << "Enter filename: ";
            std::cin >> filename;
            if (FileManager::load(filename, doc)) {
                std::cout << "Document loaded." << std::endl;
            } else {
                std::cout << "Load failed." << std::endl;
            }
        } else if (command == "quit") {
            break;
        }
    }
    return 0;
}
