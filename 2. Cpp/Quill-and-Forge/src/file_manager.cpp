// file_manager.cpp
#include "file_manager.h"
#include <fstream>

bool FileManager::save(const std::string& filename, const Document& doc) {
    std::ofstream outFile(filename);
    if (!outFile) return false;
    doc.print(outFile);
    return true;
}

bool FileManager::load(const std::string& filename, Document& doc) {
    std::ifstream inFile(filename);
    if (!inFile) return false;
    std::string line;
    while (std::getline(inFile, line)) {
        doc.append(line);
    }
    return true;
}
