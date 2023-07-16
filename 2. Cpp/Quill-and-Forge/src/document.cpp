// document.cpp
#include "document.h"
#include <iostream>

void Document::edit(int line, const std::string& text) {
    if (line < lines.size()) {
        lines[line] = text;
    }
}

void Document::append(const std::string& text) {
    lines.push_back(text);
}

void Document::print() const {
    for (const auto& line : lines) {
        std::cout << line << std::endl;
    }
}

